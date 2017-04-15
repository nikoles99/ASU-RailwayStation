package api.controllers;

import api.model.CarriageBean;
import api.model.TrainBean;
import api.service.CarriageService;
import api.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/addTrain", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    void add(@RequestBody TrainBean train) throws Exception {
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

    @RequestMapping(value = "/getCarriageById", method = RequestMethod.POST)
    CarriageBean getCarriageById(@RequestParam("id") Integer id) {
        return carriageService.getById(id);
    }
}
