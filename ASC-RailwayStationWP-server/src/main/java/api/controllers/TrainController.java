package api.controllers;

import api.exception.TrainException;
import api.model.TrainBean;
import api.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@RestController
@EnableAutoConfiguration
public class TrainController extends AbstractController {

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = "/addTrain", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    void addNewRoute(@RequestBody TrainBean train) throws TrainException {
        trainService.add(train);
    }

    @RequestMapping(value = "/getTrainsByStations", method = RequestMethod.POST)
    List<TrainBean> getTrainsByRoute(@RequestParam("arrivalStation") String arrivalStation, @RequestParam("departureStation") String departureStation) {
        return trainService.getByStations(arrivalStation, departureStation);
    }

    @RequestMapping(value = "/getTrainsByParams", method = RequestMethod.POST)
    List<TrainBean> getTrainsByParams(@RequestParam("arrivalStation") String arrivalStation, @RequestParam("departureStation") String departureStation,
                                      @RequestParam("arrivalDate") Date arrivalDate, @RequestParam("departureDate") Date departureDate) {
        return trainService.getByParams(arrivalStation, departureStation, arrivalDate, departureDate);
    }
}
