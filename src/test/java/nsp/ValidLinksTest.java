package nsp;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import mid.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static mid.util.Utils.logAllure;

public class ValidLinksTest extends BaseTest {

    @DataProvider(name = "links")
    public Object[][] data() {
        return new Object[][]{
                {"https://www.nespresso.com/ru/ru/machine-assistance", "div#assistance-header", "Техническая поддержка"},
                {"https://www.nespresso.com/ru/ru/404", "h1.header", "Страница не найдена"},
                {"https://www.nespresso.com/ru/ru/store-locator", "p.title-big", "Я хочу найти бутик и приобрести…"},
                {"https://www.nespresso.com/ru/ru/nespresso-club", "h1", "Что Nespresso может сделать для Вас?"},
                {"https://www.nespresso.com/ru/ru/grands-crus-coffee-range", "article h2", "An Authentic Coffee Experience"},
                {"https://www.nespresso.com/ru/ru/our-choices", "h2", "О выборе"},
                {"https://www.nespresso.com/ru/ru/delivery-payment", "p.bl-title", "Обращаем Ваше внимание на изменение сроков доставки"},
                {"https://www.nespresso.com/ru/ru/holiday-delivery", "p.bl-title", "ОБРАЩАЕМ ВАШЕ ВНИМАНИЕ НА ИЗМЕНЕНИЕ СРОКОВ ДОСТАВКИ"},
                {"https://www.nespresso.com/ru/ru/contactus", "div h1", "Что Nespresso может сделать для Вас?"},
                {"https://www.nespresso.com/ru/ru/terms-conditions", "div.eb_main h3 ", "1. Условия продаж."},
                {"https://www.nespresso.com/ru/ru/promo-terms-and-conditions", ".page-title-wrapper", "Условия и правила акций Nespresso"},
                {"https://www.nespresso.com/ru/ru/join-nespresso-club", "h1", "Что Nespresso может сделать для Вас?"},
                {"https://www.nespresso.com/ru/ru/giftfinder#/", ".g_textContainer", "Я хочу подарить подарок и сказать…"},
                {"https://www.nespresso.com/ru/ru/thequest", "h2", "ИСКЛЮЧИТЕЛЬНЫЙ КОФЕ ВСЕГДА СТОИТ ПОИСКОВ"},
                {"https://www.nespresso.com/ru/errors/503.php", "p.text b", "Наш веб-сайт в настоящее время находится на обслуживании, и новая версия появится в ближайшее время!"},
//not ready                {"https://www.nespresso.com/ru/ru/ultimate-coffee-creations", "", ""},
                {"https://www.nespresso.com/ru/ru/ultimate-coffee-creations", "", ""},
        };
    }

    @Description("Проверить валидность ссылок")
    @Test(dataProvider = "links")
    public void testLink(String url, String locator, String text) {
        open(url);
        logAllure("url:" + url + " Header text:" + text);
        $(locator).shouldHave(Condition.text(text));
    }

}
