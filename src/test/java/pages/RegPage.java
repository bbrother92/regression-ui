package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class RegPage {

    /**
     * go to Registration form
     */
    private SelenideElement accountLink = $x("//a[contains(text(),'Создать учётную запись')]");
    private String accountLinkTitle = "Создать учётную запись";
    /**
     * Reg form fields
     */
    private SelenideElement name = $("input#firstname");
    private SelenideElement lname = $("input#lastname");
    private SelenideElement mail = $("input#email_address");
    private SelenideElement pass = $("input#password");
    private SelenideElement passconfirm = $("input[name='password_confirmation']");
    private SelenideElement button = $("#form-validate  button");

    /*
     * Registration validation
     */
    public SelenideElement nameMsgLoc = $("#firstname-error");
    public SelenideElement lnameMsgLoc = $("#lastname-error");
    public SelenideElement mailMsgLoc = $("#email_address-error");
    public SelenideElement passMsgLoc = $("#password-error");
    public SelenideElement passconfirmMsgLoc = $("#password-confirmation-error");
    public SelenideElement sameUserLoc = $("div.page.messages");


    public String sameUserMsg = "Учётная запись с таким адресом электронной почты уже существует.";


    //TODO
    public AccountPanel submit(String name, String lastname, String email, String password, String passwordconfirm) {
        this.name.setValue(name);
        lname.setValue(lastname);
        mail.setValue(email);
        pass.setValue(password);
        passconfirm.setValue(passwordconfirm);
        button.waitUntil(Condition.visible, 5000).scrollTo().click();
        return page(AccountPanel.class);

    }

    public RegPage toForm() {
        $(accountLink).shouldHave(text(accountLinkTitle));
        accountLink.waitUntil(Condition.visible, 5000).click();
        return page(RegPage.class);
    }


}
