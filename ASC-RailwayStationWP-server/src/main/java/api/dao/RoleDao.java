package api.dao;

import api.entity.RoleEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nolesuk on 11-Apr-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface RoleDao {

    RoleEntity getById(Integer id);

    List<RoleEntity> getAll();

    void add(RoleEntity role);
}
