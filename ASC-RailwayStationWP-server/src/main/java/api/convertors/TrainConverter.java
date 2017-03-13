package api.convertors;

import api.entity.CarriageEntity;
import api.entity.TrainEntity;
import api.entity.TrainScheduleEntity;
import api.model.CarriageBean;
import api.model.TrainBean;
import api.model.TrainScheduleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by nolesuk on 13-Mar-17.
 */

@Component
public class TrainConverter {

    @Autowired
    private CarriageConverter carriageConverter;

    @Autowired
    private ScheduleConverter scheduleConverter;

    public TrainBean convertToBean(TrainEntity trainEntity) {
        TrainBean trainBean = new TrainBean();
        trainBean.setNumber(trainEntity.getNumber());
        trainBean.setId(trainEntity.getId());
        Set<CarriageEntity> carriages = trainEntity.getCarriages();
        trainBean.setCarriages(carriageConverter.convertToBean(carriages));
        Set<TrainScheduleEntity> scheduleEntities = trainEntity.getScheduleEntities();
        trainBean.setSchedules(scheduleConverter.convertToBean(scheduleEntities));
        return trainBean;
    }

    public TrainEntity convertToEntity(TrainBean trainBean) {
        TrainEntity trainEntity = new TrainEntity();
        trainEntity.setNumber(trainBean.getNumber());
        trainEntity.setId(trainBean.getId());
        Set<CarriageBean> carriages = trainBean.getCarriages();
        trainEntity.setCarriages(carriageConverter.convertToEntity(carriages));
        Set<TrainScheduleBean> schedules = trainBean.getSchedules();
        trainEntity.setScheduleEntities(scheduleConverter.convertToEntity(schedules));
        return trainEntity;
    }

    public List<TrainEntity> convertToEntity(List<TrainBean> beans) {
        List<TrainEntity> entities = new ArrayList<TrainEntity>();
        for (TrainBean bean : beans) {
            TrainEntity stationEntity = convertToEntity(bean);
            entities.add(stationEntity);
        }
        return entities;
    }

    public List<TrainBean> convertToBean(List<TrainEntity> entities) {
        List<TrainBean> beans = new ArrayList<TrainBean>();
        for (TrainEntity entity : entities) {
            TrainBean trainBean = convertToBean(entity);
            beans.add(trainBean);
        }
        return beans;
    }
}
