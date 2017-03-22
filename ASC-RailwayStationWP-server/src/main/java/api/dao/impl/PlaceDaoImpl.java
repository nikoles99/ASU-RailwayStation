package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.PlaceDao;
import api.entity.PlaceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class PlaceDaoImpl extends AbstractDao<PlaceEntity> implements PlaceDao {

    @Override
    public PlaceEntity get(Integer id) {
        return getById(PlaceEntity.class, id);
    }

    @Override
    public void add(PlaceEntity place) {

    }

    @Override
    public void update(PlaceEntity place) {

    }

    @Override
    public void remove(PlaceEntity place) {

    }
}
