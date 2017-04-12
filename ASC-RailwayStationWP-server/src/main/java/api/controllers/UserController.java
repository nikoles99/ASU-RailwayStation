package api.controllers;

import api.model.UserBean;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/bookTicket", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void bookTicket(@RequestBody UserBean user) {
        userService.bookTicket(user);
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    void authentication(@RequestParam("login") String login, @RequestParam("password") String password) {
        userService.authentication(login, password);
    }

    @RequestMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void registration(@RequestBody UserBean user) {
        userService.registration(user);
    }
}
