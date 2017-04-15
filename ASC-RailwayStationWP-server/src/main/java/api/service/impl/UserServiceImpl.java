package api.service.impl;

import api.convertors.UserConverter;
import api.dao.UserDao;
import api.entity.RoleEntity;
import api.entity.UserEntity;
import api.model.UserBean;
import api.service.UserService;
import api.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    @Qualifier(value = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public void bookTicket(UserBean user) {
        UserEntity userEntity = userConverter.toEntity(user);
        if (user.getTickets().isEmpty()) {
            throw new IllegalStateException("Tickets must be exist");
        }
        userDao.update(userEntity);
    }

    @Override
    public void authenticate(String login, String password) throws Exception {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void registration(UserBean user) {
        UserEntity userEntity = userConverter.toEntity(user);
        RoleEntity role = new RoleEntity();
        userEntity.setRoles(Collections.singletonList(role));
        userDao.add(userEntity);
    }

    @Override
    public UserBean getAuthorizeUser() {
        UserEntity user = UserUtils.getUser();
        if (user != null) {
            return userConverter.toBean(user);
        }
        return null;
    }
}
