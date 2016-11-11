import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/***
 * Class for getting the localized messages
 */
public class LocaleBundle {
    private static final String FILE_NAME = "messages";
    private Locale locale;
    private ResourceBundle bundle;

    public LocaleBundle() {
        locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle(FILE_NAME, this.locale, new UTF8Control());
    }

    public LocaleBundle(Locale locale) {
        this.locale = locale;
        bundle = ResourceBundle.getBundle(FILE_NAME, this.locale, new UTF8Control());
    }

    /***
     * @param message the key of the message name in resource file
     * @return localized string based on current locale
     */
    public String getMessage(final String message) {
        return bundle.getString(message);
    }

    /***
     * @return current locale
     */

    public Locale getLocale() {
        return locale;
    }

    /***
     * Supporting class for ResourceBundle that reads locale files in UTF-8 encoding.
     */
    private static class UTF8Control extends ResourceBundle.Control {
        public ResourceBundle newBundle
                (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {

            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }
}
