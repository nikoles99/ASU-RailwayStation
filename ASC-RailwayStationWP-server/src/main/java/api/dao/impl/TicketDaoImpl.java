package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.TicketDao;
import api.entity.TicketEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class TicketDaoImpl extends AbstractDao<TicketEntity> implements TicketDao {

    @Override
    public void add(TicketEntity order) {

    }

    @Override
    public TicketEntity getOrder(Integer id) {
        return getById(TicketEntity.class, id);
    }

    @Override
    public void update(TicketEntity order) {

    }

    @Override
    public void remove(TicketEntity order) {

    }
}
