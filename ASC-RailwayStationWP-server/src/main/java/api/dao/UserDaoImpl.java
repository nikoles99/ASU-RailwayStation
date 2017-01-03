package api.dao;

import api.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        logger.info("User add successfully " + userEntity);
    }

    @Override
    public UserEntity getUser(Integer id) {
        return null;
    }
}
