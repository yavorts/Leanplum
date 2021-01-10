package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

public class CampaignSettingsPage {
    WebDriver driver;

    @FindBy(css = "[placeholder=\"Campaign Name\"]")
    private WebElement campaignName;

    @FindBy(css = ".content .controls span")
    private List<WebElement> campaignControls;

    public CampaignSettingsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    public void setCampaignName(String name) {
        WaitUtils.waitForVisibleElement(campaignName, driver);
        campaignName.clear();
        campaignName.sendKeys(name);
    }

    public void selectSaveOrCancel(String controlOption) {
        WebElement element = WebElementUtils.getElementWithTextFromListOfElements
                (controlOption, campaignControls);
        WaitUtils.waitForClickableElement(element, driver);
        element.click();
    }
}
