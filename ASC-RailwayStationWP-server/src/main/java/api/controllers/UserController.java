package api.controllers;

import api.model.SimpleResponseBean;
import api.model.UserBean;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(value = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/bookTicket", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void bookTicket(@RequestBody UserBean user) {
        userService.bookTicket(user);
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    void authentication(@RequestParam("login") String login, @RequestParam("password") String password) throws Exception {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
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
    SimpleResponseBean isAuthenticated() {
        Boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        SimpleResponseBean simpleResponseBean = new SimpleResponseBean(authenticated.toString());
        return simpleResponseBean;
    }
}
