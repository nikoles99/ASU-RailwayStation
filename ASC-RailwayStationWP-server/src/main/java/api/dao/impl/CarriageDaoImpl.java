package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.CarriageDao;
import api.entity.CarriageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Repository
public class CarriageDaoImpl extends AbstractDao<CarriageEntity> implements CarriageDao {

    @Override
    public void add(CarriageEntity carriage) {

    }

    @Override
    public CarriageEntity get(Integer id) {
        return getById(CarriageEntity.class, id);
    }

    @Override
    public void update(CarriageEntity carriage) {

    }

    @Override
    public void remove(CarriageEntity carriage) {

    }
}
