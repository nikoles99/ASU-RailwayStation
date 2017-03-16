package api.convertors;

import api.entity.StationEntity;
import api.entity.TrainScheduleEntity;
import api.model.StationBean;
import api.model.TrainScheduleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by nikita on 27.02.17.
 */
@Component
public class StationConverter extends AbstractConvertor<StationBean, StationEntity> {

    @Autowired
    ScheduleConverter scheduleConverter;

    @Override
    public StationBean convertToBean(StationEntity stationEntity) {
        StationBean stationBean = new StationBean();
        stationBean.setName(stationEntity.getName());
        stationBean.setId(stationEntity.getId());
        List<TrainScheduleEntity> schedules = stationEntity.getSchedules();
        stationBean.setSchedules(scheduleConverter.convertToBeanCollection(schedules));
        return stationBean;
    }

    @Override
    public StationEntity convertToEntity(StationBean stationBean) {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setName(stationBean.getName());
        stationEntity.setId(stationBean.getId());
        List<TrainScheduleBean> schedules = stationBean.getSchedules();
        stationEntity.setSchedules(scheduleConverter.convertToEntityCollection(schedules));
        return stationEntity;
    }
}
