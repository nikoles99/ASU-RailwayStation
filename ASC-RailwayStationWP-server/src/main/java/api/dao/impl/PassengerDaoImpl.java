package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.PassengerDao;
import api.entity.PassengerEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikita on 14.03.17.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class PassengerDaoImpl extends AbstractDao<PassengerEntity> implements PassengerDao {
    @Override
    public void add(PassengerEntity passenger) {

    }

    @Override
    public PassengerEntity get(Integer id) {
        return null;
    }

    @Override
    public void update(PassengerEntity passenger) {

    }

    @Override
    public void remove(PassengerEntity passenger) {

    }
}
