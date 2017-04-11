package api.service.impl;

import api.convertors.UserConverter;
import api.dao.UserDao;
import api.entity.RoleEntity;
import api.entity.UserEntity;
import api.model.UserBean;
import api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    @Override
    public void add(UserBean user) {
        UserEntity userEntity = userConverter.toEntity(user);
        userDao.add(userEntity);
    }

    @Override
    public void bookTicket(UserBean user) {
        UserEntity userEntity = userConverter.toEntity(user);
        if (user.getTickets().isEmpty()) {
            throw new IllegalStateException("Tickets must be exist");
        }
        userDao.update(userEntity);
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = userDao.getByLogin(login);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        RoleEntity role = user.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        return new User(user.getName(), user.getPassword(), grantedAuthorities);
    }
}
