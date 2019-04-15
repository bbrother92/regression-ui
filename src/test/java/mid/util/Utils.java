package mid.util;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Utils {
    public static Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    //todo cant end input
    public static void debug() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Press any key to continue\n");
        String st = reader.next();
        System.out.println("You entered" + st);
    }


    @Step
    public static void logAllure(String log) {
        logger.info(": " + log);
    }
}
