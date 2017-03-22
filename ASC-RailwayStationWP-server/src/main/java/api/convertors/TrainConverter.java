package api.convertors;

import api.entity.CarriageEntity;
import api.entity.TrainEntity;
import api.entity.ScheduleEntity;
import api.model.CarriageBean;
import api.model.TrainBean;
import api.model.TrainScheduleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by nolesuk on 13-Mar-17.
 */

@Component
public class TrainConverter extends AbstractConvertor<TrainBean, TrainEntity> {

    @Autowired
    private CarriageConverter carriageConverter;

    @Autowired
    private ScheduleConverter scheduleConverter;

    @Override
    public TrainBean convertToBean(TrainEntity trainEntity) {
        TrainBean trainBean = new TrainBean();
        trainBean.setId(trainEntity.getId());
        trainBean.setName(trainEntity.getName());
        List<CarriageEntity> carriages = trainEntity.getCarriages();
        trainBean.setCarriages(carriageConverter.convertToBeanCollection(carriages));
        List<ScheduleEntity> scheduleEntities = trainEntity.getScheduleEntities();
        trainBean.setSchedules(scheduleConverter.convertToBeanCollection(scheduleEntities));
        return trainBean;
    }

    @Override
    public TrainEntity convertToEntity(TrainBean trainBean) {
        TrainEntity trainEntity = new TrainEntity();
        trainEntity.setId(trainBean.getId());
        trainEntity.setName(trainBean.getName());
        List<CarriageBean> carriages = trainBean.getCarriages();
        trainEntity.setCarriages(carriageConverter.convertToEntityCollection(carriages));
        List<TrainScheduleBean> schedules = trainBean.getSchedules();
        trainEntity.setScheduleEntities(scheduleConverter.convertToEntityCollection(schedules));
        return trainEntity;
    }

}
