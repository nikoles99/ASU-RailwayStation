package api.controllers;

import api.exception.TrainException;
import api.model.SimpleResponseBean;
import api.model.TrainBean;
import api.service.train.TrainService;
import api.utils.MessagesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@RestController
@EnableAutoConfiguration
public class TrainController extends AbstractController {

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = "/addNewRoute", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    void addNewRoute(@RequestBody TrainBean trainBean) throws TrainException {
        trainService.addTrainRoute(trainBean);
    }

    @RequestMapping(value = "/getTrainsByRoute", method = RequestMethod.POST)
    List<TrainBean> getTrainsByRoute(@RequestParam("arrivalStation") String arrivalStation, @RequestParam("departureStation") String departureStation) {
        return trainService.getTrainsByRoute(arrivalStation, departureStation);
    }

    @RequestMapping(value = "/getTrainsByParams", method = RequestMethod.POST)
    List<TrainBean> getTrainsByParams(@RequestParam("arrivalStation") String arrivalStation, @RequestParam("departureStation") String departureStation,
                                      @RequestParam("arrivalDate") String arrivalDate, @RequestParam("departureDate") String departureDate) {
        return trainService.getTrainsByParams(arrivalStation, departureStation, arrivalDate, departureDate);
    }
}
