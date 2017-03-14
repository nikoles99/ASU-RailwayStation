package api.service.train;

import api.convertors.TrainConverter;
import api.dao.schedule.TrainScheduleDao;
import api.dao.station.StationDao;
import api.dao.train.TrainDao;
import api.entity.StationEntity;
import api.entity.TrainEntity;
import api.model.TrainBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public void getTrainByRoute(String route) {

    }

    @Override
    public void getTrainByArrivalStation(StationEntity stationEntity) {

    }

    @Override
    public void getTrainByDepartureStation(StationEntity stationEntity) {

    }

    @Override
    public void getTrainByArrivalDate(Date arrivalDate) {

    }

    @Override
    public void getTrainByDepartureDate(Date departureDate) {

    }
}
