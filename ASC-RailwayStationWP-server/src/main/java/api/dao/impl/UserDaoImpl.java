package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.TicketDao;
import api.dao.UserDao;
import api.entity.TicketEntity;
import api.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public void add(UserEntity user) {
        super.persist(user);
    }

    @Override
    public UserEntity get(Integer id) {
        return super.getById(UserEntity.class, id);
    }

    @Override
    public UserEntity getByLogin(String login) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        Predicate loginPredicate = criteriaBuilder.equal(root.get("login"), login);
        criteriaQuery.select(root).where(loginPredicate);
        TypedQuery<UserEntity> resultQuery = getEntityManager().createQuery(criteriaQuery);
        return resultQuery.getSingleResult();
    }

    @Override
    public void update(UserEntity user) {
        super.merge(user);
    }

    @Override
    public void delete(UserEntity user) {
        super.remove(user);
    }

}
