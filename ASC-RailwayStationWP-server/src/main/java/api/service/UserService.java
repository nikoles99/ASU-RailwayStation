package api.service;

import api.model.UserBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService{

    void authenticate(String login, String password) throws Exception;

    void registration(UserBean user);

    UserBean getAuthorizeUser();

}
