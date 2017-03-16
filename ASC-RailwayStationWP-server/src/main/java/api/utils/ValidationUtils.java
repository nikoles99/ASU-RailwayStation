package api.utils;

/**
 * Created by nolesuk on 16-Mar-17.
 */
public class ValidationUtils {

    public static final Boolean isNotNull(Object... obj) {
        for (Object o : obj) {
            if (o == null) {
                return false;
            }
        }

        return true;
    }
}
