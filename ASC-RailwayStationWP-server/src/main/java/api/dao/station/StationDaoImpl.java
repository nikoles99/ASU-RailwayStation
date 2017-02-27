package api.dao.station;

import api.dao.user.UserDaoImpl;
import api.entity.StationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by nikita on 27.02.17.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Repository
public class StationDaoImpl implements StationDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addStation(StationEntity stationEntity) {
        entityManager.persist(stationEntity);
        logger.info("Station add successfully " + stationEntity);
        getStation("Ивье");
    }

    @Override
    public StationEntity getStation(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StationEntity> criteriaQuery = criteriaBuilder.createQuery(StationEntity.class);
        Root<StationEntity> stationEntityRoot = criteriaQuery.from(StationEntity.class);
        criteriaQuery.select(stationEntityRoot);
        criteriaQuery.where(criteriaBuilder.equal(stationEntityRoot.get("name"),name));
        StationEntity students = entityManager.createQuery(criteriaQuery).getSingleResult();
        return students;
    }

    @Override
    public void updateStation(StationEntity stationEntity) {

    }

    @Override
    public void removeStation(StationEntity stationEntity) {

    }
}
