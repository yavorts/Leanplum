import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    @FindBy(css = "input[placeholder=Email]")
    private WebElement email;

    @FindBy(css = "input[type=password]")
    private WebElement password;

    @FindBy(css = "input[type=checkBox]")
    private WebElement rememberCheckbox;

    @FindBy(css = "[name=signUpForm] button[type=submit]")
    private WebElement logInButton;

    @FindBy(xpath = "//*[@href=\'https://sso.leanplum.com\']")
    private WebElement businessLogIn;

    @FindBy(css = ".ngdialog-close")
    private WebElement closeButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setEmail(String emailText) {
        email.sendKeys(emailText);
    }

    public void setPassword(String passwordText) {
        password.sendKeys(passwordText);
    }

    

}
