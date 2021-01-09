package utils;

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

    public WebDriver initializeDriver(){
        Properties properties = new Properties();
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
        return driver;
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
