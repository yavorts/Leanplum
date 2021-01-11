package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Class containing utility methods for operating with WebElements
 * @author yavort
 */
public class WebElementUtils {
    public static void clickElementWithTextFromListOfElements(String text, List<WebElement> listOfElements) {
        for (WebElement element : listOfElements) {
            if(element.getText().equals(text)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Method to get webElement with desired text from a list of webElements
     * @param text the text of the webElement
     * @param listOfElements the list of the webElements
     * @return return the filtered element as webElement
     */
    public static WebElement getElementWithTextFromListOfElements(String text, List<WebElement> listOfElements) {
        WebElement elementResult = null;
        for (WebElement element : listOfElements) {
            if(element.getText().equals(text)) {
                elementResult = element;
                break;
            }
        }
        return elementResult;
    }

    /**
     * Method to scroll to the desired element visibility
     * @param driver the instance of the WebDriver
     * @param element the element to scroll to
     */
    public static void scrollElementIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Method to determine if a webelement has a specified attribute
     * @param element the webElement
     * @param attributeType the type of the searched attribute
     * @param attributeText the text of the attribute
     * @return return result if the webElement has the attribute
     */
    public static boolean isAttributePresentInWebelement(WebElement element, String attributeType, String attributeText) {
        return element.getAttribute(attributeType).contains(attributeText);
    }

    /**
     * Method to hover the mouse over a specified element
     * @param driver the webDriver instance
     * @param element the webElement to hover over
     */
    public static void hoverOverWebelement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
