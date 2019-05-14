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
        LoginPage lp = open(URL, LoginPage.class).toLoginForm();
        lp.login("johndoetestexample2018@gmail.com", "Test2018").confirmLogged();
        lp.logout();
    }


    @Test
    public void wrongMailTest() {
        LoginPage lp = open(URL, LoginPage.class).toLoginForm();
        lp.login("wrongexample@gmail.com", "Test2018");
        lp.invldMailPassLoc.shouldHave(Condition.text("неправильный адрес электронной почты (email) или пароль."));
    }

    @Test
    public void wrongPassTest() {
        LoginPage lp = open(URL, LoginPage.class).toLoginForm();
        lp.login("johndoetestexample2018@gmail.com", "@$$$!");
        new LoginPage().invldMailPassLoc.shouldHave(Condition.text("неправильный адрес электронной почты (email) или пароль."));
    }

    @Test
    public void invldMailTest() {
        LoginPage lp = open(URL, LoginPage.class).toLoginForm();
        lp.login("wrongexamplegmail.com", "Test2018");
        Assert.assertEquals(new LoginPage().mailErrorLoc.getText(), "Пожалуйста, введите правильный адрес электронной почты (Пример: johndoe@domain.com).");
    }

    @Test
    public void blankMailTest() {
        LoginPage lp = open(URL, LoginPage.class).toLoginForm();
        lp.login("", "Test2018");
        Assert.assertEquals(new LoginPage().mailErrorLoc.getText(), "Это поле обязательно для заполнения.");
    }


    @Test
    public void blankPassTest() {
        LoginPage lp = open(URL, LoginPage.class).toLoginForm();
        lp.login("johndoetestexample2018@gmail.com", "");
        Assert.assertEquals(new LoginPage().passErrorLoc.getText(), "Это поле обязательно для заполнения.");
    }
}
