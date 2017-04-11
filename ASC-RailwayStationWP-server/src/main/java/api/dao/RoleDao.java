package api.dao;

import api.entity.RoleEntity;

import java.util.List;

/**
 * Created by nolesuk on 11-Apr-17.
 */
public interface RoleDao {

    RoleEntity getById(Integer id);

    List<RoleEntity> getAll();

    void add(RoleEntity role);
}
