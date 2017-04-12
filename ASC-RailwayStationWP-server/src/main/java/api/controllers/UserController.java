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

    @RequestMapping(value = "/login")
    @ResponseBody
    void addUser() {
        UserBean user = new UserBean();
        user.setName("Nikita");
        user.setLastName("Olesiuk");
        user.setPasportNumber("KH123455");
        user.setEmail("nikido@tut.by");
        user.setLogin("admin");
        user.setPassword("admin");
        userService.registration(user);
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    void getCarriageById(@RequestParam("login") String login, @RequestParam("password") String password) {
        userService.authentication(login, password);
    }

    @RequestMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    void registration(@RequestBody UserBean user) {
        userService.registration(user);
    }
}
