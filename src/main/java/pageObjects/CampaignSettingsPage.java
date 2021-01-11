package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

/**
 * Page Object class for the settings of the campaign
 * @author yavort
 */
public class CampaignSettingsPage {
    WebDriver driver;

    @FindBy(css = "[placeholder=\"Campaign Name\"]")
    private WebElement campaignName;

    @FindBy(css = ".content .controls span")
    private List<WebElement> campaignControls;

    /**
     * The constructor of the CampaignSettingsPage class
     * @param driver The WebDriver instance
     */
    public CampaignSettingsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    /**
     * Method to set the campaign name
     * @param name The name of the campaign
     */
    public void setCampaignName(String name) {
        WaitUtils.waitForVisibleElement(campaignName, driver);
        campaignName.clear();
        campaignName.sendKeys(name);
    }

    /**
     * Method to choose save or cancel on the control options options
     * @param controlOption The control option should be save or cancel
     */
    public void selectSaveOrCancel(String controlOption) {
        WebElement element = WebElementUtils.getElementWithTextFromListOfElements
                (controlOption, campaignControls);
        WaitUtils.waitForClickableElement(element, driver);
        element.click();
    }
}
