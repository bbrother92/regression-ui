package mid.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static mid.util.Utils.checkSame;
import static mid.util.Utils.logAllure;


public class WishlistPage {


    /**
     * Catalog btns
     */

    public SelenideElement itemCard = $("div.hover-box");               // product preview card
    public SelenideElement itemCardLink = $(".images-container a.product-item-link");     // product card link (item title)
    public SelenideElement wishlistBtn = $("a.action.towishlist");
    /**
     * Wishlist
     */
    public SelenideElement wishlistLoc = $("a.wishlist");
    public String listTitles = ".product-item-name a";
    public String deleteBtn = ".cell.remove.product a";
    public String emptyListMsg = "Товары для сравнения не выбраны.";
    public String emptyListLoc = ".message.info.empty";
    public String compareListResult = "#product-comparison  strong > a";


    public String addOnCatalog() {
        itemCard.waitUntil(Condition.visible, 5000).scrollTo().click(); // click on item block to show up compare button
        String itemTitle = itemCardLink.waitUntil(Condition.visible, 5000).getText();
        wishlistBtn.waitUntil(Condition.visible, 5000).scrollTo().click();
        logAllure("item added to list " + itemTitle);
        return itemTitle;
    }

    public String addOnCard() {
        itemCard.waitUntil(Condition.visible, 5000).scrollTo();
        String itemTitle = itemCardLink.waitUntil(Condition.visible, 5000).getText();
        itemCardLink.waitUntil(Condition.visible, 5000).click();
        wishlistBtn.waitUntil(Condition.visible, 5000).scrollTo().click();
        logAllure("item added to list " + itemTitle);
        return itemTitle;
    }

    public void checkInWishlist(String needle) {
        wishlistLoc.waitUntil(Condition.visible, 5000).click();
        logAllure("switching to new tab to check item:"+needle);
        switchTo().window(1);
        $(listTitles).waitUntil(Condition.visible, 5000);
        Boolean result = false;
        for (SelenideElement item : $$(listTitles)) {
            logAllure("items in list: " + item.getText());
            if (checkSame(item.getText(),needle)) {
                result = true;
            }
        }
        Assert.assertTrue(result);
    }

//    public void deleteInComparelist() {
//        compareListLoc.waitUntil(Condition.visible, 5000).click();
//        logAllure("switching to new tab to delete item");
//        $(deleteBtn).waitUntil(Condition.visible, 5000);
//        ElementsCollection items = $$(deleteBtn);
//        // deleting from another side of list
//        for (int i = items.size() - 1; i >= 0; i--) {
//            logAllure("DELETING ITEM: " + $$(compareListResult).get(i).getText());
//            items.get(i).click();
//        }
//        $(emptyListLoc).shouldHave(Condition.text(emptyListMsg));
//    }
}
//todo check if item avaliable for purchase