package api.dao;

import api.entity.PassengerEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface PassengerDao {

    void addPassenger(PassengerEntity passengerEntity);

    PassengerEntity getPassenger(Integer id);

    void updatePassenger(PassengerEntity passengerEntity);

    void removePassenger(PassengerEntity passengerEntity);
}
