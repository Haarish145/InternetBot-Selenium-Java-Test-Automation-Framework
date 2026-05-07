# InternetBot Test Modules and Scope

InternetBot covers 18 explicitly defined Selenium test cases for The Internet Herokuapp.

## Test Case Breakdown

| Module | Number of Test Cases |
| --- | ---: |
| Authentication | 4 |
| JavaScript Alerts | 4 |
| Checkboxes and Dropdowns | 4 |
| File Upload | 3 |
| Dynamic Loading | 3 |
| Total | 18 |

## Authentication

| ID | Scenario | Expected Result | Implementation |
| --- | --- | --- | --- |
| AUTH_TC_01 | Verify successful login with valid credentials | Secure area page should be displayed | `AuthenticationTest.AUTH_TC_01_02_verifyLoginWithCredentialSet` with valid data |
| AUTH_TC_02 | Verify login failure with invalid credentials | Invalid username/password message should appear | `AuthenticationTest.AUTH_TC_01_02_verifyLoginWithCredentialSet` with invalid data |
| AUTH_TC_03 | Verify success message after successful login | Success alert message should appear | `AuthenticationTest.AUTH_TC_03_verifySuccessMessageAfterSuccessfulLogin` |
| AUTH_TC_04 | Verify logout redirects to login page | Login page should be displayed | `AuthenticationTest.AUTH_TC_04_verifyLogoutRedirectsToLoginPage` |

## JavaScript Alerts

| ID | Scenario | Expected Result | Implementation |
| --- | --- | --- | --- |
| ALERT_TC_01 | Accept JS Alert | Result should display successful alert action | `JavaScriptAlertsTest.ALERT_TC_01_acceptJsAlertShowsSuccessfulAction` |
| ALERT_TC_02 | Dismiss JS Confirm | Result should display dismissed message | `JavaScriptAlertsTest.ALERT_TC_02_dismissJsConfirmShowsDismissedMessage` |
| ALERT_TC_03 | Enter text in JS Prompt | Entered text should be displayed | `JavaScriptAlertsTest.ALERT_TC_03_enterTextInJsPromptDisplaysEnteredText` |
| ALERT_TC_04 | Verify result text updates correctly | Result section should refresh correctly | `JavaScriptAlertsTest.ALERT_TC_04_resultTextUpdatesCorrectlyAcrossAlertActions` |

## Checkboxes and Dropdowns

| ID | Scenario | Expected Result | Implementation |
| --- | --- | --- | --- |
| CHECK_TC_01 | Check checkbox 1 | Checkbox 1 should be selected | `CheckboxesAndDropdownsTest.CHECK_TC_01_checkCheckboxOneSelectsIt` |
| CHECK_TC_02 | Toggle checkbox 2 | Checkbox state should change | `CheckboxesAndDropdownsTest.CHECK_TC_02_toggleCheckboxTwoChangesItsState` |
| DROP_TC_01 | Select dropdown option 1 | Option 1 should be selected | `CheckboxesAndDropdownsTest.DROP_TC_01_selectDropdownOptionOne` |
| DROP_TC_02 | Verify dropdown option count | Correct number of dropdown options should appear | `CheckboxesAndDropdownsTest.DROP_TC_02_verifyDropdownOptionCount` |

## File Upload

| ID | Scenario | Expected Result | Implementation |
| --- | --- | --- | --- |
| UPLOAD_TC_01 | Upload valid file | Uploaded filename should display | `FileUploadTest.UPLOAD_TC_01_uploadValidFileDisplaysFilename` |
| UPLOAD_TC_02 | Verify upload page title | Upload page should load correctly | `FileUploadTest.UPLOAD_TC_02_verifyUploadPageTitle` |
| UPLOAD_TC_03 | Upload unsupported file type | Application should handle gracefully | `FileUploadTest.UPLOAD_TC_03_uploadUnsupportedFileTypeHandledGracefully` |

## Dynamic Loading

| ID | Scenario | Expected Result | Implementation |
| --- | --- | --- | --- |
| DYNAMIC_TC_01 | Click Start and wait for content | Dynamic text should appear | `DynamicLoadingTest.DYNAMIC_TC_01_clickStartAndWaitForDynamicContent` |
| DYNAMIC_TC_02 | Verify loaded text | `Hello World!` should appear | `DynamicLoadingTest.DYNAMIC_TC_02_verifyLoadedTextIsHelloWorld` |
| DYNAMIC_TC_03 | Refresh disappearing elements page | Disappearing element should reappear | `DynamicLoadingTest.DYNAMIC_TC_03_refreshDisappearingElementsUntilGalleryAppears` |

## Coverage Status

All 18 required test cases are represented in the framework.
