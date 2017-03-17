package api.dao.schedule;

import api.dao.AbstractDao;
import api.entity.TrainScheduleEntity;
import api.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nikita on 14.03.17.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Repository
public class TrainScheduleDaoImpl extends AbstractDao<TrainScheduleEntity> implements TrainScheduleDao {

    @Autowired
    private Environment env;


    @Override
    public void addTrainSchedule(TrainScheduleEntity trainScheduleEntity) {

    }

    @Override
    public TrainScheduleEntity getTrainSchedule(Integer id) {
        return null;
    }

    @Override
    public void updateTrainSchedule(TrainScheduleEntity trainScheduleEntity) {

    }

    @Override
    public void removeTrainSchedule(TrainScheduleEntity trainScheduleEntity) {

    }

    @Override
    public Boolean isScheduleValidate(TrainScheduleEntity schedule) {
        Integer stationId = schedule.getStationEntity().getId();
        Date arrivalDate = schedule.getArrivalDate();
        Date departureDate = schedule.getDepartureDate();
        List<TrainScheduleEntity> schedules = getScheduleByParams(departureDate, arrivalDate, stationId);
        return schedules.isEmpty();
    }

    @Override
    public List<TrainScheduleEntity> getScheduleByParams(Date departureDate, Date arrivalDate, Integer stationId) {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TrainScheduleEntity> criteriaQuery = criteriaBuilder.createQuery(TrainScheduleEntity.class);

        Root<TrainScheduleEntity> trainEntityRoot = criteriaQuery.from(TrainScheduleEntity.class);

        Predicate stationIdPredicate = criteriaBuilder.equal(trainEntityRoot.get("stationEntity"), stationId);

        Integer intervalDeparture = Integer.parseInt(env.getProperty("train-interval-departure"));
        Date startArrivalInterval = DateUtils.takeAwayMinutes(arrivalDate, intervalDeparture);
        Date endArrivalInterval = DateUtils.addMinutes(arrivalDate, intervalDeparture);
        Predicate arrivalDatePredicate = criteriaBuilder.between(trainEntityRoot.get("arrivalDate").as(Date.class), startArrivalInterval, endArrivalInterval);

        Date startDepartureInterval = DateUtils.takeAwayMinutes(departureDate, intervalDeparture);
        Date endDepartureInterval = DateUtils.addMinutes(departureDate, intervalDeparture);
        Predicate departureDatePredicate = criteriaBuilder.between(trainEntityRoot.get("departureDate").as(Date.class), startDepartureInterval, endDepartureInterval);

        Predicate result = criteriaBuilder.and(stationIdPredicate, arrivalDatePredicate, departureDatePredicate);
        TypedQuery<TrainScheduleEntity> typedQuery = getEntityManager().createQuery(criteriaQuery
                .select(trainEntityRoot)
                .where(result)
        );
        return typedQuery.getResultList();
    }
}
