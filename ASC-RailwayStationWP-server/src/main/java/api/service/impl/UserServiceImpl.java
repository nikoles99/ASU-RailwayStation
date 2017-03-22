package api.service.impl;

import api.model.UserBean;
import api.convertors.UserConverter;
import api.dao.UserDao;
import api.entity.UserEntity;
import api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    @Override
    public void add(UserBean user) {
        UserEntity userEntity = userConverter.toEntity(user);
        userDao.add(userEntity);
        logger.info("success");
    }
}
