package api.dao.schedule;

import api.entity.TrainScheduleEntity;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface TrainScheduleDao {

    void addTrainSchedule(TrainScheduleEntity trainScheduleEntity);

    TrainScheduleEntity getTrainSchedule(Integer id);

    void updateTrainSchedule(TrainScheduleEntity trainScheduleEntity);

    void removeTrainSchedule(TrainScheduleEntity trainScheduleEntity);
}
