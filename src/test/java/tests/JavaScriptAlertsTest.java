package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertPage;
import utils.RetryAnalyzer;

public class JavaScriptAlertsTest extends BaseTest {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void ALERT_TC_01_acceptJsAlertShowsSuccessfulAction() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.openJavaScriptAlerts();
        alertPage.acceptJsAlert();

        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void ALERT_TC_02_dismissJsConfirmShowsDismissedMessage() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.openJavaScriptAlerts();
        alertPage.dismissJsConfirm();

        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void ALERT_TC_03_enterTextInJsPromptDisplaysEnteredText() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.openJavaScriptAlerts();
        alertPage.enterPromptText("InternetBot");

        Assert.assertEquals(alertPage.getResultText(), "You entered: InternetBot");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void ALERT_TC_04_resultTextUpdatesCorrectlyAcrossAlertActions() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.openJavaScriptAlerts();
        alertPage.acceptJsAlert();
        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");

        alertPage.dismissJsConfirm();
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }
}
