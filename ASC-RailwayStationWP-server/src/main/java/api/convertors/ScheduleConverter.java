package api.convertors;

import api.dao.order.OrderDao;
import api.dao.station.StationDao;
import api.dao.train.TrainDao;
import api.entity.OrderEntity;
import api.entity.StationEntity;
import api.entity.TrainEntity;
import api.entity.TrainScheduleEntity;
import api.model.TrainScheduleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nolesuk on 13-Mar-17.
 */

@Component
public class ScheduleConverter {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private StationDao stationDao;

    @Autowired
    private OrderDao orderDao;

    public TrainScheduleBean convertToBean(TrainScheduleEntity entity) {
        TrainScheduleBean bean = new TrainScheduleBean();
        bean.setArrivalDate(entity.getArrivalDate());
        bean.setId(entity.getId());
        bean.setDepartureDate(entity.getDepartureDate());
        bean.setStationId(entity.getStationEntity().getId());
        bean.setTrainId(entity.getTrainEntity().getId());
        bean.setOrderId(entity.getOrderEntity().getId());
        return bean;
    }

    public TrainScheduleEntity convertToEntity(TrainScheduleBean bean) {
        TrainScheduleEntity entity = new TrainScheduleEntity();
        entity.setArrivalDate(bean.getArrivalDate());
        entity.setId(bean.getId());
        entity.setDepartureDate(bean.getDepartureDate());
        StationEntity stationEntity = stationDao.getStationById(bean.getStationId());
        entity.setStationEntity(stationEntity);
        TrainEntity train = trainDao.getTrain(bean.getTrainId());
        entity.setTrainEntity(train);
        OrderEntity order = orderDao.getOrder(bean.getOrderId());
        entity.setOrderEntity(order);
        return entity;
    }

    public Set<TrainScheduleEntity> convertToEntity(Set<TrainScheduleBean> beans) {
        Set<TrainScheduleEntity> entities = new HashSet<TrainScheduleEntity>();
        for (TrainScheduleBean bean : beans) {
            TrainScheduleEntity entity = convertToEntity(bean);
            entities.add(entity);
        }
        return entities;
    }

    public Set<TrainScheduleBean> convertToBean(Set<TrainScheduleEntity> entities) {
        Set<TrainScheduleBean> beans = new HashSet<TrainScheduleBean>();
        for (TrainScheduleEntity entity : entities) {
            TrainScheduleBean bean = convertToBean(entity);
            beans.add(bean);
        }
        return beans;
    }
}
