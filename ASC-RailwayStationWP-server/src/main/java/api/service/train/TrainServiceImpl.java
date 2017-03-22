package api.service.train;

import api.convertors.TrainConverter;
import api.dao.schedule.TrainScheduleDao;
import api.dao.station.StationDao;
import api.dao.train.TrainDao;
import api.entity.TrainEntity;
import api.entity.ScheduleEntity;
import api.exception.TrainException;
import api.model.TrainBean;
import api.utils.DateUtils;
import api.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
    public void addTrainRoute(TrainBean trainBean) throws TrainException {
        TrainEntity trainEntity = trainConverter.convertToEntity(trainBean);
        checkCreationTrain(trainEntity);
        trainDao.addTrain(trainEntity);
    }

    private void checkCreationTrain(TrainEntity train) throws TrainException {
        for (ScheduleEntity schedule : train.getScheduleEntities()) {
            Boolean isScheduleValidate = trainScheduleDao.isScheduleValidate(schedule);
            if (!isScheduleValidate) {
                LOGGER.error("Schedule station arrival/departure time of " + schedule.getStationEntity().toString() + " is invalid");
                throw new TrainException("Такой маршрут уже существует");
            }
        }
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
    public List<TrainBean> getTrainsByParams(String arrivalStation, String departureStation, String arrivalDateStr, String departureDateStr) {
        if (ValidationUtils.isNotNull(arrivalStation, departureStation, arrivalDateStr, departureDateStr)) {
            Date arrivalDate = DateUtils.format(arrivalDateStr);
            Date departureDate = DateUtils.format(departureDateStr);
            List<TrainEntity> trainsEntities = trainDao.getTrainsByParams(arrivalStation, departureStation, arrivalDate, departureDate);
            return trainConverter.convertToBeanCollection(trainsEntities);
        }
        return null;
    }
}
