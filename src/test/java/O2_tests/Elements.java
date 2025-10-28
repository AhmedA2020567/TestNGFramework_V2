package O2_tests;

import O1_base.BaseTest;
import O1_base.BaseTest_Allure;
import O1_base.BaseTest_Extended;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(AllureListener.class)
@Epic("Web Application Testing")
@Feature("Text Box Feature")
//@Listeners(O2_tests.TestListener.class)
public class Elements extends BaseTest_Allure {

    @Test(priority = 1, groups = {"regression"})
//    @Description("Verify text box is displayed on the page")
//    @Severity(SeverityLevel.CRITICAL)
//    @Story("Text Box Validation")
//    @Step("Test: Verify text box presence")
    public void TextBox() throws InterruptedException {
        O2_pages.Elements page = new O2_pages.Elements(driver);
        Thread.sleep(500);
        Thread.sleep(500);
        page.clickOnTextBox();
        Thread.sleep(1000);
        page.enterFullName("John Doe");
        Thread.sleep(1000);
        page.enterEmail("john.doe@example.com");
        Thread.sleep(1000);
        page.enterAddress("123 Main Street, Cairo, Egypt");
        Thread.sleep(1000);
        page.enterPassword("Secure@12355");
        //page.submitBtn();
        //Master Branch V1
        // Remote Main Branch
        // Test
    }

    @Test(priority = 2)
//    @Description("Verify CheckBox is displayed on the page")
//    @Severity(SeverityLevel.CRITICAL)
//    @Story("CheckBox Validation")
//    @Step("Test: Verify CheckBox presence")
    public void CheckBox() throws InterruptedException {
        Thread.sleep(1000);
        O2_pages.Elements page = new O2_pages.Elements(driver);
        Thread.sleep(1000);
        page.clickOnCheckBox();
        Thread.sleep(1000);
        page.clickOnCheckBox_Main();
    }

    @Test(priority = 3)
//    @Description("Verify webtable displayed on the page")
//    @Severity(SeverityLevel.CRITICAL)
//    @Story("webtable Validation")
//    @Step("Test: Verify webtable presence")
    public void webtable() throws InterruptedException {
        Thread.sleep(1000);
        O2_pages.Elements page = new O2_pages.Elements(driver);
        Thread.sleep(1000);
        page.clickOnEdit();
        Thread.sleep(1000);
        page.fillFirstName("Ahmed Ashraf");
        //Assert.assertTrue(false, "This assertion will always fail");

    }


}