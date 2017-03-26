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
        return null;
    }

    @Override
    public void update(UserEntity user) {
        super.merge(user);
    }

    @Override
    public void delete(UserEntity user) {
        super.remove(user);
    }

    @Override
    public void bookTicket(TicketEntity ticket) {
        ticketDao.addTicket(ticket);
    }
}
