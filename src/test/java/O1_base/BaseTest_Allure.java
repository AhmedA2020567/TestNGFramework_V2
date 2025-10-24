package O1_base;

import O2_tests.AllureListener;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Listeners(AllureListener.class)
public class BaseTest_Allure {

    public static WebDriver driver;

    @BeforeSuite
    public void clearAllureResults() throws Exception {
        // Clear allure-results directory before starting tests
        // Ahmed

//        String allureResultsDir = System.getProperty("user.dir") + "/allure-results/";
//        File directory = new File(allureResultsDir);
//
//        if (directory.exists()) {
//            FileUtils.cleanDirectory(directory);
//            System.out.println("🧹 Allure results directory cleared");
//        } else {
//            directory.mkdirs();
//            System.out.println("📁 Allure results directory created");
//        }
    }


    @BeforeClass
    @Step("Setup: Initialize WebDriver and navigate to application")
    public void setup() throws Exception {
        driver = DriverFactory.initDriver("chrome");
        driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");
    }


    @AfterClass
    @Step("Teardown: Close browser and quit driver")
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        DriverFactory.quitDriver();
    }

    /**
     * Capture screenshot and return file path
     */
    public static String captureScreenshot(String testName) throws Exception {
        // Create screenshots directory
        String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate unique screenshot name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = screenshotDir + testName + "_" + timestamp + ".png";

        // Take screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotPath);
        FileUtils.copyFile(source, destination);

        System.out.println("📸 Screenshot saved: " + screenshotPath);
        return screenshotPath;
    }


}