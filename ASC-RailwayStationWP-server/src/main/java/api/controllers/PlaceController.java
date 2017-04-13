package api.controllers;

import api.model.CarriageType;
import api.model.PlaceBean;
import api.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by nikita on 26.03.17.
 */
@RestController
public class PlaceController extends AbstractController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/getFreePlacesByType", method = RequestMethod.POST)
    List<PlaceBean> getFreePlacesByType(@RequestParam("trainId") Integer trainId, @RequestParam("carriageType") CarriageType type,
                                        @RequestParam("departureDate") Date departureDate, @RequestParam("arrivalDate") Date arrivalDate) {
        return placeService.getFreePlaces(trainId, type, departureDate, arrivalDate);
    }
}
