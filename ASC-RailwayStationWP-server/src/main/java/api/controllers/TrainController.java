package api.controllers;

import api.model.CarriageBean;
import api.model.TrainBean;
import api.model.TrainScheduleBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@RestController
@EnableAutoConfiguration
public class TrainController extends AbstractController {

    @RequestMapping(value = "/addNewRoute", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    TrainBean home(@RequestBody TrainBean trainBean) {
        return trainBean;
    }
}
