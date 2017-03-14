package api.dao.schedule;

import api.dao.AbstractDao;
import api.entity.TrainScheduleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikita on 14.03.17.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Repository
public class TrainScheduleDaoImpl extends AbstractDao<TrainScheduleEntity> implements TrainScheduleDao {

    @Override
    public void addTrainSchedule(TrainScheduleEntity trainScheduleEntity) {

    }

    @Override
    public TrainScheduleEntity getTrainSchedule(Integer id) {
        return null;
    }

    @Override
    public void updateTrainSchedule(TrainScheduleEntity trainScheduleEntity) {

    }

    @Override
    public void removeTrainSchedule(TrainScheduleEntity trainScheduleEntity) {

    }
}
