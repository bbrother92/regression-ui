package mid.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class ComparePage {



    /**
     * Catalog
     */

    // product preview card
    public SelenideElement itemCard  = $("div.hover-box");
    // product card link (item title)
    public SelenideElement itemCardLink  = $(".images-container a.product-item-link");
    public SelenideElement compareBtn  = $("a.action.tocompare");



    public String addOnCatalog() {
        itemCard.waitUntil(Condition.visible, 5000).scrollTo().click(); // click on item block to show up compare button
        String itemTitle = itemCardLink.waitUntil(Condition.visible, 5000).getText();
        compareBtn.waitUntil(Condition.visible, 5000).scrollTo().click();
        return itemTitle;
    }




}
