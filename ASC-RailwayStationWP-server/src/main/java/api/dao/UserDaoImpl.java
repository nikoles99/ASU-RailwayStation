package api.dao;

import api.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(UserEntity userEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userEntity);
        logger.info("User add successfully" + userEntity);
    }

    @Override
    public UserEntity getUser(Integer id) {
        return null;
    }
}
