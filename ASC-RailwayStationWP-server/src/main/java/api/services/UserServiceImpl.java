package api.services;

import api.bean.UserBean;
import api.convertors.UserConverter;
import api.dao.UserDao;
import api.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    @Override
    public void addUser(UserBean userBean) {
        UserEntity userEntity = userConverter.convertToEntity(userBean);
        userDao.addUser(userEntity);

    }
}
