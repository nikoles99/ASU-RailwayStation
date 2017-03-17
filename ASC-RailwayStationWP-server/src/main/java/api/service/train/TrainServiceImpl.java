package api.service.train;

import api.convertors.TrainConverter;
import api.dao.schedule.TrainScheduleDao;
import api.dao.station.StationDao;
import api.dao.train.TrainDao;
import api.entity.TrainEntity;
import api.entity.TrainScheduleEntity;
import api.exception.TrainException;
import api.model.TrainBean;
import api.utils.DateUtils;
import api.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public void addTrainRoute(TrainBean trainBean) throws TrainException {
        TrainEntity trainEntity = trainConverter.convertToEntity(trainBean);

        TrainScheduleEntity departureTrainSchedule = getFirstSchedule(trainEntity, 0);
        Date departureDate = departureTrainSchedule.getDepartureDate();
        String departureStation = departureTrainSchedule.getStationEntity().getName();

        int countSchedules = trainEntity.getScheduleEntities().size();
        TrainScheduleEntity arrivalTrainSchedule = getLastSchedule(trainEntity, countSchedules);
        Date arrivalDate = arrivalTrainSchedule.getDepartureDate();
        String arrivalStation = arrivalTrainSchedule.getStationEntity().getName();

        List<TrainEntity> trains = trainDao.getTrainsByParams(arrivalStation, departureStation, arrivalDate, departureDate);
        checkCreationNewRoute(trains, departureDate, arrivalDate);
        trainDao.addTrain(trainEntity);
    }

    private TrainScheduleEntity getLastSchedule(TrainEntity trainEntity, int countSchedules) {
        return trainEntity.getScheduleEntities().get(countSchedules - 1);
    }

    private TrainScheduleEntity getFirstSchedule(TrainEntity trainEntity, int index) {
        return trainEntity.getScheduleEntities().get(index);
    }

    private void checkCreationNewRoute(List<TrainEntity> trainsByParams, Date departureDate, Date arrivalDate) throws TrainException {
        for (TrainEntity train : trainsByParams) {
            List<TrainScheduleEntity> schedules = train.getScheduleEntities();
            for (TrainScheduleEntity schedule : schedules) {
                Boolean isScheduleValidate = trainScheduleDao.isScheduleValidate(schedule);
                if (!isScheduleValidate) {
                    throw new TrainException("Schedule station arrival/departure time" + schedule.getStationEntity().toString() + "is invalid");
                }
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
