package api.convertors;

import api.entity.StationEntity;
import api.entity.ScheduleEntity;
import api.model.StationBean;
import api.model.ScheduleBean;
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
    public StationBean toBean(StationEntity stationEntity) {
        StationBean stationBean = new StationBean();
        stationBean.setName(stationEntity.getName());
        stationBean.setId(stationEntity.getId());
        List<ScheduleEntity> schedules = stationEntity.getSchedules();
        stationBean.setSchedules(scheduleConverter.toBeanCollection(schedules));
        return stationBean;
    }

    @Override
    public StationEntity toEntity(StationBean stationBean) {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setName(stationBean.getName());
        stationEntity.setId(stationBean.getId());
        List<ScheduleBean> schedules = stationBean.getSchedules();
        stationEntity.setSchedules(scheduleConverter.toEntityCollection(schedules));
        return stationEntity;
    }
}
