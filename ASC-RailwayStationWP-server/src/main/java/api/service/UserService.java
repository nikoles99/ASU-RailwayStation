package api.service;

import api.model.UserBean;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void add(UserBean user);

    void bookTicket(UserBean user);
}
