package api.service.train;

import api.convertors.TrainConverter;
import api.dao.schedule.TrainScheduleDao;
import api.dao.station.StationDao;
import api.dao.train.TrainDao;
import api.entity.TrainEntity;
import api.model.TrainBean;
import api.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TrainConverter trainConverter;

    @Autowired
    private StationDao stationDao;

    @Autowired
    private TrainScheduleDao trainScheduleDao;

    @Override
    public void addTrain() {

    }

    @Override
    public void addTrainRoute(TrainBean trainBean) {
        TrainEntity trainEntity = trainConverter.convertToEntity(trainBean);
        trainDao.addTrain(trainEntity);
    }

    @Override
    public List<TrainBean> getTrainsByRoute(String arrivalStation, String departureStation) {
        if (ValidationUtils.isNotNull(arrivalStation, departureStation)) {
            List<TrainEntity> trainsEntities = trainDao.getTrainByRoute(arrivalStation, departureStation);
            return trainConverter.convertToBeanCollection(trainsEntities);
        }
        return null;
    }

    @Override
    public List<TrainBean> getTrainsByParams(String arrivalStation, String departureStation, String arrivalDate, String departureDate) {
        if (ValidationUtils.isNotNull(arrivalStation, departureStation, arrivalDate, departureDate)) {
            List<TrainEntity> trainsEntities = trainDao.getTrainsByParams(arrivalStation, departureStation, arrivalDate, departureDate);
            return trainConverter.convertToBeanCollection(trainsEntities);
        }
        return null;
    }
}
