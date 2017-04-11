package api.controllers;

import api.model.UserBean;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    UserBean addUser(@RequestBody final UserBean user) {
        userService.add(user);
        return user;
    }

    @RequestMapping(value = "/bookTicket", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    void bookTicket(@RequestBody UserBean user) {
        userService.bookTicket(user);
    }
}
