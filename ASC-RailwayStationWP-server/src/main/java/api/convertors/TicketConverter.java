package api.convertors;

import api.dao.CarriageDao;
import api.dao.PlaceDao;
import api.dao.UserDao;
import api.entity.CarriageEntity;
import api.entity.PlaceEntity;
import api.entity.TicketEntity;
import api.entity.UserEntity;
import api.model.TicketBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nikita on 26.03.17.
 */
@Component
public class TicketConverter extends AbstractConvertor<TicketBean, TicketEntity> {

    @Autowired
    private PlaceDao placeDao;

    @Override
    public TicketBean toBean(TicketEntity entity) {
        TicketBean bean = new TicketBean();
        bean.setId(entity.getId());
        PlaceEntity place = entity.getPlace();
        bean.setPlaceId(place.getId());
        bean.setPlaceNumber(place.getNumber());
        bean.setCarriageNumber(place.getCarriage().getNumber());
        bean.setCarriageType(place.getCarriage().getType());
        bean.setArrivalDate(entity.getArrivalDate());
        bean.setArrivalStation(entity.getArrivalStation());
        bean.setDepartureDate(entity.getDepartureDate());
        bean.setDepartureStation(entity.getDepartureStation());
        bean.setPrice(entity.getPrice());
        return bean;
    }

    @Override
    public TicketEntity toEntity(TicketBean bean) {
        TicketEntity entity = new TicketEntity();
        entity.setId(bean.getId());
        PlaceEntity place = placeDao.get(bean.getPlaceId());
        entity.setPlace(place);
        entity.setArrivalDate(bean.getArrivalDate());
        entity.setArrivalStation(bean.getArrivalStation());
        entity.setDepartureDate(bean.getDepartureDate());
        entity.setDepartureStation(bean.getDepartureStation());
        entity.setPrice(bean.getPrice());
        return entity;
    }
}
