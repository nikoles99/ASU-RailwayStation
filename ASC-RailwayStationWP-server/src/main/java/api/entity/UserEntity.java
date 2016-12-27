package api.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity{

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    public UserEntity() {

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
