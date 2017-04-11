package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.RoleDao;
import api.entity.RoleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nolesuk on 11-Apr-17.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class RoleDaoImpl extends AbstractDao<RoleEntity> implements RoleDao {
    @Override
    public RoleEntity getById(Integer id) {
        return getById(id);
    }

    @Override
    public List<RoleEntity> getAll() {
        return getAll();
    }

    @Override
    public void add(RoleEntity role) {
        persist(role);
    }
}
