package api.service;

import api.exception.TrainException;
import api.model.TrainBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@Transactional
public interface TrainService {

    void add(TrainBean train) throws TrainException;

    List<TrainBean> getByStations(String arrivalStation, String departureStation);

    List<TrainBean> getByParams(String arrivalStation, String departureStation, Date arrivalDate, Date departureDate);

}
