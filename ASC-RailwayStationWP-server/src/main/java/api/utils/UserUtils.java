package api.utils;

import api.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by nikita on 14.04.17.
 */
public class UserUtils {

    public static Boolean isAuthenticated() {
        Object principal = getAuthentication().getPrincipal();
        return principal instanceof UserEntity;
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static UserEntity getUser() {
        Boolean authenticated = isAuthenticated();
        if (authenticated) {
            return (UserEntity) getAuthentication().getPrincipal();
        }
        return null;
    }
}
