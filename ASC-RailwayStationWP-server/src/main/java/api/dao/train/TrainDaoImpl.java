package api.dao.train;

import api.dao.user.UserDaoImpl;
import api.entity.StationEntity;
import api.entity.TrainEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Repository
public class TrainDaoImpl implements TrainDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addTrain(TrainEntity trainEntity) {
        entityManager.persist(trainEntity);
        logger.info("Station add successfully " + trainEntity);
    }

    @Override
    public TrainEntity getTrain(Integer id) {
        if (id != null) {
            return entityManager.find(TrainEntity.class, id);
        }
        return null;
    }

    @Override
    public void updateTrain(TrainEntity trainEntity) {

    }

    @Override
    public void removeTrain(TrainEntity trainEntity) {

    }
}
