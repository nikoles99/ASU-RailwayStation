package api.dao;

import api.entity.PassengerEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface PassengerDao {

    void add(PassengerEntity passenger);

    PassengerEntity get(Integer id);

    void update(PassengerEntity passenger);

    void remove(PassengerEntity passenger);
}
