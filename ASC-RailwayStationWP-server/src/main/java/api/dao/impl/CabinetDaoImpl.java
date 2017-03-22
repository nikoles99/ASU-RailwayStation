package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.CabinetDao;
import api.entity.CabinetEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikita on 14.03.17.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class CabinetDaoImpl extends AbstractDao<CabinetEntity> implements CabinetDao {

    @Override
    public void add(CabinetEntity cabinet) {

    }

    @Override
    public CabinetEntity get(Integer id) {
        return null;
    }

    @Override
    public void update(CabinetEntity cabinet) {

    }

    @Override
    public void remove(CabinetEntity cabinet) {

    }
}
