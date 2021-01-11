package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

/**
 * Page Object Class representing the Side Naviation Menu
 * @author yavort
 */
public class SideNavigationMenu {
    WebDriver driver;
    @FindBy(css = ".side-nav-view")
    private WebElement sideNavMenu;

    @FindBy(css = ".icon+div p")
    private List<WebElement> sideNavMenuOptions;

    /**
     * The constructor of the SideNavigationMenu class
     * @param driver The WebDriver instance
     */
    public SideNavigationMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    /**
     * Sets the side navigation menu status to open or closed
     * @param state state open or closed
     */
    public void setSideNavMenuState(boolean state) {
        boolean currentStateRemember = this.IsSideNavMenuOpened();
        if (currentStateRemember != state) {
            WebElementUtils.scrollElementIntoView(driver, sideNavMenu);
            WebElementUtils.hoverOverWebelement(driver, sideNavMenu);
        }
    }

    /**
     * Method to check if the side menu is opened or not
     * @return the state of the side nav in bool
     */
    public boolean IsSideNavMenuOpened() {
        WaitUtils.waitForVisibleElement(sideNavMenu, driver);
        return WebElementUtils.isAttributePresentInWebelement
                (sideNavMenu, "class", "open");
    }

    /**
     * Method to select one of the side menu options
     * @param menuOption the category to select
     */
    public void selectOptionFromSideMenuNav(String menuOption) {
        this.setSideNavMenuState(true);
        WebElementUtils.clickElementWithTextFromListOfElements(menuOption, sideNavMenuOptions);
    }
}
