package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.UserDao;
import api.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {

    @Override
    public void add(UserEntity user) {
        persist(user);
    }

    @Override
    public UserEntity get(Integer id) {
        return null;
    }

    @Override
    public UserEntity getByLogin(String login) {
        return null;
    }

    @Override
    public void update(UserEntity user) {

    }

    @Override
    public void delete(UserEntity user) {

    }
}
