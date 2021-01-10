package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;


public class ListenersUtil extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ReportsUtil.setReports();
    private static ThreadLocal<ExtentTest> threadExtent = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        threadExtent.set(test);
    }


    public void onTestSuccess(ITestResult result) {
        test = threadExtent.get().pass(String.valueOf(result.getStatus()));

    }


    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        Object testObject = result.getInstance();
        Class testClass = result.getTestClass().getRealClass();
        try {
            driver = (WebDriver) testClass.getDeclaredField("driver").get(testObject);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        test = threadExtent.get().fail(result.getThrowable());
        try {
            threadExtent.get().addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void onTestSkipped(ITestResult result) {
        test = threadExtent.get().skip("Test Skipped");
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }


    public void onTestFailedWithTimeout(ITestResult result) {

    }


    public void onStart(ITestContext context) {

    }


    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
