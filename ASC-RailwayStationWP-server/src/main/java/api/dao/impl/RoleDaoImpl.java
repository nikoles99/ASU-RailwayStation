package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.RoleDao;
import api.entity.RoleEntity;

import java.util.List;

/**
 * Created by nolesuk on 11-Apr-17.
 */
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
