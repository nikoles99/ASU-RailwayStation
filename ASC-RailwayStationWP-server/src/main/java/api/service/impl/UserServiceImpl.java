package api.service.impl;

import api.authentication.UserAuthentication;
import api.convertors.UserConverter;
import api.dao.UserDao;
import api.entity.RoleEntity;
import api.entity.UserEntity;
import api.model.UserBean;
import api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    public void bookTicket(UserBean user) {
        UserEntity userEntity = userConverter.toEntity(user);
        if (user.getTickets().isEmpty()) {
            throw new IllegalStateException("Tickets must be exist");
        }
        userDao.update(userEntity);
    }

    @Override
    public void authentication(String login, String password) {
        UserEntity user = userDao.getByLogin(login);
        Authentication auth = new UserAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    public void registration(UserBean user) {
        UserEntity userEntity = userConverter.toEntity(user);
        RoleEntity role = new RoleEntity();
        userEntity.setRoles(Collections.singletonList(role));
        userDao.add(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userDao.getByLogin(userName);
    }
}
