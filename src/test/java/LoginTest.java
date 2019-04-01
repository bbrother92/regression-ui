import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.Test;
import pages.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class LoginTest extends BaseTest {

    // page url
    private static final String URL = "https://www.mideastore.ru/";


    @Test
    public void loginTest() {
        open(URL, LoginPage.class)
                .toLoginForm()
                .login("johndoetestexample2018@gmail.com", "Test2018")
                .confirmLogged();
        new LoginPage().logout();
		  sleep(9000);

    }

//	/**
//	 * Login with wrong pass and email
//	 */
//	@Test(invocationCount = 1)
//	public void loginWrongMailTest() {
//		open(URL, LoginPage.class).login(User.NegativeTest.EMAIL, User.NegativeTest.PSWD);
//		LoginPage loginPage = page(LoginPage.class);
//		loginPage.alertField.shouldHave(Condition.text("Введите правильный адрес электронной почты и пароль"));
//	}
//
//	/**
//	 * Login with empty pass
//	 */
//	@Test(invocationCount = 1)
//	public void loginEmptyPassTest() {
//		open(URL, LoginPage.class).login(User.NegativeTest2.EMAIL, User.NegativeTest2.PSWD);
//		LoginPage loginPage = page(LoginPage.class);
//		loginPage.alertField.shouldHave(Condition.text("Это поле обязательно!"));
//	}
//
//	/**
//	 * Login with empty email
//	 */
//	@Test(invocationCount = 1)
//	public void loginEmptyEmailTest() {
//		open(URL, LoginPage.class).login(User.NegativeTest3.EMAIL, User.NegativeTest3.PSWD);
//		LoginPage loginPage = page(LoginPage.class);
//		loginPage.alertField.shouldHave(Condition.text("Это поле обязательно!"));
//	}
}
