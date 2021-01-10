package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

public class SideNavigationMenu {
    WebDriver driver;
    @FindBy(css = ".side-nav-view")
    private WebElement sideNavMenu;

    @FindBy(css = ".icon+div p")
    private List<WebElement> sideNavMenuOptions;

    public SideNavigationMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    public void setSideNavMenuState(boolean state) {
        boolean currentStateRemember = this.IsSideNavMenuOpened();
        if (currentStateRemember != state) {
            WebElementUtils.scrollElementIntoView(driver, sideNavMenu);
            WebElementUtils.hoverOverWebelement(driver, sideNavMenu);
        }
    }

    public boolean IsSideNavMenuOpened() {
        WaitUtils.waitForVisibleElement(sideNavMenu, driver);
        return WebElementUtils.isAttributePresentInWebelement
                (sideNavMenu, "class", "open");
    }

    public void selectOptionFromSideMenuNav(String menuOption) {
        this.setSideNavMenuState(true);
        WebElementUtils.clickElementWithTextFromListOfElements(menuOption, sideNavMenuOptions);
    }
}
