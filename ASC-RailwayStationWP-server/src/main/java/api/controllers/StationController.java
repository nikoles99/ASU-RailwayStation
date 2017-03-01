package api.controllers;

import api.exception.StationException;
import api.model.SimpleResponseBean;
import api.model.StationBean;
import api.service.station.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nikita on 27.02.17.
 */
@RestController
@EnableAutoConfiguration
public class StationController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StationController.class);

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/addStation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    SimpleResponseBean addStaion(StationBean stationBean) {
        try {
            stationService.addStation(stationBean);
            return new SimpleResponseBean("success");
        } catch (StationException e) {
            e.printStackTrace();
            String message = e.getMessage();
            LOGGER.error(message);
            return new SimpleResponseBean(message);
        }
    }
}
