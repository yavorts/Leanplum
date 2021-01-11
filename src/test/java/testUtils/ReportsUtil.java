package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Class for creating directories for setting and storing reports
 */
public class ReportsUtil {
    static ExtentReports extentReports;

    public static ExtentReports setReports() {
        String path = System.getProperty("user.dir") + "\\extentReportFile.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Test Results");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
