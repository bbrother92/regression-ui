package mid;

import io.qameta.allure.Feature;
import mid.pages.CartPage;
import mid.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Feature("Тесты валидации полей логина и тест входа/выхода")
public class CartTest extends BaseTest {

    private static final String URL = "https://www.mideastore.ru/";
/*
    @DataProvider(name = "cataloglinks")
    public Object[][] data() {
        return new Object[][]{
//                {"Мелкая бытовая техника", "Микроволновки Мидеа"},
//                {"Климатическая техника", "Обогреватели"},
                {"Мелкая бытовая техника", "Хлебопечи Мидеа"}
//                {"Мелкая бытовая техника", "Роботы-пылесосы Midea"},
        };
    }
*/
    @Test
    public void addOnCardTest() {
        LoginPage lp = open(URL, LoginPage.class);
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018").confirmLogged();
        open(URL);
        lp.gotoCatalog("Мелкая бытовая техника", "Тостеры");
        new CartPage().addOnCard();
    }

//    @Test(dataProvider = "cataloglinks", invocationCount = 10)
    @Test(invocationCount = 20)
//    public void addOnCatalogTest(String menu, String submenu) {
    public void addOnCatalogTest() {
        LoginPage lp = open(URL, LoginPage.class);
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018").confirmLogged();
        open(URL);
        lp.gotoCatalog("Мелкая бытовая техника", "Хлебопечи Мидеа");
        String itemTitle = new CartPage().addOnCatalog();
        new CartPage().checkInCartlist(itemTitle);
    }
}
