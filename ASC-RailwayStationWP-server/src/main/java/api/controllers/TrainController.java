package api.controllers;

import api.model.CarriageBean;
import api.model.SimpleResponseBean;
import api.model.TrainBean;
import api.model.TrainScheduleBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@RestController @EnableAutoConfiguration public class TrainController extends AbstractController {

    @RequestMapping(value = "/addNewRoute", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST) @ResponseBody TrainBean home(
            TrainBean trainBean) {
        TrainBean trainBean1 = new TrainBean();
        trainBean1.setNumber(1);
        trainBean1.setSchedules(new HashSet<TrainScheduleBean>() {{
            add(new TrainScheduleBean());
            add(new TrainScheduleBean());
            add(new TrainScheduleBean());
        }});
        trainBean1.setCarriages(new HashSet<CarriageBean>() {{
            add(new CarriageBean());
            add(new CarriageBean());
        }});
        return trainBean1;
    }
}
