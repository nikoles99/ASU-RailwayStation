package api.dao.place;

import api.dao.user.UserDaoImpl;
import api.entity.PlaceEntity;
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
public class PlaceDaoImpl implements PlaceDao {

    private static final Logger logger = LoggerFactory.getLogger(PlaceDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addPlace(PlaceEntity placeEntity) {

    }

    @Override
    public PlaceEntity getPlace(Integer id) {
        return entityManager.find(PlaceEntity.class, id);
    }

    @Override
    public void updatePlace(PlaceEntity placeEntity) {

    }

    @Override
    public void removePlace(PlaceEntity placeEntity) {

    }
}
