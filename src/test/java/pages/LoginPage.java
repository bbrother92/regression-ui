package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class LoginPage {
	
	/**
	 * go to Login form
	 */
	private SelenideElement accountLink = $(".authorization-link a");
	private String accountLinkTitle = "Войти";
	/**
	 * Login form fields
	 */
	private SelenideElement mail = $("input[name='login[username]']");
	private SelenideElement pass = $("input[name='login[password]']");
	private SelenideElement button = $(".fieldset  button[type='submit']");
	
	
	
	public AccountPanel login(String email, String password) {
		mail.setValue(email);
		pass.setValue(password);
		button.waitUntil(Condition.visible, 5000).scrollTo().click();
		return page(AccountPanel.class);
		
	}
	
	public LoginPage toLoginForm() {
		$(accountLink).shouldHave(text(accountLinkTitle));
		accountLink.waitUntil(Condition.visible, 5000).click();
		return page(LoginPage.class);
		
	}
	
}
