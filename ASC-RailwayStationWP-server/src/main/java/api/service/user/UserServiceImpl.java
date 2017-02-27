package api.service.user;

import api.model.UserBean;
import api.convertors.UserConverter;
import api.dao.user.UserDao;
import api.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    @Override
    public void addUser(UserBean userBean) {
        UserEntity userEntity = userConverter.convertToEntity(userBean);
        userDao.addUser(userEntity);
        logger.info("success");
    }
}
