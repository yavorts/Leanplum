package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class WebElementUtils {
    public static void clickElementWithTextFromListOfElements(String text, List<WebElement> listOfElements) {
        for (WebElement element : listOfElements) {
            if(element.getText().equals(text)) {
                element.click();
                break;
            }
        }
    }

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

    public static void scrollElementIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static boolean isAttributePresentInWebelement(WebElement element, String attributeType, String attributeText) {
        return element.getAttribute(attributeType).contains(attributeText);
    }

    public static void hoverOverWebelement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
