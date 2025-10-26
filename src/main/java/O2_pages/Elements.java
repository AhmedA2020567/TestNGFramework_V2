package O2_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Elements {

    private WebDriver driver;

    // ===== Locators =====
    private By TextBox = By.xpath("//a[@href='text-box.php']");
    private By fullNameInput = By.id("fullname");
    private By emailInput = By.id("email");
    private By addressTextarea = By.id("address");
    private By passwordInput = By.id("password");
    private By submitBtn = By.cssSelector(".btn.btn-primary");
    private By checkBox = By.xpath("//a[@href='check-box.php']");
    private By checkbox_Main_1 = By.id("c_bs_1");


    private By webtables = By.xpath("//a[@href='webtables.php']");
    private By editIcons = By.xpath("(//a[@title='editt' and @class='edit-wrap'])[2]");
    private By fName = By.xpath("(//input[@id='firstname' and contains(@class,'form-control')])[2]");


    // ===== Constructor =====
    public Elements(WebDriver driver) {
        this.driver = driver;
    }

    // ===== Page Actions =====
    public void clickOnTextBox() {
        driver.findElement(TextBox).click();
    }

    public void enterFullName(String fullName) {
        driver.findElement(fullNameInput).clear();
        driver.findElement(fullNameInput).sendKeys(fullName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterAddress(String address) {
        driver.findElement(addressTextarea).clear();
        driver.findElement(addressTextarea).sendKeys(address);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void submitBtn() {
        driver.findElement(submitBtn).click();
    }

    public void clickOnCheckBox() {
        driver.findElement(checkBox).click();
    }

    public void clickOnCheckBox_Main() {
        driver.findElement(checkbox_Main_1).click();
    }

    public void clickOnEdit() throws InterruptedException {
        driver.findElement(webtables).click();
        Thread.sleep(2000);
        driver.findElement(editIcons).click();
        Thread.sleep(2000);

    }

    public void fillFirstName(String firstName) {
        driver.findElement(fName).sendKeys(firstName);
    }


}
