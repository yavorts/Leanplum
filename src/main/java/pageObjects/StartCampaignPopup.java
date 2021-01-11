package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

/**
 * Page Object class representing the popup in Start campaign page
 * @author yavort
 */
public class StartCampaignPopup {
    WebDriver driver;

    @FindBy(xpath = "//*[contains(text(),'Start campaign')]")
    private WebElement startCampaignButton;

    /**
     * Constructor of the StartCampaignPopup class
     * @param driver The instance of the WebDriver
     */
    public StartCampaignPopup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    /**
     * Method to click the start campaign button
     */
    public void clickStartCampaign() {
        WaitUtils.waitForClickableElement(startCampaignButton,driver);
        startCampaignButton.click();
    }

}
