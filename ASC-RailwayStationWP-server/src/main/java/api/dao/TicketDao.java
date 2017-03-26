package api.dao;

import api.entity.TicketEntity;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface TicketDao {

    void add(TicketEntity order);

    TicketEntity getOrder(Integer id);

    void update(TicketEntity order);

    void remove(TicketEntity order);
}
