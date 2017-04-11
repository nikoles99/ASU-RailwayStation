package api.convertors;

import api.dao.RoleDao;
import api.entity.RoleEntity;
import api.entity.TicketEntity;
import api.model.TicketBean;
import api.model.UserBean;
import api.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter extends AbstractConvertor<UserBean, UserEntity> {

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private RoleDao roleDao;

    public UserBean toBean(UserEntity entity) {
        UserBean userBean = new UserBean();
        userBean.setLogin(entity.getLogin());
        userBean.setPassword(entity.getPassword());
        List<TicketBean> tickets = ticketConverter.toBeanCollection(entity.getTickets());
        userBean.setTickets(tickets);
        userBean.setName(entity.getName());
        userBean.setAddress(entity.getAddress());
        userBean.setCountry(entity.getCountry());
        userBean.setEmail(entity.getEmail());
        userBean.setLastName(entity.getLastName());
        userBean.setMiddleName(entity.getMiddleName());
        userBean.setId(entity.getId());
        userBean.setRoleId(entity.getRole().getId());
        return userBean;
    }

    public UserEntity toEntity(UserBean bean) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(bean.getLogin());
        userEntity.setPassword(bean.getPassword());
        List<TicketEntity> tickets = ticketConverter.toEntityCollection(bean.getTickets());
        userEntity.setTickets(tickets);
        userEntity.setName(bean.getName());
        userEntity.setAddress(bean.getAddress());
        userEntity.setCountry(bean.getCountry());
        userEntity.setEmail(bean.getEmail());
        userEntity.setLastName(bean.getLastName());
        userEntity.setMiddleName(bean.getMiddleName());
        userEntity.setId(bean.getId());
        Integer roleId = bean.getRoleId();
        RoleEntity role = roleDao.getById(roleId);
        userEntity.setRole(role);
        return userEntity;
    }
}
