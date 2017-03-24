package api.controllers;

import api.exception.StationException;
import api.model.StationBean;
import api.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addStaion(StationBean station) throws StationException {
        stationService.add(station);
    }

    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteStation(StationBean station) {
        stationService.delete(station);
    }

    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateStation(StationBean station) {
        stationService.update(station);
    }

    @RequestMapping(value = "/getAllStations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<StationBean> getAllStations() {
        return stationService.getAllStations();
    }

    @RequestMapping(value = "/getStationById", produces = MediaType.APPLICATION_JSON_VALUE)
    StationBean getStationById(@RequestParam("id") Integer id) {
        return stationService.getById(id);
    }

    @RequestMapping(value = "/getStationByName", produces = MediaType.APPLICATION_JSON_VALUE)
    StationBean getStationByName(@RequestParam("name") String name) {
        return stationService.getByName(name);
    }
}
