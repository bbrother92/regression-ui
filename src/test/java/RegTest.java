import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegPage;

import static com.codeborne.selenide.Selenide.open;


@Feature("Тесты валидации полей регистрации")
public class RegTest extends BaseTest {

    private static final String URL = "https://www.mideastore.ru/";


    @Test
    public void blankNameTest() {
        open(URL, RegPage.class)
                .toForm()
                .submit("", "Фамилия", "johndoetestexample2018@gmail.com", "Test2018", "Test2018");
        Assert.assertEquals(new RegPage().nameMsgLoc.getText(), "Это поле обязательно для заполнения.");
    }

    @Test
    public void blankLastnameTest() {
        open(URL, RegPage.class)
                .toForm()
                .submit("Максимилиан", "", "johndoetestexample2018@gmail.com", "Test2018", "Test2018");
        Assert.assertEquals(new RegPage().lnameMsgLoc.getText(), "Это поле обязательно для заполнения.");
    }

    @Test
    public void blankPassTest() {
        open(URL, RegPage.class)
                .toForm()
                .submit("Максимилиан", "Фамилия", "johndoetestexample2018@gmail.com", "", "Test2018");
        Assert.assertEquals(new RegPage().passMsgLoc.getText(), "Это поле обязательно для заполнения.");
    }

    @Test
    public void blankPassconfirmTest() {
        open(URL, RegPage.class)
                .toForm()
                .submit("Максимилиан", "Фамилия", "johndoetestexample2018@gmail.com", "Test2018", "");
        Assert.assertEquals(new RegPage().passconfirmMsgLoc.waitUntil(Condition.visible,5000).getText(), "Это поле обязательно для заполнения.");
    }

    @Test
    public void matchPassTest() {
        open(URL, RegPage.class)
                .toForm()
                .submit("Максимилиан", "Фамилия", "johndoetestexample2018@gmail.com", "Test2018", "test201*");
        Assert.assertEquals(new RegPage().passconfirmMsgLoc.getText(), "Please enter the same value again.");
    }

    @Test
    public void invalidEmailTest() {
        open(URL, RegPage.class)
                .toForm()
                .submit("Максимилиан", "Фамилия", "john2018gmail.com", "Test2018", "Test2018");
        Assert.assertEquals(new RegPage().mailMsgLoc.getText(), "Пожалуйста, введите правильный адрес электронной почты (Пример: johndoe@domain.com).");
    }

    @Test
    public void smallPassTest() {
        open(URL, RegPage.class)
                .toForm()
                .submit("Максимилиан", "Фамилия", "john2018gmail.com", "test5", "test5");
        Assert.assertEquals(new RegPage().passMsgLoc.getText(), "Минимальная длина этого поля должна быть равна или больше 6 символов. Пробелы перед и после символов будут проигнорированы.");
    }

    @Test
    public void sameUserTest() {
        open(URL, RegPage.class)
                .toForm()
                .submit("Максимилиан", "Фамилия", "johndoetestexample2018@gmail.com", "Test2018", "Test2018");
        Assert.assertTrue(new RegPage().sameUserLoc.waitUntil(Condition.visible,1000).getText().contains("Учётная запись с таким адресом электронной почты уже существует"));
    }





}
