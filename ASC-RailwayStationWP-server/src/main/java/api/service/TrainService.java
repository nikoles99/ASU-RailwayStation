package api.service;

import api.exception.TrainException;
import api.model.TrainBean;

import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
public interface TrainService {

    void add(TrainBean train) throws TrainException;

    List<TrainBean> getByStations(String arrivalStation, String departureStation);

    List<TrainBean> getByParams(String arrivalStation, String departureStation, String arrivalDate, String departureDate);

}
