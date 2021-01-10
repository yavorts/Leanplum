package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

public class CampaignActions {
    WebDriver driver;

    @FindBy(css = ".outside-app span")
    private List<WebElement> outsideAppOptions;

    @FindBy(css = ".field-value-view input[name=Message]")
    private WebElement messageForPushNotifiations;

    public CampaignActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    public void clickOutsideAppOption(String option) {
        WaitUtils.waitForListOfElementsToBeVisible(outsideAppOptions,driver);
        WebElementUtils.clickElementWithTextFromListOfElements(option, outsideAppOptions);
    }

    public void setMessagePushNotifications(String message) {
        WaitUtils.waitForClickableElement(messageForPushNotifiations, driver);
        messageForPushNotifiations.sendKeys(message);
    }
}
