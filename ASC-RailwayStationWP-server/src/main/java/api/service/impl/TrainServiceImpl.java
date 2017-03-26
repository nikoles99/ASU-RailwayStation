package api.service.impl;

import api.convertors.TrainConverter;
import api.dao.ScheduleDao;
import api.dao.StationDao;
import api.dao.TicketDao;
import api.dao.TrainDao;
import api.entity.ScheduleEntity;
import api.entity.TrainEntity;
import api.exception.TrainException;
import api.model.TrainBean;
import api.service.TrainService;
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
    private ScheduleDao scheduleDao;

    @Autowired
    private TicketDao ticketDao;

    @Override
    public void add(TrainBean train) throws TrainException {
        TrainEntity trainEntity = trainConverter.toEntity(train);
        checkCreationTrain(trainEntity);
        trainDao.add(trainEntity);
    }

    private void checkCreationTrain(TrainEntity train) throws TrainException {
        for (ScheduleEntity schedule : train.getSchedules()) {
            Boolean isScheduleValidate = scheduleDao.isValidate(schedule);
            if (!isScheduleValidate) {
                LOGGER.error("Schedule station arrival/departure time of " + schedule.getStation().toString() + " is invalid");
                throw new TrainException("Такой маршрут уже существует");
            }
        }
    }

    @Override
    public List<TrainBean> getByStations(String arrivalStation, String departureStation) {
        if (ValidationUtils.isNotNull(arrivalStation, departureStation)) {
            List<TrainEntity> trainsEntities = trainDao.getByStations(arrivalStation, departureStation);
            return trainConverter.toBeanCollection(trainsEntities);
        }
        return null;
    }

    @Override
    public List<TrainBean> getByParams(String arrivalStation, String departureStation, Date arrivalDate, Date departureDate) {
        if (ValidationUtils.isNotNull(arrivalStation, departureStation, arrivalDate, departureDate)) {
            List<TrainEntity> trainsEntities = trainDao.getByParams(arrivalStation, departureStation, arrivalDate, departureDate);
            return trainConverter.toBeanCollection(trainsEntities);
        }
        return null;
    }
}
