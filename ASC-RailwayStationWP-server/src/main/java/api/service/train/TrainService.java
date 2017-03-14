package api.service.train;

import api.entity.StationEntity;
import api.model.TrainBean;

import java.util.Date;

/**
 * Created by nolesuk on 07-Mar-17.
 */
public interface TrainService {

    void addTrain();

    void addTrainRoute(TrainBean trainBean);

    void getTrainByRoute(String route);

    void getTrainByArrivalStation(StationEntity stationEntity);

    void getTrainByDepartureStation(StationEntity stationEntity);

    void getTrainByArrivalDate(Date arrivalDate);

    void getTrainByDepartureDate(Date departureDate);

}
