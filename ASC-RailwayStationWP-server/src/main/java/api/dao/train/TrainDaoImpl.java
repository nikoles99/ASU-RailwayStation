package api.dao.train;

import api.dao.AbstractDao;
import api.entity.StationEntity;
import api.entity.TrainEntity;
import api.entity.TrainScheduleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Repository
public class TrainDaoImpl extends AbstractDao<TrainEntity> implements TrainDao {

    private static final Logger logger = LoggerFactory.getLogger(TrainDaoImpl.class);

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
        return null;
    }

    @Override
    public List<TrainEntity> getTrainsByParams(String arrivalStation, String departureDate, String arrivalDate, String departureDate1) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TrainEntity> criteriaQuery = criteriaBuilder.createQuery(TrainEntity.class);

        Root<TrainEntity> trainEntityRoot = criteriaQuery.from(TrainEntity.class);

        Join<TrainEntity, TrainScheduleEntity> trainScheduleJoin = trainEntityRoot.join("scheduleEntities");
        Join<TrainScheduleEntity, StationEntity> stationEntityJoin = trainScheduleJoin.join("stationEntity");

        List<Predicate> conditions = new ArrayList();

        conditions.add(criteriaBuilder.equal(stationEntityJoin.get("name"), "Минск - Гродно"));
        //conditions.add(criteriaBuilder.equal(stationEntityJoin.get("name"), departureDate));
        //conditions.add(criteriaBuilder.greaterThan(trainScheduleJoin.get("arrivalDate").as(Date.class), new Date()));
        //conditions.add(criteriaBuilder.lessThan(trainScheduleJoin.get("departureDate").as(Date.class), new Date()));

        TypedQuery<TrainEntity> typedQuery = getEntityManager().createQuery(criteriaQuery
                .select(trainEntityRoot)
                .where(conditions.toArray(new Predicate[] {}))
                .orderBy(criteriaBuilder.asc(trainEntityRoot.get("name")))
                .distinct(true)
        );
        return typedQuery.getResultList();
    }

}
