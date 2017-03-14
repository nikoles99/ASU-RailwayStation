package api.dao.carriage;

import api.dao.AbstractDao;
import api.dao.place.PlaceDaoImpl;
import api.entity.CarriageEntity;
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
public class CarriageDaoImpl extends AbstractDao<CarriageEntity> implements CarriageDao {

    private static final Logger logger = LoggerFactory.getLogger(PlaceDaoImpl.class);

    @Override
    public void addCarriage(CarriageEntity carriageEntity) {

    }

    @Override
    public CarriageEntity getCarriage(Integer id) {
        return getById(CarriageEntity.class, id);
    }

    @Override
    public void updateCarriage(CarriageEntity carriageEntity) {

    }

    @Override
    public void removeCarriage(CarriageEntity carriageEntity) {

    }
}
