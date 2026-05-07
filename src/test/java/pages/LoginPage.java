package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(linkText = "Form Authentication")
    private WebElement formAuthenticationLink;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    @FindBy(css = "a.button.secondary.radius")
    private WebElement logoutButton;

    @FindBy(css = "div.example h2")
    private WebElement pageHeading;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openFormAuthentication() {
        clickElement(formAuthenticationLink);
    }

    public void login(String username, String password) {
        typeText(usernameInput, username);
        typeText(passwordInput, password);
        clickElement(loginButton);
    }

    public boolean isSecureAreaDisplayed() {
        return getText(pageHeading).contains("Secure Area");
    }

    public boolean isLoginPageDisplayed() {
        return waitUtility.untilUrlContains("/login") && getText(pageHeading).contains("Login Page");
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public boolean isSuccessMessageDisplayed() {
        return getFlashMessage().contains("You logged into a secure area!");
    }

    public boolean isInvalidLoginMessageDisplayed() {
        String message = getFlashMessage();
        return message.contains("Your username is invalid!") || message.contains("Your password is invalid!");
    }

    public void logout() {
        clickElement(logoutButton);
    }
}
