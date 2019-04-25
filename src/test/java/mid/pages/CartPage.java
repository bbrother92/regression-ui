package mid.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static mid.util.Utils.elementAt;
import static mid.util.Utils.logAllure;


public class CartPage {

    /**
     * Catalog btns
     */
    private String itemCard = "//div[contains(@class,'hover-box')]";               // product preview card
    private SelenideElement itemCardLink = $x("//div[contains(@class,'images-container')]//a[contains(@class,'product-item-link')]");     // product card link (item title)
    private SelenideElement addToCart = $("//button[contains(@class,'tocart')]");
    /**
     * Comparelist
     */
    private SelenideElement compareListLoc = $(".btn-compare.compare");
    private String listTitles = "#product-comparison  strong > a";
    private String deleteBtn = ".cell.remove.product a";
    private String emptyListMsg = "Товары для сравнения не выбраны.";
    private String emptyListLoc = ".message.info.empty";

    private String outOfStockLabel = "//div[contains(@class,'unavailable')]";


    public void checkOutOfStock() {
        String locator = itemCard.concat(outOfStockLabel);
        logAllure("Checking item is available " + itemCardLink.getText());
        try {
            $x(elementAt(locator,1)).waitUntil(Condition.not(Condition.visible), 500).getText();
        } catch (AssertionError e) {
//                e.printStackTrace();
            throw new Error("Item out of stock");
        }
    }

}
