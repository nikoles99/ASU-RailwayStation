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
    public PlaceBean toBean(PlaceEntity entity) {
        PlaceBean bean = new PlaceBean();
        bean.setId(entity.getId());
        bean.setNumber(entity.getNumber());
        bean.setCarriageId(entity.getCarriage().getId());
        bean.setTrainId(entity.getTrain().getId());
        return bean;
    }

    @Override
    public PlaceEntity toEntity(PlaceBean bean) {
        PlaceEntity entity = new PlaceEntity();
        entity.setId(bean.getId());
        entity.setNumber(bean.getNumber());
        TrainEntity train = trainDao.get(bean.getTrainId());
        entity.setTrain(train);
        CarriageEntity carriage = carriageDao.get(bean.getCarriageId());
        entity.setCarriage(carriage);
        return entity;
    }

}
