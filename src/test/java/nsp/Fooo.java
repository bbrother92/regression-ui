package nsp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import mid.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Fooo extends BaseTest {

    private static final String URL = "https://nsp.mygento.net/ru/";
    //    private static final String URL = "https://nsp.mygento.net/ru/coffee-capsules/original";
    private static ArrayList<String> links = new ArrayList();


    @Test
    public void getHeaderTags() {
        open(URL);
//        executeJavaScript("h3.forEach(item => console.log(item.textContent));"); // todo why doesnt work
        links.addAll(grabLinkstoList(links));
        ArrayList<String> newlist = new ArrayList();

        for (String link : links) {
            open(link);
            System.out.println("\n******* opening " + link + "\n");
            newlist.addAll(grabLinkstoList(  links));
        }
        System.out.println();
        System.out.println(newlist);
//        overLinksGetTags(links);
    }

    public ArrayList<String> grabLinkstoList(ArrayList links) {
        ElementsCollection items = $$x("//a[@href][not(contains(@href,'#')) and not(contains(@href,'_zzz_'))]");
        int index = 0;
        ArrayList newlist = new ArrayList();
        for (SelenideElement item : items) {
            System.out.println("Link: " + index++ + " " + item);
            newlist.add(item.getAttribute("href"));
        }
        return newlist;
    }

    public void overLinksGetTags(ArrayList<String> ar) {

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
