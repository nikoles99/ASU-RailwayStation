package api.controllers;

import api.model.SimpleResponseBean;
import api.model.TrainBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@RestController
@EnableAutoConfiguration
public class TrainController extends AbstractController{

    @RequestMapping(value = "/addNewRoute", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody SimpleResponseBean home(TrainBean trainBean) {
        return new SimpleResponseBean("ok");
    }
}
