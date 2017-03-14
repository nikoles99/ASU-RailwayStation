package api.convertors;

import api.model.UserBean;
import api.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConvertor<UserBean, UserEntity> {

    public UserBean convertToBean(UserEntity userEntity) {
        UserBean userBean = new UserBean();
        userBean.setLogin(userEntity.getLogin());
        userBean.setPassword(userEntity.getPassword());
        return userBean;
    }

    public UserEntity convertToEntity(UserBean userBean) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userBean.getLogin());
        userEntity.setPassword(userBean.getPassword());
        return userEntity;
    }
}
