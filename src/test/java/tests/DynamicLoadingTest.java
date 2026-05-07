package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DynamicPage;
import utils.RetryAnalyzer;

public class DynamicLoadingTest extends BaseTest {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void DYNAMIC_TC_01_clickStartAndWaitForDynamicContent() {
        DynamicPage dynamicPage = new DynamicPage(driver);
        dynamicPage.openDynamicLoadingExample();
        dynamicPage.startDynamicLoading();

        Assert.assertFalse(dynamicPage.waitForLoadedText().isBlank(), "Dynamic text should appear.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void DYNAMIC_TC_02_verifyLoadedTextIsHelloWorld() {
        DynamicPage dynamicPage = new DynamicPage(driver);
        dynamicPage.openDynamicLoadingExample();
        dynamicPage.startDynamicLoading();

        Assert.assertEquals(dynamicPage.waitForLoadedText(), "Hello World!");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void DYNAMIC_TC_03_refreshDisappearingElementsUntilGalleryAppears() {
        DynamicPage dynamicPage = new DynamicPage(driver);
        dynamicPage.openDisappearingElements();

        Assert.assertTrue(dynamicPage.refreshUntilGalleryAppears(), "Disappearing element should reappear.");
    }
}
