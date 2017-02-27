package api.controllers;

import api.model.UserBean;
import api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserBean home(UserBean userBean) {
        userService.addUser(userBean);
        return userBean;
    }

    @ControllerAdvice
    public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
        public JsonpAdvice() {
            super("callback");
        }
    }
}
