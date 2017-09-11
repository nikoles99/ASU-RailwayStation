package api.controllers;

import api.model.CarriageBean;
import api.model.TrainBean;
import api.service.CarriageService;
import api.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@RestController
public class TrainController extends AbstractController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private CarriageService carriageService;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/addTrains", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void add(@RequestBody TrainBean train) throws Exception {
        trainService.add(train);
    }

    @RequestMapping(value = "/getTrainsByStations", method = RequestMethod.POST)
    List<TrainBean> getTrainsByRoute(@RequestParam("arrivalStation") String arrivalStation, @RequestParam("departureStation") String departureStation) {
        return trainService.getByStations(arrivalStation, departureStation);
    }

    @RequestMapping(value = "/getTrainsByParams", method = RequestMethod.POST)
    List<TrainBean> getTrainsByParams(@RequestParam("arrivalStation") String arrivalStation,
                                      @RequestParam("departureStation") String departureStation,
                                      @RequestParam("arrivalDate") String arrivalTime,
                                      @RequestParam("departureDate") String departureTime) {
        return trainService.getByParams(arrivalStation, departureStation, new Date(Long.parseLong(arrivalTime)), new Date(Long.parseLong(departureTime)));
    }

    @RequestMapping(value = "/getCarriageById", method = RequestMethod.POST)
    CarriageBean getCarriageById(@RequestParam("id") Integer id) {
        return carriageService.getById(id);
    }
}
