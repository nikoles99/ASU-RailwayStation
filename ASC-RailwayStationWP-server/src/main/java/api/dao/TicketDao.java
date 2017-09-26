package api.dao;

import api.entity.TicketEntity;
import api.model.CarriageType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 22-Feb-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface TicketDao {

    List<TicketEntity> getBookedTickets();

    List<TicketEntity> getBookedTickets(Integer trainId, CarriageType carriageType, Date departureDate, Date arrivalDate);

    TicketEntity addTicket(TicketEntity ticket);

    void removeTicket(TicketEntity ticket);

    TicketEntity getById(Integer ticketId);
}
