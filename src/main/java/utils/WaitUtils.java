package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;



/**
 * Class containing utility methods for selenium waiting
 * @author russ huber
 */
public final class WaitUtils {

    static Logger log = LogManager.getLogger(WaitUtils.class.getName());

    public static final int DEFAULT_WAIT_TIME_SECONDS =3;
    private static final int LONG_WAIT_TIME = 45; // in seconds
    private static final int WAIT_TIME = 10; // in seconds
    private static final int POLL_FREQUENCY_INTERVAL = 2; // in milli-seconds

    private static final List<Class<? extends Throwable>> IGNORE_EXCEPTIONS = Arrays.asList(NotFoundException.class,
            NoSuchElementException.class,
            StaleElementReferenceException.class);

    /**
     * Waits for the Javascript document ready state to be returned
     * @param driver  The WebDriver instance
     */
    public static void waitForDocumentReadyState(WebDriver driver) {

        ExpectedCondition<Boolean> pageLoadCondition =
                d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete");

        createWaitInstance(driver).until(pageLoadCondition);
    }


    /**
     * Waits for an element to appear
     * @param element  The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForVisibleElement(WebElement element, WebDriver driver) {
        return waitForVisibleElement(element, driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
    }

    /**
     * Waits for all in list to appear
     * @param elements  The list of Webelements to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static List<WebElement> waitForListOfElementsToBeVisible(List<WebElement> elements, WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * Waits for the WebElement to become visible
     * @param element  The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @param timeOutInSeconds  The timeout to wait for
     * @param pollIntervalInMs  The polling interval in ms
     * @return  The located WebElement instance
     */
    public static WebElement waitForVisibleElement(WebElement element, WebDriver driver, long timeOutInSeconds,
                                                   long pollIntervalInMs) {
        WebDriverWait wait = createWaitInstance(driver, timeOutInSeconds, pollIntervalInMs);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    /**
     * Waits for the WebElement to become invisible
     * @param element  The WebElement instance to wait for invisibility of
     * @param driver  The WebDriver instance
     * @return  The invisible WebElement instance
     */
    public static WebElement waitForInvisibilityOfElement(WebElement element, WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver);
        wait.until(not(visibilityOf(element)));
        return element;
    }


    /**
     * Waits for an element to become interactable
     * @param element The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForClickableElement(WebElement element, WebDriver driver) {

        WebDriverWait wait = createWaitInstance(driver);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Removes the implicit wait time
     * @param driver  the WebDriver instance to affect
     */
    public static void removeImplicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Re-instates the implicit wait time after it has been de-activated
     * @param driver  the WebDriver instance to affect
     */
    public static void restoreImplicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * Creates an instance of the WebDriverWait class
     * @param driver  The WebDriver instance source object
     * @return An instance of {@link WebDriverWait} bound to the source WebDriver object
     */
    private static WebDriverWait createWaitInstance(WebDriver driver) {
        return createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
    }

    /**
     * Creates an instance of the WebDriverWait class
     * @param driver  The WebDriver instance source object
     * @param timeOutInSeconds   The timeout to set (in seconds)
     * @param pollIntervalInMs   The interval to poll (in milliseconds)
     * @return An instance of {@link WebDriverWait} bound to the source WebDriver object
     */
    private static WebDriverWait createWaitInstance(WebDriver driver, long timeOutInSeconds, long pollIntervalInMs) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, pollIntervalInMs);
        wait.ignoreAll(IGNORE_EXCEPTIONS);
        wait.withMessage("The specified element cannot be located, timed out after " + timeOutInSeconds + " seconds");
        return wait;
    }

}
