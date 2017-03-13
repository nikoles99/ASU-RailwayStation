package api.convertors;

import api.dao.train.TrainDao;
import api.entity.CarriageEntity;
import api.entity.PlaceEntity;
import api.entity.TrainEntity;
import api.model.CarriageBean;
import api.model.PlaceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Component
public class CarriageConverter {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private PlaceConverter placeConvertor;

    public CarriageBean convertToBean(CarriageEntity carriageEntity) {
        CarriageBean carriageBean = new CarriageBean();
        carriageBean.setNumber(carriageEntity.getNumber());
        carriageBean.setId(carriageEntity.getId());
        carriageBean.setCarriageType(carriageEntity.getCarriageType());
        Set<PlaceBean> placeBeans = placeConvertor.convertToBean(carriageEntity.getPlaces());
        carriageBean.setPlaces(placeBeans);
        carriageBean.setTrainId(carriageEntity.getTrainEntity().getId());
        return carriageBean;
    }

    public CarriageEntity convertToEntity(CarriageBean carriageBean) {
        CarriageEntity carriageEntity = new CarriageEntity();
        carriageEntity.setNumber(carriageBean.getNumber());
        carriageEntity.setId(carriageBean.getId());
        carriageEntity.setCarriageType(carriageBean.getCarriageType());
        Set<PlaceEntity> placeEntities = placeConvertor.convertToEntity(carriageBean.getPlaces());
        carriageEntity.setPlaces(placeEntities);
        TrainEntity train = trainDao.getTrain(carriageBean.getTrainId());
        carriageEntity.setTrainEntity(train);
        return carriageEntity;
    }

    public Set<CarriageEntity> convertToEntity(Set<CarriageBean> beans) {
        Set<CarriageEntity> entities = new HashSet<CarriageEntity>();
        for (CarriageBean bean : beans) {
            CarriageEntity carriageEntity = convertToEntity(bean);
            entities.add(carriageEntity);
        }
        return entities;
    }

    public Set<CarriageBean> convertToBean(Set<CarriageEntity> entities) {
        Set<CarriageBean> beans = new HashSet<CarriageBean>();
        for (CarriageEntity entity : entities) {
            CarriageBean carriageBean = convertToBean(entity);
            beans.add(carriageBean);
        }
        return beans;
    }

}
