package api.convertors;

import api.dao.TrainDao;
import api.entity.CarriageEntity;
import api.entity.PlaceEntity;
import api.entity.TrainEntity;
import api.model.CarriageBean;
import api.model.PlaceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Component
public class CarriageConverter extends AbstractConvertor<CarriageBean, CarriageEntity> {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private PlaceConverter placeConvertor;

    @Override
    public CarriageBean convertToBean(CarriageEntity carriageEntity) {
        CarriageBean carriageBean = new CarriageBean();
        carriageBean.setNumber(carriageEntity.getNumber());
        carriageBean.setId(carriageEntity.getId());
        carriageBean.setCarriageType(carriageEntity.getCarriageType());
        List<PlaceBean> placeBeans = placeConvertor.convertToBeanCollection(carriageEntity.getPlaces());
        carriageBean.setPlaces(placeBeans);
        carriageBean.setTrainId(carriageEntity.getTrainEntity().getId());
        return carriageBean;
    }

    @Override
    public CarriageEntity convertToEntity(CarriageBean carriageBean) {
        CarriageEntity carriageEntity = new CarriageEntity();
        carriageEntity.setNumber(carriageBean.getNumber());
        carriageEntity.setId(carriageBean.getId());
        carriageEntity.setCarriageType(carriageBean.getCarriageType());
        List<PlaceEntity> placeEntities = placeConvertor.convertToEntityCollection(carriageBean.getPlaces());
        carriageEntity.setPlaces(placeEntities);
        TrainEntity train = trainDao.getTrain(carriageBean.getTrainId());
        carriageEntity.setTrainEntity(train);
        return carriageEntity;
    }
}
