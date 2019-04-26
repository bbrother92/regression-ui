package mid.util;

import io.qameta.allure.Step;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;


public class Utils {
    public static Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    public static void debug() {
        /* todo cant type anything whye
        Scanner reader = new Scanner(System.in);
        System.out.println("Press any key to continue\n");
        String st = reader.next();
        System.out.println("You entered" + st);
        */
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date();
        screenshot("debug" + dtf.format(date).toString());
        TextIO textIO = TextIoFactory.getTextIO();
        String line = textIO.newStringInputReader()
                .withDefaultValue("ok")
                .read("Continue");
        logAllure(String.format("after pausing execution:%s on url:%s", line, url()));
    }


    public static void higlighElement(String cssselector) {
        JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
        jse.executeScript(String.format("document.querySelector('%s').style.border='3px solid red';", cssselector));
    }

    /**
     * Locates element at position (Actually its just wrapper of xpath string)
     *
     * @param element  xpath
     * @param position First element has index 1
     * @return
     */
    public static String elementAt(String element, int position) {
        return String.format("(%s)[position()=%d]", element, position); //todo implement item #
    }


    @Step
    public static void logAllure(Object log) {
        logger.info(": " + log.toString());
    }

    public static boolean checkSame(String longstring, String needle) {
        return longstring.trim().toLowerCase().contains(needle.trim().toLowerCase());
    }
}
