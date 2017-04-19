package api.service.impl;

import api.convertors.PlaceConverter;
import api.convertors.TicketConverter;
import api.dao.TicketDao;
import api.dao.TrainDao;
import api.entity.*;
import api.model.CarriageType;
import api.model.PlaceBean;
import api.model.TicketBean;
import api.service.PlaceService;
import api.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nikita on 14.03.17.
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private PlaceConverter placeConverter;

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private TicketDao ticketDao;


    @Override
    public List<PlaceBean> getFreePlaces(Integer trainId, CarriageType carriageType, Date departureDate, Date arrivalDate) {
        TrainEntity train = trainDao.get(trainId);
        List<TicketEntity> tickets = ticketDao.getBookedTickets(trainId, carriageType, departureDate, arrivalDate);
        List<CarriageEntity> carriages = getCarriagesByType(train, carriageType);
        List<PlaceEntity> freePlaces = getFreePlaces(carriages, tickets);
        return placeConverter.toBeanCollection(freePlaces);
    }


    private List<PlaceEntity> getFreePlaces(List<CarriageEntity> carriages, List<TicketEntity> bookedTickets) {
        for (CarriageEntity carriage : carriages) {
            removeBookedPlaces(bookedTickets, carriage);
        }
        return getPlaces(carriages);
    }

    private List<PlaceEntity> getPlaces(List<CarriageEntity> carriages) {
        List<PlaceEntity> freePlaces = new ArrayList<PlaceEntity>();
        for (CarriageEntity carriage : carriages) {
            freePlaces.addAll(carriage.getPlaces());
        }
        return freePlaces;
    }

    private void removeBookedPlaces(List<TicketEntity> bookedTickets, CarriageEntity carriage) {
        for (TicketEntity ticket : bookedTickets) {
            Integer carriageId = ticket.getPlace().getCarriage().getId();
            if (carriageId.equals(carriage.getId())) {
                PlaceEntity placeEntity = new PlaceEntity();
                Integer placeId = ticket.getPlace().getId();
                placeEntity.setId(placeId);
                carriage.getPlaces().remove(placeEntity);
            }
        }
    }


    private List<CarriageEntity> getCarriagesByType(TrainEntity train, CarriageType carriageType) {
        List<CarriageEntity> carriages = new ArrayList<CarriageEntity>();
        for (CarriageEntity carriage : train.getCarriages()) {
            if (carriageType.equals(carriage.getType())) {
                carriages.add(carriage);
            }
        }
        return carriages;
    }

    @Override
    public List<PlaceBean> getFreePlaces(Integer trainId) {
        return null;
    }

    @Override
    public Integer bookTicket(TicketBean ticket) {
        TicketEntity ticketEntity = ticketConverter.toEntity(ticket);
        UserEntity user = UserUtils.getUser();
        ticketEntity.setUser(user);
        ticketDao.addTicket(ticketEntity);
        return user.getId();
    }


    @Override
    public List<TicketBean> getBookedTickets() {
        List<TicketEntity> bookedTickets = ticketDao.getBookedTickets();
        return ticketConverter.toBeanCollection(bookedTickets);
    }

    @Override
    public void remove(Integer ticketId) {
        TicketEntity ticketEntity = ticketDao.getById(ticketId);
        ticketDao.removeTicket(ticketEntity);
    }
}
