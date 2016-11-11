import java.util.Calendar;
import java.util.Date;

public class TimeParser {
    private static LocaleBundle locale = new LocaleBundle();

    public static final int MORNING_HOURS = 6;
    public static final int DAY_HOURS = 9;
    public static final int EVENING_HOURS = 19;
    public static final int NIGHT_HOURS = 23;

    private TimeParser() {

    }

    /***
     * Function returns greeting message based on given time.
     *
     * morning message: 06:00 - 08:59.
     * day message: 09:00 - 18:59.
     * evening message: 19:00 - 22:53.
     * night message: 23:00 - 05:59.
     *
     * @param date current date
     * @return greeting message based on date-time
     */

    public static String getGreeting(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);

        if (hours >= MORNING_HOURS && hours < DAY_HOURS) {
            return locale.getMessage("greet.morning");
        } else if (hours >= DAY_HOURS && hours < EVENING_HOURS) {
            return locale.getMessage("greet.day");
        } else if (hours >= EVENING_HOURS && hours < NIGHT_HOURS) {
            return locale.getMessage("greet.evening");
        } else return locale.getMessage("greet.night");
    }

}
