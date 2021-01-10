package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

public class LoginPage {
    WebDriver driver;
    @FindBy(css = "input[placeholder=Email]")
    private WebElement email;

    @FindBy(css = "[name=signInForm] input[type=Password]")
    private WebElement password;

    @FindBy(css = "input[type=checkBox]")
    private WebElement rememberCheckbox;

    @FindBy(css = "[name=signInForm] button[type=submit]")
    private WebElement logInButton;

    @FindBy(xpath = "//*[@href=\'https://sso.leanplum.com\']")
    private WebElement businessLogIn;

    @FindBy(css = ".ngdialog-close")
    private WebElement closeButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    public void setEmail(String emailText) {
        WaitUtils.waitForVisibleElement(email, driver);
        email.sendKeys(emailText);
    }

    public void setPassword(String passwordText) {
        WaitUtils.waitForVisibleElement(password, driver);
        password.sendKeys(passwordText);
    }

    public void setRememberState(boolean state) {
        boolean currentStateRemember = this.IsRememberButtonSelected();
        if (currentStateRemember != state) {
            WebElementUtils.scrollElementIntoView(driver, rememberCheckbox);
            rememberCheckbox.click();
        }
    }

    public boolean IsRememberButtonSelected() {
        WaitUtils.waitForVisibleElement(rememberCheckbox, driver);
        return WebElementUtils.isAttributePresentInWebelement
                (rememberCheckbox, "class", "ng-not-empty");
    }

    public void clickLoginButton() {
        WaitUtils.waitForVisibleElement(logInButton, driver);
        logInButton.click();
    }

}
