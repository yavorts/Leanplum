package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

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


    public NewCampaignPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    public void clickCampaignName() {
        WaitUtils.waitForClickableElement(campaignName, driver);
        campaignName.click();
    }

    public void selectCampaignOption(String option) {
        WaitUtils.waitForClickableElement(campaignName, driver);
        String locator = String.format("//*[contains(text(),'%s')]", option);

        WebElement element = driver.findElement(By.xpath(locator));
        WaitUtils.waitForClickableElement(element, driver);
        element.click();
    }

    public void selectCampaignGoal(String goal) {
        WaitUtils.waitForListOfElementsToBeVisible(campaignGoalsOptions, driver);
        WebElementUtils.clickElementWithTextFromListOfElements(goal, campaignGoalsOptions);
    }

    public void clickStartCampaignButton() {
        WaitUtils.waitForClickableElement(startCampaignButton, driver);
        startCampaignButton.click();
    }

    public void clickEndCampaignButton() {
        WaitUtils.waitForClickableElement(endCampaignButton, driver);
        endCampaignButton.click();
    }

    public String getPopupEndCampaignTitleMessage() {
        WaitUtils.waitForClickableElement(endCampaignPopupMessageTitle, driver);
        return endCampaignPopupMessageTitle.getText();
    }

    public void clickEndCampaignOnPopupMessage() {
        WaitUtils.waitForClickableElement(endCampaignPopupMessage, driver);
        endCampaignPopupMessage.click();
    }

    public String getEndStatusCampaign() {
        WaitUtils.waitForClickableElement(finishedStatusCampaign, driver);
        return finishedStatusCampaign.getText();
    }
}
