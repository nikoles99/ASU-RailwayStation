package api.dao;

import api.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager getEntityManagerFactory() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void addUser(UserEntity userEntity) {
        EntityManager entityManager = getEntityManagerFactory();
        entityManager.persist(userEntity);
        logger.info("User add successfully " + userEntity);
    }

    @Override
    public UserEntity getUser(Integer id) {
        return null;
    }
}
