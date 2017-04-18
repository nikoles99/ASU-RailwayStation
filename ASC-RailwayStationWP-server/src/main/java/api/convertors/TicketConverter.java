package api.convertors;

import api.dao.CarriageDao;
import api.dao.UserDao;
import api.entity.CarriageEntity;
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
    private UserDao userDao;

    @Autowired
    private CarriageDao carriageDao;

    @Override
    public TicketBean toBean(TicketEntity entity) {
        TicketBean bean = new TicketBean();
        bean.setId(entity.getId());
        bean.setPlaceId(entity.getPlaceId());
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
        entity.setTrainId(bean.getId());
        entity.setPlaceId(bean.getPlaceId());
        entity.setArrivalDate(bean.getArrivalDate());
        entity.setArrivalStation(bean.getArrivalStation());
        entity.setDepartureDate(bean.getDepartureDate());
        entity.setDepartureStation(bean.getDepartureStation());
        entity.setPrice(bean.getPrice());
        return entity;
    }
}
