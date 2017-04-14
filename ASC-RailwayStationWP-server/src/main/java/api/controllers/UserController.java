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

    @Autowired
    private UserUtils userUtils;

    @RequestMapping(value = "/bookTicket", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void bookTicket(@RequestBody UserBean user) {
        userService.bookTicket(user);
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    void authentication(@RequestParam("login") String login, @RequestParam("password") String password) throws Exception {
        try {
            userUtils.authenticate(login, password);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    void registration(UserBean user) {
        userService.registration(user);
    }

    @RequestMapping(value = "/isAuthenticated", method = RequestMethod.POST)
    @ResponseBody
    Boolean isAuthenticated() {
        return userUtils.isAuthenticated();
    }
}
