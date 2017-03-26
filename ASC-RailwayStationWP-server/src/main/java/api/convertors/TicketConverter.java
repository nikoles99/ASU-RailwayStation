package api.convertors;

import api.dao.UserDao;
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

    @Override
    public TicketBean toBean(TicketEntity entity) {
        TicketBean bean = new TicketBean();
        bean.setId(entity.getId());
        bean.setTrainId(entity.getId());
        bean.setCarriageId(entity.getCarriageId());
        bean.setPlaceId(entity.getPlaceId());
        bean.setUserId(entity.getUser().getId());
        bean.setArrivalDate(entity.getArrivalDate());
        bean.setArrivalStation(entity.getArrivalStation());
        bean.setDepartureDate(entity.getDepartureDate());
        bean.setDepartureStation(entity.getDepartureStation());
        return bean;
    }

    @Override
    public TicketEntity toEntity(TicketBean bean) {
        TicketEntity entity = new TicketEntity();
        entity.setId(bean.getId());
        entity.setTrainId(bean.getId());
        entity.setCarriageId(bean.getCarriageId());
        entity.setPlaceId(bean.getPlaceId());
        UserEntity userEntity = userDao.get(bean.getUserId());
        entity.setUser(userEntity);
        entity.setArrivalDate(bean.getArrivalDate());
        entity.setArrivalStation(bean.getArrivalStation());
        entity.setDepartureDate(bean.getDepartureDate());
        entity.setDepartureStation(bean.getDepartureStation());
        return entity;
    }
}
