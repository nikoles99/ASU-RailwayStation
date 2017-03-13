package api.service.train;

import api.convertors.TrainConverter;
import api.dao.train.TrainDao;
import api.entity.TrainEntity;
import api.model.TrainBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TrainConverter trainConverter;

    @Override
    public void addTrain() {

    }

    @Override
    public void addTrainRoute(TrainBean trainBean) {
        TrainEntity trainEntity = trainConverter.convertToEntity(trainBean);
        trainDao.addTrain(trainEntity);
    }
}
