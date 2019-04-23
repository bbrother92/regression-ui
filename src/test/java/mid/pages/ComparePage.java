package mid.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static mid.util.Utils.logAllure;


public class ComparePage {

    /**
     * Catalog compare btns
     */
    private SelenideElement itemCard = $("div.hover-box");               // product preview card
    private SelenideElement itemCardLink = $(".images-container a.product-item-link");     // product card link (item title)
    private SelenideElement compareBtn = $("a.action.tocompare");
    /**
     * Comparelist
     */
    private SelenideElement compareListLoc = $(".btn-compare.compare");
    private String listTitles = "#product-comparison  strong > a";
    private String deleteBtn = ".cell.remove.product a";
    private String emptyListMsg = "Товары для сравнения не выбраны.";
    private String emptyListLoc = ".message.info.empty";


    public String addOnCatalog() {
        itemCard.waitUntil(Condition.visible, 5000).scrollTo().click(); // click on item block to show up compare button
        String itemTitle = itemCardLink.waitUntil(Condition.visible, 5000).getText();
        compareBtn.waitUntil(Condition.visible, 5000).scrollTo().click();
        logAllure("item added to list " + itemTitle);
        return itemTitle;
    }

    public String addOnCard() {
        itemCard.waitUntil(Condition.visible, 5000).scrollTo();
        String itemTitle = itemCardLink.waitUntil(Condition.visible, 5000).getText();
        itemCardLink.waitUntil(Condition.visible, 5000).click();
        compareBtn.waitUntil(Condition.visible, 5000).scrollTo().click();
        logAllure("item added to list " + itemTitle);
        return itemTitle;
    }

    public void checkInComparelist(String needle) {
        compareListLoc.waitUntil(Condition.visible, 5000).click();
        logAllure("switching to new tab to check item:" + needle);
        switchTo().window(1);
        $(listTitles).waitUntil(Condition.visible, 5000);
        Boolean result = false;
        for (SelenideElement item : $$(listTitles)) {
            logAllure("items in list: " + item.getText());
            if (item.getText().toLowerCase().contains(needle.toLowerCase())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
    }

    @Description("Удалить в листе")
    public void deleteInCl() {
        compareListLoc.waitUntil(Condition.visible, 5000).click();
        logAllure("switching to new tab to delete item");
//        switchTo().window(1);
        $(deleteBtn).waitUntil(Condition.visible, 5000);
        ElementsCollection items = $$(deleteBtn);
        // deleting from another side of list
        for (int i = items.size() - 1; i >= 0; i--) {
            logAllure("DELETING ITEM: " + $$(listTitles).get(i).getText());
            items.get(i).click();
        }
        $(emptyListLoc).shouldHave(Condition.text(emptyListMsg));
    }
}
//todo check if item avaliable for purchase