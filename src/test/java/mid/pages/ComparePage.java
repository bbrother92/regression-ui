package mid.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;


public class ComparePage {


    /**
     * Catalog
     */

    public SelenideElement itemCard = $("div.hover-box");               // product preview card
    public SelenideElement itemCardLink = $(".images-container a.product-item-link");     // product card link (item title)
    public SelenideElement compareBtn = $("a.action.tocompare");
    /**
     * Comparelist
     */
    public SelenideElement compareListLoc = $(".btn-compare.compare");
    public String listTitles = "#product-comparison  strong > a";


    public String addOnCatalog() {
        itemCard.waitUntil(Condition.visible, 5000).scrollTo().click(); // click on item block to show up compare button
        String itemTitle = itemCardLink.waitUntil(Condition.visible, 5000).getText();
        compareBtn.waitUntil(Condition.visible, 5000).scrollTo().click();
        return itemTitle;
    }

    public String addOnCard() {
        itemCard.waitUntil(Condition.visible, 5000).scrollTo();
        String itemTitle = itemCardLink.waitUntil(Condition.visible, 5000).getText();
        itemCardLink.waitUntil(Condition.visible, 5000).click();
        compareBtn.waitUntil(Condition.visible, 5000).scrollTo().click();
        return itemTitle;
    }

    //todo test this
    public void checkInComparelist(String needle) {
        compareListLoc.waitUntil(Condition.visible, 5000).click();
        System.out.println("sddfsdsfjkldfsjkldfsdsj");
        switchTo().window(1);
        $(listTitles).waitUntil(Condition.visible, 5000);
        Boolean result = false;
        for (SelenideElement item : $$(listTitles)) {
            System.out.println(item.getText());
            if (item.getText().toLowerCase().contains(needle.toLowerCase())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
    }


}
