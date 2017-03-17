package api.dao.schedule;

import api.entity.TrainScheduleEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface TrainScheduleDao {

    void addTrainSchedule(TrainScheduleEntity trainScheduleEntity);

    TrainScheduleEntity getTrainSchedule(Integer id);

    void updateTrainSchedule(TrainScheduleEntity trainScheduleEntity);

    void removeTrainSchedule(TrainScheduleEntity trainScheduleEntity);

    Boolean isScheduleValidate(TrainScheduleEntity schedule);

    List<TrainScheduleEntity> getScheduleByParams(Date departureDate, Date arrivalDate, Integer stationId);
}
