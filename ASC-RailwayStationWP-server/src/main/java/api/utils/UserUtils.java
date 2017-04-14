package api.utils;

import api.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by nikita on 14.04.17.
 */
@Component
public class UserUtils {

    @Autowired
    @Qualifier(value = "authenticationManager")
    private AuthenticationManager authenticationManager;

    public Boolean isAuthenticated() {
        Object principal = getAuthentication().getPrincipal();
        return principal instanceof UserEntity;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void authenticate(String login, String password) throws Exception {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public UserEntity getUser() {
        Boolean authenticated = isAuthenticated();
        if (authenticated) {
            return (UserEntity) getAuthentication().getPrincipal();
        }
        return null;
    }
}
