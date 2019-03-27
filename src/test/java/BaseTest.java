import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.close;

/**
 * Superclass for tests:
 * {@link  LoginTest,}
 */
public abstract class BaseTest {
	protected Logger logger;
	
	@BeforeSuite
	public void setUp() {
		SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
		
		Configuration.browser = "chrome";
		Configuration.startMaximized = false;
//		Configuration.timeout=4999;
		Locale.setDefault(Locale.ENGLISH);
		logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		logger.info("Starting test suite");
	}
	
	@BeforeMethod
	public void setUpMethod(Method method)
	{
		logger.info("Starting test method: " + method.getName().toString());
	}
	
	@AfterMethod
	public void tearDownMethod(Method method)  {
		logger.info("Exiting test method: " + method.getName().toString());
		WebDriverRunner.getWebDriver().manage().deleteAllCookies();
		
	}
	
	@AfterSuite
	public void tearDown() {
		logger.info("Exiting test suite");
		close();
	}
	
	@AfterTest
	public void clear() {
	}
	
	
}