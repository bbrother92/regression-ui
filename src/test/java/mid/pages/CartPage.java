package mid.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static mid.util.Utils.*;

public class CartPage {

    /**
     * Catalog btns
     */
    private String itemCard = "//div[contains(@class,'hover-box')]";               // product preview card
    private SelenideElement itemCardLink = $x("//div[contains(@class,'images-container')]//a[contains(@class,'product-item-link')]");     // product card link (item title)
    private String addToCart = "//button[contains(@class,'tocart')]";
    /**
     * Inside shopping cart
     */
    private String cartLoc = ".header-bottom a.showcart";
    private String listTitles = ".Shopping-Cart__Items h3 a";
    private String deleteBtn = "a.action-delete";
    private String emptyListMsg = "Ваша корзина покупок пуста.";
    private String emptyListLoc = ".cart-empty";

    private String outOfStockLabel = "//div[contains(@class,'unavailable')]";

    private void checkOutOfStock(int position) {
        String locator = elementAt(itemCard, position) + outOfStockLabel; // xpath: label position relative to product preview card selector
        logAllure("Checking item is available " + itemCardLink.getText());
        try {
            $x(locator).waitUntil(Condition.not(Condition.visible), 500);
        } catch (AssertionError e) {
            /* e.printStackTrace(); */
            throw new AssertionError("Item out of stock");
        }
    }

    public String addOnCatalog() {
        checkOutOfStock(1); //todo position hardcoded
        $x(itemCard).waitUntil(Condition.visible, 5000).scrollTo().click(); // click on item block to show up compare button
        String itemTitle = itemCardLink.waitUntil(Condition.visible, 5000).getText();
        $x(addToCart).waitUntil(Condition.visible, 5000).scrollTo().click();
        logAllure("item added to cart " + itemTitle);
        return itemTitle;
    }

    public String addOnCard() {
        checkOutOfStock(1); //todo position hardcoded
        $x(itemCard).waitUntil(Condition.visible, 5000).scrollTo();
        String itemTitle = itemCardLink.waitUntil(Condition.visible, 5000).getText();
        itemCardLink.waitUntil(Condition.visible, 5000).click();
        $x(addToCart).waitUntil(Condition.visible, 5000).scrollTo().click();
        logAllure("item added to cart " + itemTitle);
        return itemTitle;
    }

    public void checkInCartlist(String needle) {
        sleep(1000); //todo
        $(cartLoc).waitUntil(Condition.visible, 5000).click();
        logAllure("checking item present in cart list:" + needle);
        $(listTitles).waitUntil(Condition.visible, 5000);

        Boolean result = false;
        ElementsCollection items = $$(listTitles);
        for (SelenideElement item : items) {
            String qtylocator = elementAt("//input[@data-role='cart-item-qty']", items.indexOf(item) + 1);
            logAllure("items in list: " + item.getText() + " qty: " + $x(qtylocator).waitUntil(Condition.visible,3000).attr("value"));
            if (checkSame(item.getText(), needle)) {
                result = true;
            }
        }
        Assert.assertTrue(result);

    }
}
