package api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static Date addMinutes(Date date, Integer minutes) {
        final long ONE_MINUTE_IN_MILLIS = 60000;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long time = calendar.getTimeInMillis();
        return new Date(time + (minutes * ONE_MINUTE_IN_MILLIS));
    }

    public static Date takeAwayMinutes(Date date, Integer minutes) {
        final long ONE_MINUTE_IN_MILLIS = 60000;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long time = calendar.getTimeInMillis();
        return new Date(time - (minutes * ONE_MINUTE_IN_MILLIS));
    }
}
