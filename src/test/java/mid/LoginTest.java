package mid;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import mid.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Feature("Тесты валидации полей логина и тест входа/выхода")
public class LoginTest extends BaseTest {

    private static final String URL = "https://www.mideastore.ru/";


    @Test
    public void loginTest() {
        open(URL, LoginPage.class)
                .toLoginForm()
                .login("johndoetestexample2018@gmail.com", "Test2018")
                .confirmLogged();
        new LoginPage().logout();
    }


    @Test
    public void wrongMailTest() {
        open(URL, LoginPage.class)
                .toLoginForm()
                .login("wrongexample@gmail.com", "Test2018");
        new LoginPage().invldMailPassLoc.shouldHave(Condition.text("неправильный адрес электронной почты (email) или пароль."));
    }

    @Test
    public void wrongPassTest() {
        open(URL, LoginPage.class)
                .toLoginForm()
                .login("johndoetestexample2018@gmail.com", "@$$$!");
        new LoginPage().invldMailPassLoc.shouldHave(Condition.text("неправильный адрес электронной почты (email) или пароль."));
    }

    @Test
    public void invldMailTest() {
        open(URL, LoginPage.class)
                .toLoginForm()
                .login("wrongexamplegmail.com", "Test2018");
        Assert.assertEquals(new LoginPage().mailErrorLoc.getText(), "Пожалуйста, введите правильный адрес электронной почты (Пример: johndoe@domain.com).");
    }

    @Test
    public void blankMailTest() {
        open(URL, LoginPage.class)
                .toLoginForm()
                .login("", "Test2018");
        Assert.assertEquals(new LoginPage().mailErrorLoc.getText(), "Это поле обязательно для заполнения.");
    }


    @Test
    public void blankPassTest() {
        open(URL, LoginPage.class)
                .toLoginForm()
                .login("johndoetestexample2018@gmail.com", "");
        Assert.assertEquals(new LoginPage().passErrorLoc.getText(), "Это поле обязательно для заполнения.");
    }
}
