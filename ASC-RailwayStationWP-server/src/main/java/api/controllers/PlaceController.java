package api.controllers;

import api.model.CarriageType;
import api.model.PlaceBean;
import api.model.TicketBean;
import api.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by nikita on 26.03.17.
 */
@RestController
public class PlaceController extends AbstractController {

  @Autowired
  private PlaceService placeService;

  @RequestMapping(value = "/getFreePlacesByType", method = RequestMethod.POST)
  List<PlaceBean> getFreePlacesByType(@RequestParam("trainId") Integer trainId, @RequestParam("carriageType") CarriageType type,
                                      @RequestParam("departureDate") Long departureDate, @RequestParam("arrivalDate") Long arrivalDate) {
    return placeService.getFreePlaces(trainId, type, new Date(departureDate), new Date(arrivalDate));
  }

  @RequestMapping(value = "/bookTicket",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
  Integer bookTicket(@RequestBody TicketBean ticket) throws Exception {
    return placeService.bookTicket(ticket);
  }

  @RequestMapping(value = "/getBookedTickets", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  @ResponseBody
  List<TicketBean> getBookedTickets() {
    return placeService.getBookedTickets();
  }

  @RequestMapping(value = "/removeTicket", method = RequestMethod.POST)
  void getBookedTickets(@RequestParam("ticketId") Integer ticketId) {
    placeService.remove(ticketId);
  }

  @RequestMapping(value = "/getFreePlacesSize", method = RequestMethod.POST)
  Map<CarriageType, Integer> getFreePlacesSize(@RequestParam("trainId") Integer trainId,
                                         @RequestParam("departureDate") Long departureDate,
                                         @RequestParam("arrivalDate") Long arrivalDate) {
    return placeService.getFreePlaces(trainId, new Date(departureDate), new Date(arrivalDate));
  }
}
