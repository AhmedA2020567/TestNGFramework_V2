package O1_base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTV1 {

    public static WebDriver driver;

    @BeforeClass
    public void setup() {
        // Initialize WebDriver from DriverFactory
        driver = DriverFactory.initDriver("chrome");
        driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        DriverFactory.quitDriver();
    }
}