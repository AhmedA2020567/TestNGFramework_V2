package O3_utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String testName) throws Exception {
        System.out.println("takeScreenshot Utils ......." );

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
