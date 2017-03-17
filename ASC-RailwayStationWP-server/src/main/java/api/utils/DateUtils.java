package api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nolesuk on 17-Mar-17.
 */
public class DateUtils {

    private static final String MASK = "yyyy-MM-dd HH:mm:ss";

    public static Date format(String date) {
        try {
            return getSimpleDateFormat().parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Invalid date format %s expected '" + MASK + "'", date), e);
        }
    }

    public static String format(Date date) {
        return getSimpleDateFormat().format(date);
    }

    private static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat(MASK);
    }
}
