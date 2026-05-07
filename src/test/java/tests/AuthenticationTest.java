package tests;

import base.BaseTest;
import dataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.RetryAnalyzer;

public class AuthenticationTest extends BaseTest {
    @Test(dataProvider = "loginCredentials", dataProviderClass = LoginDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void AUTH_TC_01_02_verifyLoginWithCredentialSet(String username, String password, boolean validCredential) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openFormAuthentication();
        loginPage.login(username, password);

        if (validCredential) {
            Assert.assertTrue(loginPage.isSecureAreaDisplayed(), "Secure area should be displayed after valid login.");
        } else {
            Assert.assertTrue(loginPage.isInvalidLoginMessageDisplayed(), "Invalid login message should be displayed.");
        }
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void AUTH_TC_03_verifySuccessMessageAfterSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openFormAuthentication();
        loginPage.login(ConfigReader.get("validUsername"), ConfigReader.get("validPassword"));

        Assert.assertTrue(loginPage.isSuccessMessageDisplayed(), "Success alert message should appear.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void AUTH_TC_04_verifyLogoutRedirectsToLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openFormAuthentication();
        loginPage.login(ConfigReader.get("validUsername"), ConfigReader.get("validPassword"));
        loginPage.logout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed after logout.");
    }
}
