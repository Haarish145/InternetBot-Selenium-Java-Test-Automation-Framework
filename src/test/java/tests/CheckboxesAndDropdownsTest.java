package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckboxPage;
import utils.RetryAnalyzer;

public class CheckboxesAndDropdownsTest extends BaseTest {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void CHECK_TC_01_checkCheckboxOneSelectsIt() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.openCheckboxes();
        checkboxPage.selectCheckboxOne();

        Assert.assertTrue(checkboxPage.isCheckboxOneSelected(), "Checkbox 1 should be selected.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void CHECK_TC_02_toggleCheckboxTwoChangesItsState() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.openCheckboxes();

        Assert.assertTrue(checkboxPage.toggleCheckboxTwoAndReturnNewState(), "Checkbox 2 state should change.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void DROP_TC_01_selectDropdownOptionOne() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.openDropdown();
        checkboxPage.selectOptionOne();

        Assert.assertEquals(checkboxPage.getSelectedDropdownOption(), "Option 1");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void DROP_TC_02_verifyDropdownOptionCount() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.openDropdown();

        Assert.assertEquals(checkboxPage.getDropdownOptionCount(), 3);
    }
}
