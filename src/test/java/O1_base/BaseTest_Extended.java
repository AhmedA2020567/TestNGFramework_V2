package O1_base;

import O3_utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest_Extended {

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
     * Initialize WebDriver before each test class
     */
    @BeforeClass
    public void setup() {
        driver = DriverFactory.initDriver("chrome");
        driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");
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

    /**
     * Log test results after each test method
     */
    @AfterMethod
    public void logTestResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            getExtentTest().log(Status.FAIL, "❌ TEST FAILED: " + result.getName());
            getExtentTest().log(Status.FAIL, "📋 Failure Reason: " + result.getThrowable().getMessage());

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

    /**
     * Clean up after all tests in the class complete
     */
    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);

        // Safe cleanup - no extent logging here as extentTest might be null
        if (driver != null) {
            DriverFactory.quitDriver();
            System.out.println("🔚 Browser closed");
        }
    }

    /**
     * Flush the report after all tests complete
     */
    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("✅ Extent Report generated successfully!");
            System.out.println("📊 Report location: " + System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        }
    }

    /**
     * Get the current ExtentTest instance
     */
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    // ==================== HELPER METHODS FOR LOGGING ====================

    /**
     * Log an info step in the report
     */
    public static void logStep(String stepDescription) {
        if (getExtentTest() != null) {
            getExtentTest().info("📝 " + stepDescription);
        }
    }

    /**
     * Log a pass message
     */
    public static void logPass(String message) {
        if (getExtentTest() != null) {
            getExtentTest().pass("✅ " + message);
        }
    }

    /**
     * Log a fail message
     */
    public static void logFail(String message) {
        if (getExtentTest() != null) {
            getExtentTest().fail("❌ " + message);
        }
    }

    /**
     * Log a warning message
     */
    public static void logWarning(String message) {
        if (getExtentTest() != null) {
            getExtentTest().warning("⚠️ " + message);
        }
    }

    /**
     * Log with custom status
     */
    public static void log(Status status, String message) {
        if (getExtentTest() != null) {
            getExtentTest().log(status, message);
        }
    }
}