package O1_base;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


//@Listeners(AllureListener.class)
public class BaseTest_Allure {

    public static WebDriver driver;

    @BeforeSuite
    public void clearAllureResults() throws Exception {
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
//    public static String captureScreenshot(String testName) throws Exception {
//        // Create screenshots directory
//        String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
//        File directory = new File(screenshotDir);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//
//        // Generate unique screenshot name
//        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String screenshotPath = screenshotDir + testName + "_" + timestamp + ".png";
//
//        // Take screenshot
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        File destination = new File(screenshotPath);
//        FileUtils.copyFile(source, destination);
//
//        System.out.println("📸 Screenshot saved: " + screenshotPath);
//        return screenshotPath;
//    }


}