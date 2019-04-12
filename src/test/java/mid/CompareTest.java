package mid;

import io.qameta.allure.Feature;
import mid.pages.ComparePage;
import mid.pages.LoginPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

@Feature("Тесты валидации полей логина и тест входа/выхода")
public class CompareTest extends BaseTest {

    private static final String URL = "https://www.mideastore.ru/";


    @Test
    public void compareOnCatalogTest() {
        LoginPage lp = open(URL, LoginPage.class);
        ComparePage cp = new ComparePage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        lp.gotoCatalog("Мелкая бытовая техника", "Микроволновки Мидеа");
        String itemTitle = cp.addOnCatalog();
        System.out.println(itemTitle);
        cp.checkInComparelist(itemTitle);


    }

    @Test
    public void compareOnCardTest() {
        LoginPage lp = open(URL, LoginPage.class);
        ComparePage cp = new ComparePage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        lp.gotoCatalog("Мелкая бытовая техника", "Микроволновки Мидеа");
        String itemTitle = cp.addOnCard();
        System.out.println(itemTitle);
        cp.checkInComparelist(itemTitle);
    }


}
