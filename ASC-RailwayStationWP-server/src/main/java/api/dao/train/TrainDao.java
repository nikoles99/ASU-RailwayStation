package api.dao.train;

import api.entity.TrainEntity;

import java.util.List;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface TrainDao {

    void addTrain(TrainEntity trainEntity);

    TrainEntity getTrain(Integer id);

    void updateTrain(TrainEntity trainEntity);

    void removeTrain(TrainEntity trainEntity);


    List<TrainEntity> getTrainByRoute(String arrivalStation, String departureStation);

    List<TrainEntity> getTrainsByParams(String arrivalStation, String departureDate, String arrivalDate, String departureDate1);

}

