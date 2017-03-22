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

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void addUser(UserEntity userEntity) {
        persist(userEntity);
    }

    @Override
    public UserEntity getUser(Integer id) {
        return null;
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        return null;
    }

    @Override
    public void updateUser(UserEntity userEntity) {

    }

    @Override
    public void removeUser(UserEntity userEntity) {

    }
}
