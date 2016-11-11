import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Date now = new Date();
        String greetingMessage = TimeParser.getGreeting(now);

        log.info(greetingMessage);
    }
}
