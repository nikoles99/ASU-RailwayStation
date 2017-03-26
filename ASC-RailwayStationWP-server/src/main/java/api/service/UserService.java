package api.service;

import api.model.UserBean;

public interface UserService {
    void add(UserBean user);

    void bookTicket(UserBean user);
}
