package pages;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertPage extends BasePage {
    private static final String JS_ALERT_RESULT = "You successfully clicked an alert";
    private static final String JS_CONFIRM_CANCEL_RESULT = "You clicked: Cancel";

    @FindBy(linkText = "JavaScript Alerts")
    private WebElement javaScriptAlertsLink;

    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    private WebElement jsAlertButton;

    @FindBy(xpath = "//button[text()='Click for JS Confirm']")
    private WebElement jsConfirmButton;

    @FindBy(xpath = "//button[text()='Click for JS Prompt']")
    private WebElement jsPromptButton;

    @FindBy(id = "result")
    private WebElement resultText;

    public AlertPage(WebDriver driver) {
        super(driver);
    }

    public void openJavaScriptAlerts() {
        clickElement(javaScriptAlertsLink);
    }

    public void acceptJsAlert() {
        clickElement(jsAlertButton);
        handleAlert().accept();
    }

    public void dismissJsConfirm() {
        clickElement(jsConfirmButton);
        handleAlert().dismiss();
    }

    public void enterPromptText(String text) {
        clickElement(jsPromptButton);
        Alert alert = handleAlert();
        alert.sendKeys(text);
        alert.accept();
    }

    public String getResultText() {
        return getText(resultText);
    }

    public boolean hasAlertAcceptedMessage() {
        return JS_ALERT_RESULT.equals(getResultText());
    }

    public boolean hasConfirmCancelledMessage() {
        return JS_CONFIRM_CANCEL_RESULT.equals(getResultText());
    }

    public boolean hasPromptText(String text) {
        return ("You entered: " + text).equals(getResultText());
    }
}
