package api.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserBean implements Serializable{


    private String login;

    private String password;


    public UserBean() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
