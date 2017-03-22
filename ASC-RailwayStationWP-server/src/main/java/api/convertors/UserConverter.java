package api.convertors;

import api.model.UserBean;
import api.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConvertor<UserBean, UserEntity> {

    public UserBean toBean(UserEntity entity) {
        UserBean userBean = new UserBean();
        userBean.setLogin(entity.getLogin());
        userBean.setPassword(entity.getPassword());
        return userBean;
    }

    public UserEntity toEntity(UserBean bean) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(bean.getLogin());
        userEntity.setPassword(bean.getPassword());
        return userEntity;
    }
}
