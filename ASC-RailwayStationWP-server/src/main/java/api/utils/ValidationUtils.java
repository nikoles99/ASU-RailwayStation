package api.utils;

import java.util.Collection;

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

    public static final Boolean isNotEmpty(Collection... collections) {
        for (Collection collection : collections) {
            if (collection.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public static final Boolean isNotEmpty(String... strings) {
        for (String string : strings) {
            if (string.isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
