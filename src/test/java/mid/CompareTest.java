package mid;

import io.qameta.allure.Feature;
import mid.pages.ComparePage;
import mid.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static mid.util.Utils.debug;

@Feature("Тесты работы листа сравнений")
public class CompareTest extends BaseTest {

    private static final String URL = "https://www.mideastore.ru/";

    @DataProvider(name = "cataloglinks")
    public Object[][] data() {
        return new Object[][]{
                {"Мелкая бытовая техника", "Микроволновки Мидеа"},
                {"Климатическая техника", "Обогреватели"},
                {"Мелкая бытовая техника", "Роботы-пылесосы Midea"},
        };
    }

    @Test(dataProvider = "cataloglinks")
    public void compareOnCatalogTest(String menu,String submenu) {
        LoginPage lp = open(URL, LoginPage.class);
        ComparePage cp = new ComparePage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        lp.gotoCatalog(menu, submenu);
        String itemTitle = cp.addOnCatalog();
        cp.checkInComparelist(itemTitle);
    }


    @Test
    public void compareOnCardTest() {
        LoginPage lp = open(URL, LoginPage.class);
        ComparePage cp = new ComparePage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        lp.gotoCatalog("Отдельностоящая техника", "Посудомоечные машины");
        String itemTitle = cp.addOnCard();
        cp.checkInComparelist(itemTitle);
    }

    @Test
    public void deleteTest() {
        LoginPage lp = open(URL, LoginPage.class);
        ComparePage cp = new ComparePage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        debug();
        lp.gotoCatalog("Отдельностоящая техника", "Посудомоечные машины");
        String itemTitle = cp.addOnCard();
        cp.checkInComparelist(itemTitle);
        cp.deleteInComparelist();
    }
}
