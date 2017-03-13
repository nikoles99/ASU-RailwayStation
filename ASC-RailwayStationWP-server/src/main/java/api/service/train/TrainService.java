package api.service.train;

import api.model.TrainBean;

/**
 * Created by nolesuk on 07-Mar-17.
 */
public interface TrainService {

    void addTrain();

    void addTrainRoute(TrainBean trainBean);

}
