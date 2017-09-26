package api.convertors;

import api.dao.StationDao;
import api.dao.TrainDao;
import api.entity.ScheduleEntity;
import api.entity.StationEntity;
import api.entity.TrainEntity;
import api.model.ScheduleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nolesuk on 13-Mar-17.
 */

@Component
public class ScheduleConverter extends AbstractConvertor<ScheduleBean, ScheduleEntity> {

  @Autowired
  private TrainDao trainDao;

  @Autowired
  private StationDao stationDao;

  @Override
  public ScheduleBean toBean(ScheduleEntity entity) {
    ScheduleBean bean = new ScheduleBean();
    bean.setArrivalDate(entity.getArrivalDate());
    bean.setId(entity.getId());
    bean.setDepartureDate(entity.getDepartureDate());
    bean.setStationId(entity.getStation().getId());
    bean.setTrainId(entity.getTrain().getId());
    return bean;
  }

  @Override
  public ScheduleEntity toEntity(ScheduleBean bean) {
    ScheduleEntity entity = new ScheduleEntity();
    entity.setArrivalDate(bean.getArrivalDate());
    entity.setId(bean.getId());
    entity.setDepartureDate(bean.getDepartureDate());
    StationEntity stationEntity = stationDao.getById(bean.getStationId());
    entity.setStation(stationEntity);
    TrainEntity train = trainDao.get(bean.getTrainId());
    entity.setTrain(train);
    return entity;
  }
}
