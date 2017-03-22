package api.controllers;

import api.exception.StationException;
import api.model.StationBean;
import api.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nikita on 27.02.17.
 */
@RestController
@EnableAutoConfiguration
public class StationController extends AbstractController {

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/addStation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addStaion(StationBean stationBean) throws StationException {
        stationService.addStation(stationBean);
    }

    @RequestMapping(value = "/deleteStation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteStation(StationBean stationBean) {
        stationService.deleteStation(stationBean);
    }

    @RequestMapping(value = "/updateStation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateStation(StationBean stationBean) {
        stationService.updateStation(stationBean);
    }

    @RequestMapping(value = "/getAllStations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<StationBean> getAllStations() {
        return stationService.getAllStations();
    }
}
