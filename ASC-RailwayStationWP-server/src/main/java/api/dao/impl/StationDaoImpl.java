package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.StationDao;
import api.entity.StationEntity;
import api.exception.StationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by nikita on 27.02.17.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class StationDaoImpl extends AbstractDao<StationEntity> implements StationDao {

    @Override
    public void add(StationEntity station) throws StationException {
        if (station == null || station.getName() == null || station.getName().isEmpty()) {
            throw new StationException(station + " entity or name is null");
        }
        StationEntity stationEntity = getByName(station.getName());
        if (stationEntity != null) {
            getEntityManager().persist(station);
            getLogger().info("Station add successfully " + station);
        } else {
            String message = "Station already exist " + station;
            getLogger().info(message);
            throw new StationException(message);
        }

    }

    @Override
    public StationEntity getByName(String name) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StationEntity> criteriaQuery = criteriaBuilder.createQuery(StationEntity.class);
        Root<StationEntity> stationEntityRoot = criteriaQuery.from(StationEntity.class);
        criteriaQuery.select(stationEntityRoot);
        criteriaQuery.where(criteriaBuilder.equal(stationEntityRoot.get("name"), name));
        TypedQuery<StationEntity> query = getEntityManager().createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<StationEntity> getAllStations() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StationEntity> criteriaQuery = criteriaBuilder.createQuery(StationEntity.class);
        Root<StationEntity> stationEntityRoot = criteriaQuery.from(StationEntity.class);
        criteriaQuery.select(stationEntityRoot);
        List<StationEntity> students = getEntityManager().createQuery(criteriaQuery).getResultList();
        return students;
    }

    @Override
    public void delete(StationEntity station) {
        remove(station);
    }

    @Override
    public StationEntity getById(Integer id) {
        return getById(StationEntity.class, id);
    }

    @Override
    public void update(StationEntity station) {
        update(station);
    }
}
