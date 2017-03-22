package api.dao.carriage;

import api.entity.CarriageEntity;
import api.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 22-Feb-17.
 */

public interface CarriageDao {

    void addCarriage(CarriageEntity carriageEntity);

    CarriageEntity getCarriage(Integer id);

    void updateCarriage(CarriageEntity carriageEntity);

    void removeCarriage(CarriageEntity carriageEntity);
}
