package testUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class BaseTest {
   protected WebDriver driver;
    Properties properties = new Properties();

    public WebDriver initializeDriver(){
        this.loadPropertiesfile();
        String browserName =  properties.getProperty("browser");

        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        if(browserName.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new ChromeDriver();
        }

        if(browserName.equals("ie")){
            WebDriverManager.edgedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

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

    public String getUrlFromProperies(){
        this.loadPropertiesfile();
        String urlResult = properties.getProperty("urlPrefix") + properties.getProperty("environment")
                +properties.getProperty("baseUrl");
        return urlResult;
    }

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
