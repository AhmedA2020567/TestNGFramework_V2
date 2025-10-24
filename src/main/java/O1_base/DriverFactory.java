package O1_base;
import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    // ThreadLocal to support parallel execution (best practice)
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            // Required for CI/CD environments
            options.addArguments("--headless=new");  // Run without GUI
            options.addArguments("--no-sandbox");     // Bypass OS security model
            options.addArguments("--disable-dev-shm-usage");  // Overcome limited resource problems
            options.addArguments("--disable-gpu");    // Disable GPU hardware acceleration
            options.addArguments("--window-size=1920,1080");  // Set window size
            options.addArguments("--remote-allow-origins=*");  // Allow remote origins
            driver.set(new ChromeDriver(options));
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver());
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}

