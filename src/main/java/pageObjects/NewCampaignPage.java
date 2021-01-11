package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

/**
 * The page object representing the new campaign page
 * @author yavort
 */
public class NewCampaignPage {
    WebDriver driver;

    @FindBy(css = ".name span")
    private WebElement campaignName;

    @FindBy(css = ".option p")
    private List<WebElement> campaignGoalsOptions;

    @FindBy(css = ".composer-summary-controls .text")
    private WebElement startCampaignButton;

    @FindBy(xpath = "//*[contains(text(),'End Campaign')]")
    private WebElement endCampaignButton;

    @FindBy(xpath = "//*[contains(text(),'End the Campaign?)]")
    private WebElement endCampaignPopupMessageTitle;

    @FindBy(xpath = "//div[@class='footer']//span[contains(text(),'End')]")
    private WebElement endCampaignPopupMessage;

    @FindBy(xpath = "//span[contains(text(),'Finished')]")
    private WebElement finishedStatusCampaign;

    /**
     * The constructor of the NewCampaignPage class
     * @param driver The instance of the WebDriver
     */
    public NewCampaignPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    /**
     * Method to click the campaign name
     */
    public void clickCampaignName() {
        WaitUtils.waitForClickableElement(campaignName, driver);
        campaignName.click();
    }

    /**
     * Method to select one of the campaign options - goal, audience, delivery method, actions
     * @param option The option to select
     */
    public void selectCampaignOption(String option) {
        WaitUtils.waitForClickableElement(campaignName, driver);
        String locator = String.format("//*[contains(text(),'%s')]", option);

        WebElement element = driver.findElement(By.xpath(locator));
        WaitUtils.waitForClickableElement(element, driver);
        element.click();
    }

    /**
     * Method to select the Goal option in the campaign
     * @param goal
     */
    public void selectCampaignGoal(String goal) {
        WaitUtils.waitForListOfElementsToBeVisible(campaignGoalsOptions, driver);
        WebElementUtils.clickElementWithTextFromListOfElements(goal, campaignGoalsOptions);
    }

    /**
     * Method to click the start campaign button
     */
    public void clickStartCampaignButton() {
        WaitUtils.waitForClickableElement(startCampaignButton, driver);
        startCampaignButton.click();
    }

    /**
     * Method to click the end campaign button
     */
    public void clickEndCampaignButton() {
        WaitUtils.waitForClickableElement(endCampaignButton, driver);
        endCampaignButton.click();
    }

    /**
     * Method to get the title message of the popup
     * @return the message as a string
     */
    public String getPopupEndCampaignTitleMessage() {
        WaitUtils.waitForClickableElement(endCampaignPopupMessageTitle, driver);
        return endCampaignPopupMessageTitle.getText();
    }

    /**
     * Clicks the end campaign on the popup message
     */
    public void clickEndCampaignOnPopupMessage() {
        WaitUtils.waitForClickableElement(endCampaignPopupMessage, driver);
        endCampaignPopupMessage.click();
    }

    /**
     * Get the status of the campaign when campaign end
     * @return the status of the campaign
     */
    public String getEndStatusCampaign() {
        WaitUtils.waitForClickableElement(finishedStatusCampaign, driver);
        return finishedStatusCampaign.getText();
    }
}
