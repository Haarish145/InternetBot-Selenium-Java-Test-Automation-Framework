package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility {
    private final WebDriver driver;
    private final int timeout;

    public WaitUtility(WebDriver driver) {
        this.driver = driver;
        this.timeout = ConfigReader.getInt("timeout");
    }

    public WebElement untilVisible(WebElement element) {
        return webDriverWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public WebElement untilClickable(WebElement element) {
        return webDriverWait().until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public boolean untilInvisible(WebElement element) {
        return webDriverWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public Alert untilAlertPresent() {
        return webDriverWait().until(ExpectedConditions.alertIsPresent());
    }

    public <T> T fluentUntil(ExpectedCondition<T> condition) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(TimeoutException.class)
                .until(condition);
    }

    private WebDriverWait webDriverWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        return wait;
    }
}
