package api.convertors;

import api.dao.OrderDao;
import api.dao.StationDao;
import api.dao.TrainDao;
import api.entity.OrderEntity;
import api.entity.StationEntity;
import api.entity.TrainEntity;
import api.entity.ScheduleEntity;
import api.model.TrainScheduleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nolesuk on 13-Mar-17.
 */

@Component
public class ScheduleConverter extends AbstractConvertor<TrainScheduleBean, ScheduleEntity> {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private StationDao stationDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public TrainScheduleBean toBean(ScheduleEntity entity) {
        TrainScheduleBean bean = new TrainScheduleBean();
        bean.setArrivalDate(entity.getArrivalDate());
        bean.setId(entity.getId());
        bean.setDepartureDate(entity.getDepartureDate());
        bean.setStationId(entity.getStation().getId());
        bean.setTrainId(entity.getTrain().getId());
        OrderEntity orderEntity = entity.getOrder();
        if (orderEntity != null) {
            bean.setOrderId(orderEntity.getId());
        }
        return bean;
    }

    @Override
    public ScheduleEntity toEntity(TrainScheduleBean bean) {
        ScheduleEntity entity = new ScheduleEntity();
        entity.setArrivalDate(bean.getArrivalDate());
        entity.setId(bean.getId());
        entity.setDepartureDate(bean.getDepartureDate());
        StationEntity stationEntity = stationDao.getById(bean.getStationId());
        entity.setStation(stationEntity);
        TrainEntity train = trainDao.get(bean.getTrainId());
        entity.setTrain(train);
        OrderEntity order = orderDao.getOrder(bean.getOrderId());
        entity.setOrder(order);
        return entity;
    }
}
