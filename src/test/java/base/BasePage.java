package base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.ConfigReader;
import utils.WaitUtility;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WaitUtility waitUtility;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtility = new WaitUtility(driver);
        PageFactory.initElements(driver, this);
    }

    protected void openHomePage() {
        driver.navigate().to(ConfigReader.get("baseUrl"));
    }

    protected WebElement waitForElement(WebElement element) {
        return waitUtility.untilVisible(element);
    }

    protected void clickElement(WebElement element) {
        waitUtility.untilClickable(element).click();
    }

    protected void typeText(WebElement element, String text) {
        WebElement visibleElement = waitForElement(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    protected void attachFile(WebElement element, String absolutePath) {
        waitForElement(element).sendKeys(absolutePath);
    }

    protected String getText(WebElement element) {
        return waitForElement(element).getText();
    }

    protected boolean isDisplayed(WebElement element) {
        return waitForElement(element).isDisplayed();
    }

    protected boolean isSelected(WebElement element) {
        return waitForElement(element).isSelected();
    }

    protected void selectDropdownByVisibleText(WebElement element, String visibleText) {
        new Select(waitForElement(element)).selectByVisibleText(visibleText);
    }

    protected Select selectDropdown(WebElement element) {
        return new Select(waitForElement(element));
    }

    protected Alert handleAlert() {
        return waitUtility.untilAlertPresent();
    }
}
