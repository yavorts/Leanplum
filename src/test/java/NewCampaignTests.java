import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewCampaignTests extends  BaseTest{
        WebDriver driver;


        @BeforeTest
        public void setupTest(){
            driver =  initializeDriver();
            driver.get("https://www.leanplum.com/dashboard#/4894110982930432/messaging?dates=lm");
        }

        @Test
        public void createCampaign() {
            // Start the test using the ExtentTest class object.




            driver.manage().window().maximize();



            // get title.
            String title = driver.getTitle();



            // Verify title.
            Assert.assertTrue(title.contains("Login to Leanplum"));



        }

        @AfterTest
        public void tearDown() {

            // Close application.
            driver.quit();



        }
    }
