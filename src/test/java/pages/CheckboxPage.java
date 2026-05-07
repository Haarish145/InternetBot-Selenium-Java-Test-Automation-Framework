package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckboxPage extends BasePage {
    @FindBy(linkText = "Checkboxes")
    private WebElement checkboxesLink;

    @FindBy(css = "#checkboxes input:nth-of-type(1)")
    private WebElement checkboxOne;

    @FindBy(css = "#checkboxes input:nth-of-type(2)")
    private WebElement checkboxTwo;

    @FindBy(linkText = "Dropdown")
    private WebElement dropdownLink;

    @FindBy(id = "dropdown")
    private WebElement dropdown;

    public CheckboxPage(WebDriver driver) {
        super(driver);
    }

    public void openCheckboxes() {
        clickElement(checkboxesLink);
    }

    public void selectCheckboxOne() {
        if (!isSelected(checkboxOne)) {
            clickElement(checkboxOne);
        }
    }

    public boolean isCheckboxOneSelected() {
        return isSelected(checkboxOne);
    }

    public boolean toggleCheckboxTwoAndReturnNewState() {
        boolean originalState = isSelected(checkboxTwo);
        clickElement(checkboxTwo);
        return isSelected(checkboxTwo) != originalState;
    }

    public void openDropdown() {
        clickElement(dropdownLink);
    }

    public void selectOptionOne() {
        selectDropdownByVisibleText(dropdown, "Option 1");
    }

    public String getSelectedDropdownOption() {
        return selectDropdown(dropdown).getFirstSelectedOption().getText();
    }

    public int getDropdownOptionCount() {
        return selectDropdown(dropdown).getOptions().size();
    }
}
