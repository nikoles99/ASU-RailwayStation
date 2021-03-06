package api.controllers;

import api.model.UserBean;
import api.service.UserService;
import api.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    void authentication(@RequestParam("login") String login, @RequestParam("password") String password) throws Exception {
        userService.authenticate(login, password);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    void registration(@RequestBody UserBean user) {
        userService.registration(user);
    }

    @RequestMapping(value = "/isAuthenticated", method = RequestMethod.POST)
    @ResponseBody
    UserBean isAuthenticated() {
        return userService.getAuthorizeUser();
    }
}
