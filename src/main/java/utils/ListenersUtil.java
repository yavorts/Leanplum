package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersUtil implements ITestListener {
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

        test = threadExtent.get().fail(result.getThrowable());
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
