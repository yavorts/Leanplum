package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

public class StartCampaignPopup {
    WebDriver driver;

    @FindBy(xpath = "//*[contains(text(),'Start campaign')]")
    private WebElement startCampaignButton;

    public StartCampaignPopup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    public void clickStartCampaign() {
        WaitUtils.waitForClickableElement(startCampaignButton,driver);
        startCampaignButton.click();
    }

}
