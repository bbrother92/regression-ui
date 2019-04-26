package mid.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static mid.util.Utils.*;


public class WishlistPage {


    /**
     * Catalog btns
     */

    private SelenideElement itemCard = $("div.hover-box");               // product preview card
    private SelenideElement itemCardLink = $(".images-container a.product-item-link");     // product card link (item title)
    private SelenideElement wishlistBtn = $("a.action.towishlist");
    /**
     * Wishlist
     */
    private SelenideElement wishlistLoc = $("a.wishlist");
    private String listTitles = ".product-item-name a";
    private String deleteBtn = "a[data-post-remove]";
    private String emptyListMsg = "В листе пожеланий пусто.";
    private String emptyListLoc = ".message.info.empty";


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

    public void deleteInWl() {
        wishlistLoc.waitUntil(Condition.visible, 5000).click();
        switchTo().window(1);
        logAllure("switching to new tab to delete item");
        $(deleteBtn).waitUntil(Condition.visible, 5000);
        ElementsCollection items = $$(deleteBtn);
        // deleting from another side of list
        for (int i = items.size() - 1; i >= 0; i--) {
            logAllure("DELETING ITEM: " + $$(listTitles).get(i).getText());
            items.get(i).scrollTo().click();
        }
        $(emptyListLoc).shouldHave(Condition.text(emptyListMsg));
    }
}
//todo check if item avaliable for purchase