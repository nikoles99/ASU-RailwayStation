package api.dao;

import api.entity.CarriageEntity;
import api.entity.UserEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 22-Feb-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface CarriageDao {

    void add(CarriageEntity carriage);

    CarriageEntity get(Integer id);

    void update(CarriageEntity carriage);

    void remove(CarriageEntity carriage);
}
