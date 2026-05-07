package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UploadPage;
import utils.ConfigReader;
import utils.RetryAnalyzer;

import java.nio.file.Path;

public class FileUploadTest extends BaseTest {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void UPLOAD_TC_01_uploadValidFileDisplaysFilename() {
        UploadPage uploadPage = new UploadPage(driver);
        uploadPage.openFileUpload();
        uploadPage.uploadFile(resourcePath(ConfigReader.get("uploadFileName")));

        Assert.assertEquals(uploadPage.getUploadedFilename(), ConfigReader.get("uploadFileName"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void UPLOAD_TC_02_verifyUploadPageTitle() {
        UploadPage uploadPage = new UploadPage(driver);
        uploadPage.openFileUpload();

        Assert.assertTrue(uploadPage.isUploadPageDisplayed(), "Upload page should load correctly.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void UPLOAD_TC_03_uploadUnsupportedFileTypeHandledGracefully() {
        UploadPage uploadPage = new UploadPage(driver);
        uploadPage.openFileUpload();
        uploadPage.uploadFile(resourcePath(ConfigReader.get("unsupportedUploadFileName")));

        Assert.assertEquals(uploadPage.getUploadResultHeading(), "File Uploaded!");
        Assert.assertEquals(uploadPage.getUploadedFilename(), ConfigReader.get("unsupportedUploadFileName"));
    }

    private Path resourcePath(String fileName) {
        return Path.of("src", "test", "resources", fileName);
    }
}
