package api.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UserBean extends AbstractBean {

    @NotNull
    private String login;

    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserBean userBean = (UserBean) o;

        if (login != null ? !login.equals(userBean.login) : userBean.login != null) return false;
        return password != null ? password.equals(userBean.password) : userBean.password == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
