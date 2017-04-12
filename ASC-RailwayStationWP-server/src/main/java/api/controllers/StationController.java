package api.controllers;

import api.entity.StationEntity;
import api.exception.StationException;
import api.model.StationBean;
import api.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nikita on 27.02.17.
 */
@RestController
public class StationController extends AbstractController {

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/addStation", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    void addStaion(StationBean station) throws Exception {
        stationService.add(station);
    }

    @RequestMapping(value = "/deleteStation", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    void deleteStation(StationBean station) {
        stationService.delete(station);
    }

    @RequestMapping(value = "/updateStation", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    void updateStation(StationBean station) {
        stationService.update(station);
    }

    @RequestMapping(value = "/getAllStations", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    List<StationBean> getAllStations() {
        return stationService.getAllStations();
    }

    @RequestMapping(value = "/getStationById", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    StationBean getStationById(@RequestParam("id") Integer id) {
        return stationService.getById(id);
    }

    @RequestMapping(value = "/getStationByName", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    List<StationBean> getStationByName(@RequestParam("name") String name) {
        return stationService.getByName(name);
    }
}
