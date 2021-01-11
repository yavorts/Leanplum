package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

/**
 * Class for the date picker or calendar options.
 * Contains all options of the calendar and how to operate with it
 * @author yavort
 */
public class DatePicker {
    WebDriver driver;

    @FindBy(css = ".current span")
    private WebElement current;

    @FindBy(css = ".lp-date-picker")
    private WebElement datePicker;

    @FindBy(css = ".nav.next")
    private WebElement arrowNext;

    @FindBy(css = ".nav.prev")
    private WebElement arrowPrevious;

    @FindBy (css = ".date-time input")
    private WebElement dateTimeInput;

    @FindBy(css = ".lp-day:not(.disabled) span")
    private List<WebElement> availableDaysInCalendar;

    /**
     * The constructor of the DatePicker class
     * @param driver The WebDriver instance
     */
    public DatePicker(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    /**
     * Method to open the calendar
     */
    public void openDatePicker() {
        WaitUtils.waitForClickableElement(datePicker, driver);
        datePicker.click();
    }

    /**
     * Extracts the month from the date set in the calendar
     * @return Returns the current month as a string
     */
    public String getCurrentMonth() {
        WaitUtils.waitForClickableElement(current, driver);
        String textInCurrent = current.getText();
        return textInCurrent.substring(0, textInCurrent.length() - 4).trim();
    }

    /**
     * Extracts the year from the date set in the calendar
     * @return Returns the current year as a string
     */
    public String getCurrentYear() {
        WaitUtils.waitForClickableElement(current, driver);
        String textInCurrent = current.getText();
        return textInCurrent.substring(textInCurrent.length() - 4, textInCurrent.length()).trim();
    }

    /**
     * Represents the months of the year as a string
     * @param month the given month to be represented as an int
     * @return the month represented as an int value
     */
    public int getMonthInInt(String month) {
        int monthInInt;
        switch (month) {
            case "January":
                monthInInt = 1;
                break;
            case "February":
                monthInInt = 2;
                break;
            case "March":
                monthInInt = 3;
                break;
            case "April":
                monthInInt = 4;
                break;
            case "May":
                monthInInt = 5;
                break;
            case "June":
                monthInInt = 6;
                break;
            case "July":
                monthInInt = 7;
                break;
            case "August":
                monthInInt = 8;
                break;
            case "September":
                monthInInt = 9;
                break;
            case "October":
                monthInInt = 10;
                break;
            case "November":
                monthInInt = 11;
                break;
            case "December":
                monthInInt = 12;
                break;
            default:
                monthInInt = 0;
                break;
        }
        return monthInInt;
    }

    /**
     * Method to click the next month/page of the calendar
     */
    public void clickNextPage() {
        WaitUtils.waitForClickableElement(arrowNext, driver);
        arrowNext.click();
    }

    /**
     * Method to click the prev month/page of the calendar
     */
    public void clickPrevPage() {
        WaitUtils.waitForClickableElement(arrowPrevious, driver);
        arrowPrevious.click();
    }

    /**
     * Method to go forward to the specified year and month in the calendar
     * @param month the desired month to search in the calendar
     * @param year the desired year to search in the calendar
     */
    public void getToDesiredYearMonth(String month, int year) {
        int desiredMonthInNumeric = this.getMonthInInt(month);
        int currentMonthNumeric = this.getMonthInInt(getCurrentMonth());
        int currentYear = Short.parseShort(this.getCurrentYear());
        int differenceInYears = (short) (year - currentYear);
        int differenceInMonths = (short) (desiredMonthInNumeric - currentMonthNumeric);
        if (differenceInYears > 0) {
            for (int i = 0; i < differenceInYears; i++) {
                for (int j = 0; j < 11; j++) {
                    this.clickNextPage();
                }
            }
        }
        if(differenceInMonths>0) {
            for (int j = 0; j < differenceInMonths; j++) {
                this.clickNextPage();
            }
        }
    }

    /**
     * Sets the desired day of the month in the calendar
     * @param day The day number represented as a string
     */
    public void setDayToDatePicker(String day) {
        WaitUtils.waitForListOfElementsToBeVisible(availableDaysInCalendar, driver);
        WebElementUtils.clickElementWithTextFromListOfElements(day, availableDaysInCalendar);
    }

    /**
     * Method to directly enter a desired date in the calendar
     * @param date The desired date to enter
     */
    public void setDateToDatePicker(String date) {
        WaitUtils.waitForClickableElement(dateTimeInput, driver);
        dateTimeInput.clear();
        dateTimeInput.sendKeys(date);
    }
}
