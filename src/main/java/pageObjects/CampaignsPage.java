package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

public class CampaignsPage {
    WebDriver driver;
    @FindBy(css = ".new-campaign")
    private WebElement newCampaignButton;

    public CampaignsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    public void clickNewCampaignButton() {
        WaitUtils.waitForVisibleElement(newCampaignButton, driver);
        WaitUtils.waitForClickableElement(newCampaignButton,driver);
        newCampaignButton.click();
    }
}
