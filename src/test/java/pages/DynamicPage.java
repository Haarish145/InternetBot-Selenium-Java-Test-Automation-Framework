package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicPage extends BasePage {
    @FindBy(linkText = "Dynamic Loading")
    private WebElement dynamicLoadingLink;

    @FindBy(linkText = "Example 1: Element on page that is hidden")
    private WebElement hiddenElementExampleLink;

    @FindBy(css = "#start button")
    private WebElement startButton;

    @FindBy(id = "finish")
    private WebElement finishContainer;

    @FindBy(linkText = "Disappearing Elements")
    private WebElement disappearingElementsLink;

    @FindBy(linkText = "Gallery")
    private WebElement galleryLink;

    @FindBy(linkText = "Dynamic Content")
    private WebElement dynamicContentLink;

    @FindBy(css = ".large-10.columns")
    private WebElement firstDynamicContentText;

    public DynamicPage(WebDriver driver) {
        super(driver);
    }

    public void openDynamicLoadingExample() {
        clickElement(dynamicLoadingLink);
        clickElement(hiddenElementExampleLink);
    }

    public void startDynamicLoading() {
        clickElement(startButton);
    }

    public String waitForLoadedText() {
        return waitUtility.fluentUntil(ExpectedConditions.visibilityOf(finishContainer)).getText();
    }

    public void openDisappearingElements() {
        driver.navigate().to(utils.ConfigReader.get("baseUrl"));
        clickElement(disappearingElementsLink);
    }

    public boolean refreshUntilGalleryAppears() {
        return waitUtility.fluentUntil(driverInstance -> {
            try {
                if (galleryLink.isDisplayed()) {
                    return true;
                }
                driver.navigate().refresh();
                return false;
            } catch (Exception exception) {
                driver.navigate().refresh();
                return false;
            }
        });
    }

    public void openDynamicContent() {
        driver.navigate().to(utils.ConfigReader.get("baseUrl"));
        clickElement(dynamicContentLink);
    }

    public boolean isDynamicContentVisible() {
        return isDisplayed(firstDynamicContentText);
    }
}
