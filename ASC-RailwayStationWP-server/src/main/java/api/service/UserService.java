package api.service;

import api.model.UserBean;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService{

    void bookTicket(UserBean user);

    void authenticate(String login, String password) throws Exception;

    void registration(UserBean user);

    UserBean getAuthorizeUser();

}
