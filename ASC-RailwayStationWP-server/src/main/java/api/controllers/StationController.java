package api.controllers;

import api.model.StationBean;
import api.service.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nikita on 27.02.17.
 */
@RestController
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/addStation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addStaion(StationBean stationBean){
        stationService.addStation(stationBean);
    }

}
