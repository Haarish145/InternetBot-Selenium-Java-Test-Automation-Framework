package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckboxPage;

public class CheckboxesAndDropdownsTest extends BaseTest {
    @Test
    public void CHECK_TC_01_checkCheckboxOneSelectsIt() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.openCheckboxes();
        checkboxPage.selectCheckboxOne();

        Assert.assertTrue(checkboxPage.isCheckboxOneSelected(), "Checkbox 1 should be selected.");
    }

    @Test
    public void CHECK_TC_02_toggleCheckboxTwoChangesItsState() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.openCheckboxes();

        Assert.assertTrue(checkboxPage.toggleCheckboxTwoAndReturnNewState(), "Checkbox 2 state should change.");
    }

    @Test
    public void DROP_TC_01_selectDropdownOptionOne() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.openDropdown();
        checkboxPage.selectOptionOne();

        Assert.assertTrue(checkboxPage.isOptionOneSelected(), "Dropdown should show Option 1 as selected.");
    }

    @Test
    public void DROP_TC_02_verifyDropdownOptionCount() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.openDropdown();

        Assert.assertEquals(checkboxPage.getDropdownOptionCount(), 3);
    }
}
