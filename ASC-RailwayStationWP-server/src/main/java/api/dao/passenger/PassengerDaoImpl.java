package api.dao.passenger;

import api.dao.AbstractDao;
import api.entity.PassengerEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikita on 14.03.17.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Repository
public class PassengerDaoImpl extends AbstractDao<PassengerEntity> implements PassengerDao {
    @Override
    public void addPassenger(PassengerEntity passengerEntity) {

    }

    @Override
    public PassengerEntity getPassenger(Integer id) {
        return null;
    }

    @Override
    public void updatePassenger(PassengerEntity passengerEntity) {

    }

    @Override
    public void removePassenger(PassengerEntity passengerEntity) {

    }
}
