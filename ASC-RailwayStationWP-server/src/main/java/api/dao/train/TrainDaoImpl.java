package api.dao.train;

import api.dao.AbstractDao;
import api.entity.StationEntity;
import api.entity.TrainEntity;
import api.entity.ScheduleEntity;
import api.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(TrainDaoImpl.class);
    private static final int TWO_WEEKS = 14;

    @Override
    public void addTrain(TrainEntity trainEntity) {
        persist(trainEntity);
    }

    @Override
    public TrainEntity getTrain(Integer id) {
        return getById(TrainEntity.class, id);
    }

    @Override
    public void updateTrain(TrainEntity trainEntity) {

    }

    @Override
    public void removeTrain(TrainEntity trainEntity) {

    }

    @Override
    public List<TrainEntity> getTrainByRoute(String arrivalStation, String departureStation) {
        Date departureDate = new Date();
        Date arrivalDate = DateUtils.addDays(departureDate, TWO_WEEKS);
        return getTrainsByParams(arrivalStation, departureStation, arrivalDate, departureDate);
    }

    @Override
    public List<TrainEntity> getTrainsByParams(String arrivalStation, String departureStation, Date arrivalDate, Date departureDate) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<TrainEntity> criteriaQuery = criteriaBuilder.createQuery(TrainEntity.class);
        Root<TrainEntity> trainEntityRoot = criteriaQuery.from(TrainEntity.class);

        Join<TrainEntity, ScheduleEntity> trainScheduleJoin = trainEntityRoot.join("scheduleEntities");
        Join<ScheduleEntity, StationEntity> stationEntityJoin = trainScheduleJoin.join("stationEntity");

        Subquery<Date> subQuery = getDateSubQuery(arrivalStation, criteriaBuilder, criteriaQuery, trainEntityRoot);

        Predicate departureDateLessThan = criteriaBuilder.lessThan(trainScheduleJoin.get("departureDate").as(Date.class), subQuery);
        Predicate departureStationPredicate = criteriaBuilder.equal(stationEntityJoin.get("name"), departureStation);
        Predicate result = criteriaBuilder.and(departureDateLessThan, departureStationPredicate);


        criteriaQuery.select(trainEntityRoot).where(result);
        TypedQuery<TrainEntity> resultQuery = getEntityManager().createQuery(criteriaQuery);
        return resultQuery.getResultList();
    }


    private Subquery<Date> getDateSubQuery(String arrivalStation, CriteriaBuilder criteriaBuilder,
                                           CriteriaQuery<TrainEntity> criteriaQuery, Root<TrainEntity> trainEntityRoot) {
        Subquery<Date> subQuery = criteriaQuery.subquery(Date.class);

        Root<TrainEntity> subQueryRoot = subQuery.from(TrainEntity.class);
        Join<TrainEntity, ScheduleEntity> subQueryTrainScheduleJoin = subQueryRoot.join("scheduleEntities");
        Join<ScheduleEntity, StationEntity> subQueryStationEntityJoin = subQueryTrainScheduleJoin.join("stationEntity");


        Predicate arrivalStationPredicate = criteriaBuilder.equal(subQueryStationEntityJoin.get("name"), arrivalStation);
        Predicate trainIdPredicate = criteriaBuilder.equal(subQueryRoot.get("id"), trainEntityRoot.get("id"));
        Predicate resultSubQuery = criteriaBuilder.and(arrivalStationPredicate, trainIdPredicate);

        Expression<Date> arrivalDateExpression = subQueryTrainScheduleJoin.get("arrivalDate").as(Date.class);
        subQuery.select(arrivalDateExpression).where(resultSubQuery);
        return subQuery;
    }
}
