package nsp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import mid.BaseTest;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class FindTagsTest extends BaseTest {

    private static final String URL = "https://nsp.mygento.net/ru/";
    private static LinkedHashSet<String> links = new LinkedHashSet();

//     todo collect 404 links

    @Test
    public void getHeaderTags() {
        open(URL);
//        executeJavaScript("h3.forEach(item => console.log(item.textContent));"); // todo why doesnt work
        links.addAll(grabLinkstoList(links));
        LinkedHashSet<String> newlist = new LinkedHashSet();

        for (String link : links) {
            open(link);
            System.out.println("\n******* opening " + link + "\n");
            newlist.addAll(grabLinkstoList(links));
        }
        System.out.println(newlist);
        overLinksGetTags(newlist);
    }

    public LinkedHashSet<String> grabLinkstoList(LinkedHashSet links) {
        /*
         * Gets links from the page and returns set
         */
        ElementsCollection items = $$x("//a[@href][not(contains(@href,'#')) and not(contains(@href,'_zzz_'))]");
        int index = 0;
        LinkedHashSet newlist = new LinkedHashSet();
        for (SelenideElement item : items) {
            System.out.println("Link: " + index++ + " " + item);

            if (item.getAttribute("href").contains("http")) {
                newlist.add(item.getAttribute("href"));
            }
        }
        return newlist;
    }

    public void overLinksGetTags(LinkedHashSet<String> ar) {
        /**
         * Get headers tags
         */
        for (String link : ar) {
            System.out.println("========================");
            open(link);
            System.out.println("URL: " + url());
            System.out.println("Title: " + title());
            for (SelenideElement item : $$("h4")) {
                System.out.println(item.getTagName() + " " + item.getText());
            }

            for (SelenideElement item : $$("h3")) {
                System.out.println(item.getTagName() + " " + item.getText());
            }

            for (SelenideElement item : $$("h2")) {
                System.out.println(item.getTagName() + " " + item.getText());
            }
            for (SelenideElement item : $$("h1")) {
                System.out.println(item.getTagName() + " " + item.getText());
            }
        }
    }
}
