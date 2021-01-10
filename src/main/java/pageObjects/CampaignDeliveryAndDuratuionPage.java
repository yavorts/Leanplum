package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class CampaignDeliveryAndDuratuionPage {
    WebDriver driver;

    @FindBy(css = ".scheduled")
    private WebElement scheduledOption;

    public CampaignDeliveryAndDuratuionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    public void clickScheduledOption() {
        WaitUtils.waitForVisibleElement(scheduledOption, driver);
        WaitUtils.waitForClickableElement(scheduledOption,driver);
        scheduledOption.click();
    }


}
