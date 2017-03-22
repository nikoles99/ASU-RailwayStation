package api.dao.schedule;

import api.entity.ScheduleEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface TrainScheduleDao {

    void addTrainSchedule(ScheduleEntity scheduleEntity);

    ScheduleEntity getTrainSchedule(Integer id);

    void updateTrainSchedule(ScheduleEntity scheduleEntity);

    void removeTrainSchedule(ScheduleEntity scheduleEntity);

    Boolean isScheduleValidate(ScheduleEntity schedule);

    List<ScheduleEntity> getScheduleByParams(Date departureDate, Date arrivalDate, Integer stationId);
}
