package O2_tests;
import O1_base.BaseTest;
import O1_base.BaseTest_Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import O3_utils.ScreenshotUtils;


public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest_Allure.driver;
        if (driver != null) {
            try {
                takeFailureScreenshot(driver, result.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            System.out.println("Driver is NUll");
    }

    private void takeFailureScreenshot(WebDriver driver, String testName) throws Exception {
        System.out.println(testName);
        ScreenshotUtils.takeScreenshot(driver, testName);
    }


    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
