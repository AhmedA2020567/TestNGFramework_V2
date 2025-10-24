package O1_base;

import O2_tests.AllureListener;
import O3_utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Listeners(AllureListener.class)
//@Listeners(O2_tests.TestListener.class) //This is for the Extended Reports
public class BaseTest {

//    @BeforeSuite
//    @BeforeClass  ← Your setup() is here, but ExtentTest is null!
//    @BeforeMethod ← ExtentTest is created here
//    @Test
//    @AfterMethod
//    @AfterClass
//    @AfterSuite

    public static WebDriver driver;

    // ExtentReports objects
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /**
     * Setup ExtentReports before the entire test suite
     */
    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
        System.out.println("✅ Extent Report initialized");
    }

    /**
     * Create a test entry in the report before each test method
     */
    @BeforeMethod
    public void setupTest(Method method) {
        // Create test in report with method name
        ExtentTest test = extent.createTest(method.getName());
        test.assignAuthor("QA Team");
        test.assignCategory("Regression");
        extentTest.set(test);

        // Log test start and browser info
        getExtentTest().info("▶️ Test Started: " + method.getName());
        getExtentTest().info("🌐 Browser: Chrome");
        getExtentTest().info("🔗 URL: https://www.tutorialspoint.com/selenium/practice/text-box.php");
    }

    @BeforeClass
    @Step("Setup: Initialize WebDriver and navigate to application") // This is adedd for the Allure reports
    public void setup() {
        // Initialize WebDriver from DriverFactory (logging will happen in @BeforeMethod)
        driver = DriverFactory.initDriver("chrome");
        driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");
    }

    /**
     * Log test results and capture screenshots on failure
     */
    @AfterMethod
    public void logTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            getExtentTest().log(Status.FAIL, "❌ TEST FAILED: " + result.getName());
            getExtentTest().log(Status.FAIL, "📋 Failure Reason: " + result.getThrowable().getMessage());

            // Capture and attach screenshot
            String screenshotPath = captureScreenshot(result.getName());
            if (screenshotPath != null) {
                getExtentTest().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
                getExtentTest().info("📸 Screenshot attached");
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            getExtentTest().log(Status.PASS, "✅ TEST PASSED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            getExtentTest().log(Status.SKIP, "⏭️ TEST SKIPPED: " + result.getName());
            if (result.getThrowable() != null) {
                getExtentTest().log(Status.SKIP, "Skip Reason: " + result.getThrowable().getMessage());
            }
        }

        // Log execution time
        long duration = (result.getEndMillis() - result.getStartMillis()) / 1000;
        getExtentTest().info("⏱️ Execution Time: " + duration + " seconds");
    }

    @AfterClass
    @Step("Teardown: Close browser and quit driver") // This is added for Allure reporting
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        getExtentTest().info("🔚 Closing browser");
        DriverFactory.quitDriver();
    }

    /**
     * Flush the report after all tests complete
     */
    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("✅ Extent Report generated successfully!");
        }
    }

    /**
     * Get the current ExtentTest instance
     */
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    /**
     * Capture screenshot and return file path
     */
    public static String captureScreenshot(String testName) {
        try {
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
        } catch (Exception e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    // ==================== HELPER METHODS FOR LOGGING ====================

    /**
     * Log an info step in the report
     */
    public static void logStep(String stepDescription) {
        getExtentTest().info("📝 " + stepDescription);
    }

    /**
     * Log a pass message
     */
    public static void logPass(String message) {
        getExtentTest().pass("✅ " + message);
    }

    /**
     * Log a fail message
     */
    public static void logFail(String message) {
        getExtentTest().fail("❌ " + message);
    }

    /**
     * Log a warning message
     */
    public static void logWarning(String message) {
        getExtentTest().warning("⚠️ " + message);
    }

    /**
     * Log with custom status
     */
    public static void log(Status status, String message) {
        getExtentTest().log(status, message);
    }
}