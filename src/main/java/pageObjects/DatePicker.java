package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

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

    public DatePicker(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

    public void openDatePicker() {
        WaitUtils.waitForClickableElement(datePicker, driver);
        datePicker.click();
    }

    public String getCurrentMonth() {
        WaitUtils.waitForClickableElement(current, driver);
        String textInCurrent = current.getText();
        return textInCurrent.substring(0, textInCurrent.length() - 4).trim();
    }

    public String getCurrentYear() {
        WaitUtils.waitForClickableElement(current, driver);
        String textInCurrent = current.getText();
        return textInCurrent.substring(textInCurrent.length() - 4, textInCurrent.length()).trim();
    }

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

    public void clickNextPage() {
        WaitUtils.waitForClickableElement(arrowNext, driver);
        arrowNext.click();
    }

    public void clickPrevPage() {
        WaitUtils.waitForClickableElement(arrowPrevious, driver);
        arrowPrevious.click();
    }

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

    public void setDayToDatePicker(String day) {
        WaitUtils.waitForListOfElementsToBeVisible(availableDaysInCalendar, driver);
        WebElementUtils.clickElementWithTextFromListOfElements(day, availableDaysInCalendar);
    }

    public void setDateToDatePicker(String date) {
        WaitUtils.waitForClickableElement(dateTimeInput, driver);
        dateTimeInput.clear();
        dateTimeInput.sendKeys(date);
    }
}
