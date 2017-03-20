package api.dao.train;

import api.dao.AbstractDao;
import api.entity.StationEntity;
import api.entity.TrainEntity;
import api.entity.TrainScheduleEntity;
import api.utils.DateUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
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
@Transactional(propagation = Propagation.REQUIRES_NEW)
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

  /*  @Override
    public List<TrainEntity> getTrainsByParams(String arrivalStation, String departureStation, Date arrivalDate, Date departureDate) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TrainEntity> criteriaQuery = criteriaBuilder.createQuery(TrainEntity.class);

        Root<TrainEntity> trainEntityRoot = criteriaQuery.from(TrainEntity.class);

        Join<TrainEntity, TrainScheduleEntity> trainScheduleJoin = trainEntityRoot.join("scheduleEntities");
        Join<TrainScheduleEntity, StationEntity> stationEntityJoin = trainScheduleJoin.join("stationEntity");

        Predicate arrivalStationEquals = criteriaBuilder.equal(stationEntityJoin.get("name"), arrivalStation);
        Predicate departureDateEqualsPredicate = criteriaBuilder.equal(stationEntityJoin.get("name"), departureStation);

        Predicate arrivalDateLessArrivalDate = criteriaBuilder.lessThanOrEqualTo(trainScheduleJoin.get("arrivalDate").as(Date.class), arrivalDate);
        Predicate arrivalDateGreaterDepartureDate = criteriaBuilder.greaterThanOrEqualTo(trainScheduleJoin.get("arrivalDate").as(Date.class), departureDate);
        Predicate arrivalDateGroup = criteriaBuilder.and(arrivalStationEquals, arrivalDateLessArrivalDate, arrivalDateGreaterDepartureDate);

        Predicate departureDateGreaterDepartureDate = criteriaBuilder.greaterThanOrEqualTo(trainScheduleJoin.get("departureDate").as(Date.class), departureDate);
        Predicate departureDateLessArrivalDate = criteriaBuilder.lessThanOrEqualTo(trainScheduleJoin.get("departureDate").as(Date.class), arrivalDate);
        Predicate departureDateGroup = criteriaBuilder.and(departureDateEqualsPredicate, departureDateGreaterDepartureDate, departureDateLessArrivalDate);

        Predicate result = criteriaBuilder.or(arrivalDateGroup, departureDateGroup);
        TypedQuery<TrainEntity> typedQuery = getEntityManager().createQuery(criteriaQuery
                .select(trainEntityRoot)
                .where(result)
                .distinct(true)
        );
        return typedQ
        uery.getResultList();
    }*/

    @Override
    public List<TrainEntity> getTrainsByParams(String arrivalStation, String departureStation, Date arrivalDate, Date departureDate) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<TrainEntity> criteriaQuery = criteriaBuilder.createQuery(TrainEntity.class);
        Root<TrainEntity> trainEntityRoot = criteriaQuery.from(TrainEntity.class);

        Join<TrainEntity, TrainScheduleEntity> trainScheduleJoin = trainEntityRoot.join("scheduleEntities");
        Join<TrainScheduleEntity, StationEntity> stationEntityJoin1 = trainScheduleJoin.join("stationEntity");


        Subquery<Date> subQuery = criteriaQuery.subquery(Date.class);

        Root<TrainEntity> arrivalStationArrivalDate = subQuery.from(TrainEntity.class);
        Join<TrainEntity, Date> trainScheduleJoinDate = arrivalStationArrivalDate.join("scheduleEntities");
        Join<TrainScheduleEntity, StationEntity> stationEntityJoin = trainScheduleJoinDate.join("stationEntity");

        Predicate arrivalStationPredicate = criteriaBuilder.equal(stationEntityJoin.get("name"), arrivalStation);
        Predicate trainIdPredicate = criteriaBuilder.equal(arrivalStationArrivalDate.get("id"), trainEntityRoot.get("id"));
        Predicate re = criteriaBuilder.and(arrivalStationPredicate, trainIdPredicate);




        Predicate departureDateLessThan = criteriaBuilder.lessThan(trainScheduleJoin.get("departureDate").as(Date.class), subQuery);
        Predicate departureStationPredicate = criteriaBuilder.equal(stationEntityJoin1.get("name"), departureStation);
        Predicate arrivalDatPredicate = criteriaBuilder.lessThanOrEqualTo(trainScheduleJoin.get("arrivalDate").as(Date.class), arrivalDate);
        Predicate departureDatePredicate = criteriaBuilder.greaterThanOrEqualTo(trainScheduleJoin.get("departureDate").as(Date.class), departureDate);

        Predicate result = criteriaBuilder.and(departureStationPredicate, departureDateLessThan);

        subQuery.select(trainScheduleJoinDate).where(re);

        criteriaQuery.select(trainEntityRoot).where(result);

        TypedQuery<TrainEntity> resultQuery = getEntityManager().createQuery(criteriaQuery);


        return resultQuery.getResultList();
    }

}

/*
select * from "trains" t
        inner join "train_schedules" s on t.id = s.train_id
        join "stations" st on st.id = s.station_id where st.name = 'Гродно' and s.departure_date < (select s.arrival_date from "trains" t1
        inner join "train_schedules" s on t1.id = s.train_id
        join "stations" st on st.id = s.station_id where st.name = 'Молодечно' and t1.id = t.id)*/
