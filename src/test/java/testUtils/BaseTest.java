package testUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Base class for Testing containing the common methods for preparation
 * of the environment, url, broswer, screenshots
 * @author yavort
 */
public class BaseTest {
  //protected WebDriver driver;
   protected RemoteWebDriver driver;
    Properties properties = new Properties();

    /**
     * Method for getting the browser specified in the
     * properties file and then initializing it
     * @return returns the instance of the webDriver
     */
    public WebDriver initializeDriver() throws MalformedURLException {
        this.loadPropertiesfile();
        String grid = properties.getProperty("grid");
        String browserName =  properties.getProperty("browser");

    if (browserName.equals("chrome")) {
        WebDriverManager.chromedriver().setup();
        if(grid.equals("false")){
            driver = new ChromeDriver();
        }
        else {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName("chrome");
            desiredCapabilities.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        }
    }

    if (browserName.equals("firefox")) {
        WebDriverManager.firefoxdriver().setup();
        if(grid.equals("false")){
            driver = new FirefoxDriver();
        }
        else {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName("firefox");
            desiredCapabilities.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        }
    }

    if (browserName.equals("ie")) {
        WebDriverManager.edgedriver().setup();
        if(grid.equals("false")){
            driver = new EdgeDriver();
        }
        else {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName("edge");
            desiredCapabilities.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        }
    }


        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Method to load the properties configuration file
     */
    public void loadPropertiesfile() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:\\LeanplumHomeAssignment\\data.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to extract and combine the different parts of the url
     * like environment, baseUrl, etc
     * @return returns the result of combining the url as a string
     */
    public String getUrlFromProperies(){
        this.loadPropertiesfile();
        String urlResult = properties.getProperty("urlPrefix") + properties.getProperty("environment")
                +properties.getProperty("baseUrl");
        return urlResult;
    }

    /**
     * Method to get the path for storing the reports and screenshots
     * @param testCaseName the name of the executed testcase to store
     * @param driver the instance of the webDriver
     * @return return the path as a string
     * @throws IOException
     */
    public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String path = System.getProperty("user.dir") + "\\reports\\" + testCaseName + formatter.format(date) +".png";
        File screenshotFile = new File(path);
        FileUtils.copyFile(source, screenshotFile);
        return path;
    }
}
