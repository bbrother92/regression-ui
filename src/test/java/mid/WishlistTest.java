package mid;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import mid.pages.LoginPage;
import mid.pages.WishlistPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Feature("Тесты работы листа сравнений")
public class WishlistTest extends BaseTest {

    private static final String URL = "https://www.mideastore.ru/";

    @DataProvider(name = "cataloglinks")
    public Object[][] data() {
        return new Object[][]{
                {"Мелкая бытовая техника", "Микроволновки Мидеа"},
                {"Климатическая техника", "Обогреватели"},
                {"Мелкая бытовая техника", "Роботы-пылесосы Midea"},
        };
    }

    @Description("Добавить в избранное через quickview в каталоге предварительно залогинившись")
    @Test(dataProvider = "cataloglinks")
    public void wishlistOnCatalogTest(String menu, String submenu) {
        LoginPage lp = open(URL, LoginPage.class);
        WishlistPage p = new WishlistPage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        lp.gotoCatalog(menu, submenu);
        String itemTitle = p.addOnCatalog();
        p.checkInWishlist(itemTitle);
    }

    @Description("Добавить в избранное через карточку товара в каталоге предварительно залогинившись")
    @Test
    public void wishlistOnCardTest() {
        LoginPage lp = open(URL, LoginPage.class);
        WishlistPage p = new WishlistPage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        lp.gotoCatalog("Отдельностоящая техника", "Посудомоечные машины");
        String itemTitle = p.addOnCard();
        p.checkInWishlist(itemTitle);
    }

    @Description("Удалить в листе")
    @Test
    public void deleteTest() {
        LoginPage lp = open(URL, LoginPage.class);
        WishlistPage p = new WishlistPage();
        lp.toLoginForm().login("johndoetestexample2018@gmail.com", "Test2018");
        open(URL);
        lp.gotoCatalog("Отдельностоящая техника", "Посудомоечные машины");
        String itemTitle = p.addOnCard();
        p.checkInWishlist(itemTitle);
        p.deleteInWl();
    }
}
