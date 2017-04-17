package api.service;

import api.model.CarriageType;
import api.model.PlaceBean;
import api.model.TicketBean;

import java.util.Date;
import java.util.List;

/**
 * Created by nikita on 14.03.17.
 */
public interface PlaceService {

    List<PlaceBean> getFreePlaces(Integer trainId, CarriageType carriageType, Date departureDate, Date  arrivalDate);

    List<PlaceBean> getFreePlaces(Integer trainId);

    Integer bookPlace(TicketBean ticket);

    List<TicketBean> getBookedTickets();
}
