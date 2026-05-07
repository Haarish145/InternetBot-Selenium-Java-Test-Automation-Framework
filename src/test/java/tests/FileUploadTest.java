package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UploadPage;
import utils.ConfigReader;

import java.nio.file.Path;

public class FileUploadTest extends BaseTest {
    @Test
    public void UPLOAD_TC_01_uploadValidFileDisplaysFilename() {
        String uploadFileName = ConfigReader.get("uploadFileName");
        UploadPage uploadPage = new UploadPage(driver);
        uploadPage.openFileUpload();
        uploadPage.uploadFile(resourcePath(uploadFileName));

        Assert.assertEquals(uploadPage.getUploadedFilename(), uploadFileName);
    }

    @Test
    public void UPLOAD_TC_02_verifyUploadPageTitle() {
        UploadPage uploadPage = new UploadPage(driver);
        uploadPage.openFileUpload();

        Assert.assertTrue(uploadPage.isUploadPageDisplayed(), "Upload page should load correctly.");
    }

    @Test
    public void UPLOAD_TC_03_uploadUnsupportedFileTypeHandledGracefully() {
        String unsupportedUploadFileName = ConfigReader.get("unsupportedUploadFileName");
        UploadPage uploadPage = new UploadPage(driver);
        uploadPage.openFileUpload();
        uploadPage.uploadFile(resourcePath(unsupportedUploadFileName));

        Assert.assertEquals(uploadPage.getUploadResultHeading(), "File Uploaded!");
        Assert.assertEquals(uploadPage.getUploadedFilename(), unsupportedUploadFileName);
    }

    private Path resourcePath(String fileName) {
        return Path.of("src", "test", "resources", fileName);
    }
}
