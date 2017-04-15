package api.convertors;

import api.entity.TicketEntity;
import api.entity.UserEntity;
import api.model.TicketBean;
import api.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter extends AbstractConvertor<UserBean, UserEntity> {

    @Autowired
    private TicketConverter ticketConverter;

    public UserBean toBean(UserEntity entity) {
        UserBean bean = new UserBean();
        bean.setLogin(entity.getLogin());
        bean.setPassword(entity.getPassword());
        bean.setEmail(entity.getEmail());
        bean.setName(entity.getName());
        bean.setLastName(entity.getLastName());
        bean.setId(entity.getId());
        return bean;
    }

    public UserEntity toEntity(UserBean bean) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(bean.getLogin());
        userEntity.setPassword(bean.getPassword());
        userEntity.setName(bean.getName());
        userEntity.setLastName(bean.getLastName());
        userEntity.setEmail(bean.getEmail());
        List<TicketEntity> tickets = ticketConverter.toEntityCollection(bean.getTickets());
        userEntity.setTickets(tickets);
        return userEntity;
    }
}
