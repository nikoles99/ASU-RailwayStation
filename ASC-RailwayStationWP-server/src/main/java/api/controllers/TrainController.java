package api.controllers;

import api.model.TrainBean;
import api.service.train.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    TrainBean addNewRoute(@RequestBody TrainBean trainBean) {
        trainService.addTrainRoute(trainBean);
        return trainBean;
    }
}
