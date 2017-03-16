package api.service.train;

import api.model.TrainBean;

import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
public interface TrainService {

    void addTrain();

    void addTrainRoute(TrainBean trainBean);

    List<TrainBean> getTrainsByRoute(String arrivalStation, String departureStation);

    List<TrainBean> getTrainsByParams(String arrivalStation, String departureStation, String arrivalDate, String departureDate);

}
