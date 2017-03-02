package api.dao.station;

import api.dao.user.UserDaoImpl;
import api.entity.StationEntity;
import api.exception.StationException;
import api.model.SimpleResponseBean;
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
import java.util.List;

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
    public void addStation(StationEntity stationEntity) throws StationException {
        if (stationEntity == null || stationEntity.getName() == null || stationEntity.getName().isEmpty()) {
            throw new StationException(stationEntity + " entity or name is null");
        }
        List<StationEntity> stations = getStations(stationEntity.getName());
        if (stations.isEmpty()) {
            entityManager.persist(stationEntity);
            logger.info("Station add successfully " + stationEntity);
        } else {
            String message = "Station already exist " + stationEntity;
            logger.info(message);
            throw new StationException(message);
        }

    }

    @Override
    public List<StationEntity> getStations(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StationEntity> criteriaQuery = criteriaBuilder.createQuery(StationEntity.class);
        Root<StationEntity> stationEntityRoot = criteriaQuery.from(StationEntity.class);
        criteriaQuery.select(stationEntityRoot);
        criteriaQuery.where(criteriaBuilder.equal(stationEntityRoot.get("name"), name));
        List<StationEntity> students = entityManager.createQuery(criteriaQuery).getResultList();
        return students;
    }

    @Override
    public List<StationEntity> getAllStations() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StationEntity> criteriaQuery = criteriaBuilder.createQuery(StationEntity.class);
        Root<StationEntity> stationEntityRoot = criteriaQuery.from(StationEntity.class);
        criteriaQuery.select(stationEntityRoot);
        List<StationEntity> students = entityManager.createQuery(criteriaQuery).getResultList();
        return students;
    }

    @Override
    public void deleteStation(StationEntity stationEntity) {
        List<StationEntity> stations = getStations(stationEntity.getName());
        for (StationEntity station : stations) {
            entityManager.remove(station);
        }
    }

    @Override
    public void updateStation(StationEntity stationEntity) {
        List<StationEntity> stations = getStations(stationEntity.getName());
        for (StationEntity station : stations) {
            station.setName(stationEntity.getName());
        }
    }
}
