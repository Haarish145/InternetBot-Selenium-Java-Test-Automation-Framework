package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertPage;

public class JavaScriptAlertsTest extends BaseTest {
    private static final String PROMPT_TEXT = "InternetBot";

    @Test
    public void ALERT_TC_01_acceptJsAlertShowsSuccessfulAction() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.openJavaScriptAlerts();
        alertPage.acceptJsAlert();

        Assert.assertTrue(alertPage.hasAlertAcceptedMessage(), "Alert result should confirm the alert was accepted.");
    }

    @Test
    public void ALERT_TC_02_dismissJsConfirmShowsDismissedMessage() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.openJavaScriptAlerts();
        alertPage.dismissJsConfirm();

        Assert.assertTrue(alertPage.hasConfirmCancelledMessage(), "Confirm result should show that cancel was clicked.");
    }

    @Test
    public void ALERT_TC_03_enterTextInJsPromptDisplaysEnteredText() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.openJavaScriptAlerts();
        alertPage.enterPromptText(PROMPT_TEXT);

        Assert.assertTrue(alertPage.hasPromptText(PROMPT_TEXT), "Prompt result should include the entered text.");
    }

    @Test
    public void ALERT_TC_04_resultTextUpdatesCorrectlyAcrossAlertActions() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.openJavaScriptAlerts();
        alertPage.acceptJsAlert();
        Assert.assertTrue(alertPage.hasAlertAcceptedMessage(), "Alert result should confirm the alert was accepted.");

        alertPage.dismissJsConfirm();
        Assert.assertTrue(alertPage.hasConfirmCancelledMessage(), "Confirm result should show that cancel was clicked.");
    }
}
