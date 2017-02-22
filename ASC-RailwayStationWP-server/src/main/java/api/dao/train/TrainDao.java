package api.dao.train;

import api.entity.TrainEntity;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface TrainDao {

    void addTrain(TrainEntity trainEntity);

    TrainEntity getTrain(Integer id);

    void updateTrain(TrainEntity trainEntity);

    void removeTrain(TrainEntity trainEntity);
}
