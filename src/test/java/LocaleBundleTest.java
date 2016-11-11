import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class LocaleBundleTest {
    private LocaleBundle localeBundle;

    @Test
    public void testNonExistingLocale() {
        Locale nonExistentLocale = new Locale("sv", "SE");
        checkDefaultAndEnglishLocale(nonExistentLocale);
    }

    @Test
    public void testEnglishLocale() {
        Locale englishLocale = new Locale("en", "US");
        checkDefaultAndEnglishLocale(englishLocale);
    }

    @Test
    public void testRussianLocale() {
        Locale nonExistentLocale = new Locale("ru", "RU");
        localeBundle = new LocaleBundle(nonExistentLocale);

        String morning = "Доброе утро, Мир!";
        String day = "Добрый день, Мир!";
        String evening = "Добрый вечер, Мир!";
        String night = "Доброй ночи, Мир!";

        assertEquals(String.format("Should be '%s' message", morning), morning,
                localeBundle.getMessage("greet.morning"));
        assertEquals(String.format("Should be '%s' message", day), day,
                localeBundle.getMessage("greet.day"));
        assertEquals(String.format("Should be '%s' message", evening), evening,
                localeBundle.getMessage("greet.evening"));
        assertEquals(String.format("Should be '%s' message", night), night,
                localeBundle.getMessage("greet.night"));
    }

    private void checkDefaultAndEnglishLocale(Locale locale) {
        localeBundle = new LocaleBundle(locale);

        String morning = "Good morning, World!";
        String day = "Good day, World!";
        String evening = "Good evening, World!";
        String night = "Good night, World!";

        assertEquals(String.format("Should be '%s' message", morning), morning,
                localeBundle.getMessage("greet.morning"));
        assertEquals(String.format("Should be '%s' message", day), day,
                localeBundle.getMessage("greet.day"));
        assertEquals(String.format("Should be '%s' message", evening), evening,
                localeBundle.getMessage("greet.evening"));
        assertEquals(String.format("Should be '%s' message", night), night,
                localeBundle.getMessage("greet.night"));
    }
}
