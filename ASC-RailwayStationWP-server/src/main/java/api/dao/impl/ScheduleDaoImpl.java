package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.ScheduleDao;
import api.entity.ScheduleEntity;
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
import java.util.Date;
import java.util.List;

/**
 * Created by nikita on 14.03.17.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class ScheduleDaoImpl extends AbstractDao<ScheduleEntity> implements ScheduleDao {

    @Autowired
    private Environment env;

    @Override
    public void add(ScheduleEntity schedule) {

    }

    @Override
    public ScheduleEntity get(Integer id) {
        return null;
    }

    @Override
    public void update(ScheduleEntity schedule) {

    }

    @Override
    public void remove(ScheduleEntity schedule) {

    }

    @Override
    public Boolean isValidate(ScheduleEntity schedule) {
        Integer stationId = schedule.getStation().getId();
        Date arrivalDate = schedule.getArrivalDate();
        Date departureDate = schedule.getDepartureDate();
        List<ScheduleEntity> schedules = getByParams(departureDate, arrivalDate, stationId);
        return schedules.isEmpty();
    }

    @Override
    public List<ScheduleEntity> getByParams(Date departureDate, Date arrivalDate, Integer stationId) {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ScheduleEntity> criteriaQuery = criteriaBuilder.createQuery(ScheduleEntity.class);

        Root<ScheduleEntity> trainEntityRoot = criteriaQuery.from(ScheduleEntity.class);

        Predicate stationIdPredicate = criteriaBuilder.equal(trainEntityRoot.get("station"), stationId);

        Integer intervalDeparture = Integer.parseInt(env.getProperty("train-interval-departure"));
        Date startArrivalInterval = DateUtils.takeAwayMinutes(arrivalDate, intervalDeparture);
        Date endArrivalInterval = DateUtils.addMinutes(arrivalDate, intervalDeparture);
        Predicate arrivalDatePredicate = criteriaBuilder.between(trainEntityRoot.get("arrivalDate").as(Date.class), startArrivalInterval, endArrivalInterval);

        Date startDepartureInterval = DateUtils.takeAwayMinutes(departureDate, intervalDeparture);
        Date endDepartureInterval = DateUtils.addMinutes(departureDate, intervalDeparture);
        Predicate departureDatePredicate = criteriaBuilder.between(trainEntityRoot.get("departureDate").as(Date.class), startDepartureInterval, endDepartureInterval);

        Predicate result = criteriaBuilder.and(stationIdPredicate, arrivalDatePredicate, departureDatePredicate);
        TypedQuery<ScheduleEntity> typedQuery = getEntityManager().createQuery(criteriaQuery
                .select(trainEntityRoot)
                .where(result)
        );
        return typedQuery.getResultList();
    }
}
