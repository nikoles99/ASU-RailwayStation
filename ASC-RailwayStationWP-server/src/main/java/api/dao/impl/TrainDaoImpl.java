package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.TrainDao;
import api.entity.StationEntity;
import api.entity.TrainEntity;
import api.entity.ScheduleEntity;
import api.utils.DateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class TrainDaoImpl extends AbstractDao<TrainEntity> implements TrainDao {

    private static final int TWO_WEEKS = 14;

    @Override
    public void add(TrainEntity train) {
        persist(train);
    }

    @Override
    public TrainEntity get(Integer id) {
        return getById(TrainEntity.class, id);
    }

    @Override
    public void update(TrainEntity train) {

    }

    @Override
    public void remove(TrainEntity train) {

    }

    @Override
    public List<TrainEntity> getByStations(String arrivalStation, String departureStation) {
        Date departureDate = new Date();
        Date arrivalDate = DateUtils.addDays(departureDate, TWO_WEEKS);
        return getByParams(arrivalStation, departureStation, arrivalDate, departureDate);
    }

    @Override
    public List<TrainEntity> getByParams(String arrivalStation, String departureStation, Date arrivalDate, Date departureDate) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<TrainEntity> criteriaQuery = criteriaBuilder.createQuery(TrainEntity.class);
        Root<TrainEntity> trainEntityRoot = criteriaQuery.from(TrainEntity.class);

        Join<TrainEntity, ScheduleEntity> trainScheduleJoin = trainEntityRoot.join("schedules");
        Join<ScheduleEntity, StationEntity> stationEntityJoin = trainScheduleJoin.join("station");

        Subquery<Date> subQuery = getSubQuery(arrivalStation, criteriaBuilder, criteriaQuery, trainEntityRoot);

        Predicate departureDateLessThan = criteriaBuilder.lessThan(trainScheduleJoin.get("departureDate").as(Date.class), subQuery);
        Predicate departureStationPredicate = criteriaBuilder.equal(stationEntityJoin.get("name"), departureStation);
        Predicate departureDateGreaterThan = criteriaBuilder.greaterThan(trainScheduleJoin.get("departureDate").as(Date.class), departureDate);
        Predicate arrivalDateLessThan = criteriaBuilder.lessThan(trainScheduleJoin.get("arrivalDate").as(Date.class), arrivalDate);
        Predicate result = criteriaBuilder.and(departureDateLessThan, departureStationPredicate, departureDateGreaterThan, arrivalDateLessThan);


        criteriaQuery.select(trainEntityRoot).where(result);
        TypedQuery<TrainEntity> resultQuery = getEntityManager().createQuery(criteriaQuery);
        return resultQuery.getResultList();
    }


    private Subquery<Date> getSubQuery(String arrivalStation, CriteriaBuilder criteriaBuilder,
                                       CriteriaQuery<TrainEntity> criteriaQuery, Root<TrainEntity> trainEntityRoot) {
        Subquery<Date> subQuery = criteriaQuery.subquery(Date.class);

        Root<TrainEntity> subQueryRoot = subQuery.from(TrainEntity.class);
        Join<TrainEntity, ScheduleEntity> subQueryTrainScheduleJoin = subQueryRoot.join("schedules");
        Join<ScheduleEntity, StationEntity> subQueryStationEntityJoin = subQueryTrainScheduleJoin.join("station");


        Predicate arrivalStationPredicate = criteriaBuilder.equal(subQueryStationEntityJoin.get("name"), arrivalStation);
        Predicate trainIdPredicate = criteriaBuilder.equal(subQueryRoot.get("id"), trainEntityRoot.get("id"));
        Predicate resultSubQuery = criteriaBuilder.and(arrivalStationPredicate, trainIdPredicate);

        Expression<Date> arrivalDateExpression = subQueryTrainScheduleJoin.get("arrivalDate").as(Date.class);
        subQuery.select(arrivalDateExpression).where(resultSubQuery);
        return subQuery;
    }
}
