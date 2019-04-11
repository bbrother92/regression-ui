package mid;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.close;

/**
 * Superclass for tests:
 * {@link  LoginTest ,}
 */
public abstract class BaseTest {
    public static Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    @BeforeSuite
    public void setUp(ITestContext context) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        Configuration.browser = "chrome";
        Configuration.startMaximized = false;
//		Configuration.timeout=4999;
        Locale.setDefault(Locale.ENGLISH);
//        logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME); //TODO why npe

        logAllure("Starting test suite " + context.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeMethod
    public void setUpMethod(Method method) {
        this.logAllure("Starting test method: " + method.getName());
    }

    @AfterMethod
    public void tearDownMethod(Method method) {
        logAllure("Exiting test method: " + method.getName());
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();

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

    //	@AfterTest
//	public void clear() {
//	}
    @Step
    public static void logAllure(String log) {
        logger.info("Logged to allure: " + log);
    }
}