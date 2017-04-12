package api.service;

import api.model.UserBean;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void bookTicket(UserBean user);

    void authentication(String login, String password);

    void registration(UserBean user);
}
