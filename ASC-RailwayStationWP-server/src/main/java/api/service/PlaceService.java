package api.service;

import api.model.CarriageType;
import api.model.PlaceBean;
import api.model.TicketBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by nikita on 14.03.17.
 */
@Transactional
public interface PlaceService {

  List<PlaceBean> getFreePlaces(Integer trainId, CarriageType carriageType, Date departureDate, Date arrivalDate);

  List<PlaceBean> getFreePlaces(Integer trainId);

  Integer bookTicket(TicketBean tickets);

  List<TicketBean> getBookedTickets();

  void remove(Integer ticketId);

  Map<CarriageType, Integer> getFreePlaces(Integer trainId, Date departureDate, Date arrivalDate);
}
