package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Panel that shows up after logging in
 */
public class AccountPanel {
	
	private String  insideAccountTitle = "Моя учётная запись";
	private SelenideElement insideAccount = $("div.page-title-wrapper > h1");
	
	public AccountPanel confirmLogged() {
		$(insideAccount).shouldHave(text(insideAccountTitle));
		return page(AccountPanel.class);
		
	}
}
