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
@Repository
public class TicketDaoImpl extends AbstractDao<TicketEntity> implements TicketDao {


    @Override
    public List<TicketEntity> getBookedTickets() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<TicketEntity> criteriaQuery = criteriaBuilder.createQuery(TicketEntity.class);
        Root<TicketEntity> root = criteriaQuery.from(TicketEntity.class);

        Predicate userPredicate = criteriaBuilder.equal(root.get("user"), UserUtils.getUser().getId());

        criteriaQuery.select(root).where(userPredicate);
        TypedQuery<TicketEntity> resultQuery = getEntityManager().createQuery(criteriaQuery);
        return resultQuery.getResultList();
    }

    @Override
    public List<TicketEntity> getBookedTickets(Integer trainId, CarriageType carriageType, Date departureDate, Date arrivalDate) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<TicketEntity> criteriaQuery = criteriaBuilder.createQuery(TicketEntity.class);
        Root<TicketEntity> root = criteriaQuery.from(TicketEntity.class);

        Join<TicketEntity, PlaceEntity> ticketPlaceJoin = root.join("place");
        Join<PlaceEntity, CarriageEntity> placeCarriageJoin = ticketPlaceJoin.join("carriage");

        Predicate carriageTypePr = criteriaBuilder.equal(placeCarriageJoin.get("type").as(CarriageType.class), carriageType);
        Predicate trainPr = criteriaBuilder.equal(placeCarriageJoin.get("train"), trainId);
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

    private List<TicketEntity> getTicketsByPlaceId(Integer placeId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TicketEntity> criteriaQuery = criteriaBuilder.createQuery(TicketEntity.class);
        Root<TicketEntity> root = criteriaQuery.from(TicketEntity.class);
        Predicate placePredicate = criteriaBuilder.equal(root.get("place"), placeId);
        criteriaQuery.select(root).where(placePredicate);
        TypedQuery<TicketEntity> resultQuery = getEntityManager().createQuery(criteriaQuery);
        return resultQuery.getResultList();
    }


    @Override
    public TicketEntity addTicket(TicketEntity ticket) {
        List<TicketEntity> tickets = getTicketsByPlaceId(ticket.getPlace().getId());
        if (tickets.isEmpty()) {
            return merge(ticket);
        }
        throw new IllegalStateException("Место не доступно");
    }

    @Override
    public void removeTicket(TicketEntity ticket) {
        super.remove(ticket);
    }

    @Override
    public TicketEntity getById(Integer ticketId) {
        return super.getById(TicketEntity.class, ticketId);
    }
}
