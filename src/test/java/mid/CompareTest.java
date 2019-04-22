package mid;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import mid.pages.ComparePage;
import mid.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

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

    @Description("Добавить в лист сравнений через quickview в каталоге предварительно залогинившись")
    @Test(dataProvider = "cataloglinks")
    public void compareOnCatalogTest(String menu, String submenu) {
        LoginPage lp = open(URL, LoginPage.class);
        ComparePage cp = new ComparePage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        lp.gotoCatalog(menu, submenu);
        String itemTitle = cp.addOnCatalog();
        cp.checkInComparelist(itemTitle);
    }

    @Description("Добавить в лист сравнений через карточку товара в каталоге предварительно залогинившись")
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

    @Description("Логин и удаление всех товаров в листе сравнений")
    @Test
    public void deleteTest() {
        LoginPage lp = open(URL, LoginPage.class);
        ComparePage cp = new ComparePage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        lp.gotoCatalog("Отдельностоящая техника", "Посудомоечные машины");
        String itemTitle = cp.addOnCard();
        cp.checkInComparelist(itemTitle);
        cp.deleteInComparelist();
    }

    @Description("Добавить в лист сравнений через quickview в каталоге без логина")
    @Test(dataProvider = "cataloglinks")
    public void compareOnCatalogWOLoginTest(String menu, String submenu) {
        LoginPage lp = open(URL, LoginPage.class);
        ComparePage cp = new ComparePage();
        lp.gotoCatalog(menu, submenu);
        String itemTitle = cp.addOnCatalog();
        cp.checkInComparelist(itemTitle);
    }

    @Description("Добавить в лист сравнений через карточку товара в каталоге без логина")
    @Test
    public void compareOnCardWOLoginTest() {
        LoginPage lp = open(URL, LoginPage.class);
        ComparePage cp = new ComparePage();
        lp.gotoCatalog("Отдельностоящая техника", "Посудомоечные машины");
        String itemTitle = cp.addOnCard();
        cp.checkInComparelist(itemTitle);
    }
}
