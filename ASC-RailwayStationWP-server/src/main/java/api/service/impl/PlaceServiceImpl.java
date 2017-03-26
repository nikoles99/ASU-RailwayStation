package api.service.impl;

import api.convertors.PlaceConverter;
import api.dao.TicketDao;
import api.dao.TrainDao;
import api.entity.CarriageEntity;
import api.entity.PlaceEntity;
import api.entity.TicketEntity;
import api.entity.TrainEntity;
import api.model.CarriageType;
import api.model.PlaceBean;
import api.service.PlaceService;
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
            Integer carriageId = ticket.getCarriage().getId();
            if (carriageId.equals(carriage.getId())) {
                PlaceEntity placeEntity = new PlaceEntity();
                Integer placeId = ticket.getPlaceId();
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
}
