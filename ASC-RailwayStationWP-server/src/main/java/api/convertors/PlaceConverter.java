package api.convertors;

import api.dao.carriage.CarriageDao;
import api.dao.train.TrainDao;
import api.entity.CarriageEntity;
import api.entity.PlaceEntity;
import api.entity.TrainEntity;
import api.model.PlaceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Component
public class PlaceConverter {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private CarriageDao carriageDao;

    public PlaceBean convertToBean(PlaceEntity entity) {
        PlaceBean bean = new PlaceBean();
        bean.setId(entity.getId());
        bean.setNumber(entity.getNumber());
        bean.setCarriageId(entity.getCarriageEntity().getId());
        bean.setTrainId(entity.getTrainEntity().getId());
        return bean;
    }

    public PlaceEntity convertToEntity(PlaceBean bean) {
        PlaceEntity entity = new PlaceEntity();
        entity.setId(bean.getId());
        entity.setNumber(bean.getNumber());
        TrainEntity train = trainDao.getTrain(bean.getTrainId());
        entity.setTrainEntity(train);
        CarriageEntity carriage = carriageDao.getCarriage(bean.getCarriageId());
        entity.setCarriageEntity(carriage);
        return entity;
    }

    public Set<PlaceEntity> convertToEntity(Set<PlaceBean> beans) {
        Set<PlaceEntity> entities = new HashSet<PlaceEntity>();
        if (beans != null) {
            for (PlaceBean bean : beans) {
                PlaceEntity entity = convertToEntity(bean);
                entities.add(entity);
            }
        }
        return entities;
    }

    public Set<PlaceBean> convertToBean(Set<PlaceEntity> entities) {
        Set<PlaceBean> beans = new HashSet<PlaceBean>();
        for (PlaceEntity entity : entities) {
            PlaceBean bean = convertToBean(entity);
            beans.add(bean);
        }
        return beans;
    }
}
