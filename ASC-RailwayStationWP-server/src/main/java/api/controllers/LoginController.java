package api.controllers;

import api.bean.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * Created by nolesuk on 23-Dec-16.
 */
@Controller
@EnableAutoConfiguration
public class LoginController {

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    User home(User user1) {
        User user = new User();
        user.setLogin(user1.getLogin());
        user.setPassword(user1.getPassword());
        return user;
    }

    @ControllerAdvice
    public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
        public JsonpAdvice() {
            super("callback");
        }
    }
}
