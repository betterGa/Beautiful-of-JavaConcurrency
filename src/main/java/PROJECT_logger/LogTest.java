package PROJECT_logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static Logger logger=LoggerFactory.getLogger("PROJECT_logger");

    public static void main(String[] args) {
        logger.warn("hello");
        logger.warn("ohh");
    }
}
