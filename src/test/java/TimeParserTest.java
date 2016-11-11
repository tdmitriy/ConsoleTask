import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TimeParserTest {
    private LocaleBundle locale;

    @Before
    public void init() {
        locale = new LocaleBundle();
    }

    @Test
    public void testMorningGreeting() {
        fetchHoursRange(TimeParser.MORNING_HOURS, TimeParser.DAY_HOURS,
                locale.getMessage("greet.morning"));
    }

    @Test
    public void testDayGreeting() {
        fetchHoursRange(TimeParser.DAY_HOURS, TimeParser.EVENING_HOURS,
                locale.getMessage("greet.day"));
    }

    @Test
    public void testEveningGreeting() {
        fetchHoursRange(TimeParser.EVENING_HOURS, TimeParser.NIGHT_HOURS,
                locale.getMessage("greet.evening"));
    }

    @Test
    public void testNightGreeting() {
        final int MIDNIGHT = 24;
        final int ONE_OCLOCK = 1;

        // from 23:00 to 00:00
        fetchHoursRange(TimeParser.NIGHT_HOURS, MIDNIGHT, locale.getMessage("greet.night"));

        // from 01:00 to 05:00
        fetchHoursRange(ONE_OCLOCK, TimeParser.MORNING_HOURS, locale.getMessage("greet.night"));
    }

    private void fetchHoursRange(int from, int to, String msg) {
        Calendar calendar = Calendar.getInstance();
        for (int hours = from; hours < to; hours++) {
            calendar.set(0, 0, 0, hours, 0);
            Date date = calendar.getTime();

            assertEquals(String.format("Should be '%s' greeting message, hours=%d", msg, hours), msg,
                    TimeParser.getGreeting(date));
        }
    }
}
