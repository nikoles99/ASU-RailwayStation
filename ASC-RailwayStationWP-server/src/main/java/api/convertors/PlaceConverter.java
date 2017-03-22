package api.convertors;

import api.dao.CarriageDao;
import api.dao.TrainDao;
import api.entity.CarriageEntity;
import api.entity.PlaceEntity;
import api.entity.TrainEntity;
import api.model.PlaceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Component
public class PlaceConverter extends AbstractConvertor<PlaceBean, PlaceEntity>  {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private CarriageDao carriageDao;

    @Override
    public PlaceBean convertToBean(PlaceEntity entity) {
        PlaceBean bean = new PlaceBean();
        bean.setId(entity.getId());
        bean.setNumber(entity.getNumber());
        bean.setCarriageId(entity.getCarriageEntity().getId());
        bean.setTrainId(entity.getTrainEntity().getId());
        return bean;
    }

    @Override
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

}
