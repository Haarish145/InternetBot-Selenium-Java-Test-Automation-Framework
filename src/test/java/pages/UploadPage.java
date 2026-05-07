package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.file.Path;

public class UploadPage extends BasePage {
    @FindBy(linkText = "File Upload")
    private WebElement fileUploadLink;

    @FindBy(id = "file-upload")
    private WebElement fileInput;

    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    @FindBy(css = "div.example h3")
    private WebElement heading;

    @FindBy(id = "uploaded-files")
    private WebElement uploadedFiles;

    public UploadPage(WebDriver driver) {
        super(driver);
    }

    public void openFileUpload() {
        clickElement(fileUploadLink);
    }

    public boolean isUploadPageDisplayed() {
        return getText(heading).contains("File Uploader");
    }

    public void uploadFile(Path filePath) {
        attachFile(fileInput, filePath.toAbsolutePath().toString());
        clickElement(uploadButton);
    }

    public String getUploadResultHeading() {
        return getText(heading);
    }

    public String getUploadedFilename() {
        return getText(uploadedFiles);
    }
}
