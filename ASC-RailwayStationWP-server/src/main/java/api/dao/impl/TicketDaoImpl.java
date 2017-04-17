package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.TicketDao;
import api.entity.*;
import api.model.CarriageType;
import api.utils.UserUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class TicketDaoImpl extends AbstractDao<TicketEntity> implements TicketDao {


    @Override
    public List<TicketEntity> getBookedTickets() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<TicketEntity> criteriaQuery = criteriaBuilder.createQuery(TicketEntity.class);
        Root<TicketEntity> root = criteriaQuery.from(TicketEntity.class);

        Predicate userPredicate = criteriaBuilder.equal(root.get("userId"), UserUtils.getUser().getId());

        criteriaQuery.select(root).where(userPredicate);
        TypedQuery<TicketEntity> resultQuery = getEntityManager().createQuery(criteriaQuery);
        return resultQuery.getResultList();
    }

    @Override
    public List<TicketEntity> getBookedTickets(Integer trainId, CarriageType carriageType, Date departureDate, Date arrivalDate) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<TicketEntity> criteriaQuery = criteriaBuilder.createQuery(TicketEntity.class);
        Root<TicketEntity> root = criteriaQuery.from(TicketEntity.class);

        Join<TicketEntity, CarriageEntity> ticketCarriageJoin = root.join("carriage");

        Predicate carriageTypePr = criteriaBuilder.equal(ticketCarriageJoin.get("type").as(CarriageType.class), carriageType);
        Predicate trainPr = criteriaBuilder.equal(root.get("trainId"), trainId);
        Predicate departureDateBetweenPr = criteriaBuilder.between(root.get("departureDate").as(Date.class), departureDate, arrivalDate);
        Predicate arrivalDateBetweenPr = criteriaBuilder.between(root.get("arrivalDate").as(Date.class), departureDate, arrivalDate);
        Predicate departureDatePr = criteriaBuilder.lessThan(root.get("departureDate").as(Date.class), departureDate);
        Predicate arrivalDatePr = criteriaBuilder.greaterThan(root.get("arrivalDate").as(Date.class), arrivalDate);

        Predicate trainAndCarriageTypePr = criteriaBuilder.and(carriageTypePr, trainPr);
        Predicate departureAndArrivalPr = criteriaBuilder.and(arrivalDatePr, departureDatePr);
        Predicate result = criteriaBuilder.or(trainAndCarriageTypePr, departureAndArrivalPr, departureDateBetweenPr, arrivalDateBetweenPr);

        criteriaQuery.select(root).where(result);
        TypedQuery<TicketEntity> resultQuery = getEntityManager().createQuery(criteriaQuery);
        return resultQuery.getResultList();
    }


    @Override
    public TicketEntity addTicket(TicketEntity ticket) {
        return merge(ticket);
    }

    @Override
    public void removeTicket(TicketEntity ticket) {
        remove(ticket);
    }
}
