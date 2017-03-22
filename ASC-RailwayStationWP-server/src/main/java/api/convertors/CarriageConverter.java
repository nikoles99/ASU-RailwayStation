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
    private PlaceConverter placeConverter;

    @Override
    public CarriageBean toBean(CarriageEntity carriageEntity) {
        CarriageBean carriageBean = new CarriageBean();
        carriageBean.setNumber(carriageEntity.getNumber());
        carriageBean.setId(carriageEntity.getId());
        carriageBean.setCarriageType(carriageEntity.getType());
        List<PlaceBean> placeBeans = placeConverter.toBeanCollection(carriageEntity.getPlaces());
        carriageBean.setPlaces(placeBeans);
        carriageBean.setTrainId(carriageEntity.getTrain().getId());
        return carriageBean;
    }

    @Override
    public CarriageEntity toEntity(CarriageBean carriageBean) {
        CarriageEntity carriageEntity = new CarriageEntity();
        carriageEntity.setNumber(carriageBean.getNumber());
        carriageEntity.setId(carriageBean.getId());
        carriageEntity.setType(carriageBean.getCarriageType());
        List<PlaceEntity> placeEntities = placeConverter.toEntityCollection(carriageBean.getPlaces());
        carriageEntity.setPlaces(placeEntities);
        TrainEntity train = trainDao.get(carriageBean.getTrainId());
        carriageEntity.setTrain(train);
        return carriageEntity;
    }
}
