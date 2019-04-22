package mid;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.close;
import static mid.util.Utils.logAllure;

/**
 * Superclass for tests:
 * {@link  LoginTest ,}
 */
public abstract class BaseTest {

    @BeforeSuite
    public void setUp(ITestContext context) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        Configuration.browser = "chrome";
        Configuration.startMaximized = false;
//		Configuration.timeout=4999;
        Locale.setDefault(Locale.ENGLISH);
//        logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME); //TODO why npe
        Logger parent = Logger.getLogger("com.codeborne.selenide");
        parent.setLevel(Level.SEVERE); // set loggin lvl for selenide
        logAllure("Starting test suite " + context.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeMethod
    public void setUpMethod(Method method) {
        logAllure("Starting test method: " + method.getName());
    }

    @AfterMethod
    public void tearDownMethod(Method method) {
        logAllure("Exiting test method: " + method.getName());
        close();
    }

    @BeforeClass
    public void setUpClass(ITestContext context) {
        logAllure("Starting test class: " + context.getCurrentXmlTest().getXmlClasses().toString());
    }

    @AfterClass
    public void tearDownClass(ITestContext context) {
        logAllure("Exiting test class: " + context.getCurrentXmlTest().getXmlClasses().toString());
    }

    @AfterSuite
    public void tearDown(ITestContext context) {
        logAllure("Exiting test suite " + context.getCurrentXmlTest().getSuite().getName());
        close();
    }

}