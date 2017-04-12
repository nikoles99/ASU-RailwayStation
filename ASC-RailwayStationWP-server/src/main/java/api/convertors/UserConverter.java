package api.convertors;

import api.entity.TicketEntity;
import api.entity.UserEntity;
import api.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter extends AbstractConvertor<UserBean, UserEntity> {

    @Autowired
    private TicketConverter ticketConverter;

    public UserBean toBean(UserEntity entity) {
        return null;
    }

    public UserEntity toEntity(UserBean bean) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(bean.getLogin());
        userEntity.setPassword(bean.getPassword());
        userEntity.setName(bean.getName());
        userEntity.setLastName(bean.getLastName());
        userEntity.setPassportNumber(bean.getPasportNumber());
        userEntity.setEmail(bean.getEmail());
        List<TicketEntity> tickets = ticketConverter.toEntityCollection(bean.getTickets());
        userEntity.setTickets(tickets);
        return userEntity;
    }
}
