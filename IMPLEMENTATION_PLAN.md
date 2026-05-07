# InternetBot Implementation Plan

## Goal

Build a reusable Selenium-Java automation framework for The Internet Herokuapp using Maven, TestNG, Page Object Model, WebDriverManager, ExtentReports, explicit waits, screenshots on failure, configuration management, and data-driven testing.

## Phase 1: Project Setup

### Tasks

1. Create Maven project structure.
2. Add required Maven dependencies.
3. Configure Java version.
4. Configure Maven Surefire Plugin to run TestNG suite.
5. Create required folders.

### Deliverables

- `pom.xml`
- `src/test/java`
- `src/test/resources`
- `reports/`
- `screenshots/`

### Verification

- Maven project loads without dependency errors.
- `mvn test` can trigger TestNG execution.

## Phase 2: Configuration Management

### Tasks

1. Create `config.properties`.
2. Add browser, base URL, timeout, headless mode, credentials, and upload file names.
3. Create `ConfigReader`.
4. Read all environment-sensitive values through `ConfigReader`.

### Deliverables

- `src/test/resources/config.properties`
- `src/test/java/utils/ConfigReader.java`

### Verification

- No hardcoded URL in test classes.
- No hardcoded credentials in test classes.
- Browser can be changed from config.

## Phase 3: WebDriver Management

### Tasks

1. Create `DriverFactory`.
2. Use WebDriverManager for driver setup.
3. Support Chrome, Firefox, and Edge.
4. Support headless execution.
5. Store WebDriver using `ThreadLocal`.
6. Add browser cleanup method.

### Deliverables

- `src/test/java/utils/DriverFactory.java`

### Verification

- Browser opens based on config value.
- Browser closes after test execution.
- Framework is ready for future parallel execution.

## Phase 4: Base Framework Layer

### Tasks

1. Create `BaseTest`.
2. Add `@BeforeMethod` for driver initialization and application launch.
3. Add `@AfterMethod` for browser cleanup.
4. Create `BasePage`.
5. Add reusable actions:
   - Wait for element
   - Click element
   - Type text
   - Attach file
   - Handle alert
   - Select dropdown
   - Read text
   - Check selected/displayed state

### Deliverables

- `src/test/java/base/BaseTest.java`
- `src/test/java/base/BasePage.java`

### Verification

- Test classes extend `BaseTest`.
- Page classes extend `BasePage`.
- Common Selenium actions are reused.

## Phase 5: Wait Strategy

### Tasks

1. Create `WaitUtility`.
2. Implement explicit waits using `WebDriverWait`.
3. Use Selenium `ExpectedConditions`.
4. Implement `FluentWait` for dynamic behavior.
5. Remove all `Thread.sleep` usage.

### Deliverables

- `src/test/java/utils/WaitUtility.java`

### Verification

- No `Thread.sleep` exists in the framework.
- Dynamic loading tests wait reliably for elements.

## Phase 6: Page Object Classes

### Tasks

1. Create page classes for required modules.
2. Keep locators inside page classes only.
3. Keep Selenium actions inside page classes only.
4. Expose meaningful business-level methods for tests.

### Deliverables

- `src/test/java/pages/LoginPage.java`
- `src/test/java/pages/AlertPage.java`
- `src/test/java/pages/CheckboxPage.java`
- `src/test/java/pages/UploadPage.java`
- `src/test/java/pages/DynamicPage.java`

### Verification

- Test classes do not contain locators.
- Test classes do not call `findElement`.
- Page methods have readable names.

## Phase 7: TestNG Test Classes

### Tasks

1. Create TestNG test classes.
2. Write tests for authentication.
3. Write tests for JavaScript alerts.
4. Write tests for checkboxes and dropdown.
5. Write tests for file upload.
6. Write tests for dynamic loading and dynamic content.
7. Use assertions for expected results.
8. Add retry analyzer where useful.

### Deliverables

- `src/test/java/tests/AuthenticationTest.java`
- `src/test/java/tests/JavaScriptAlertsTest.java`
- `src/test/java/tests/CheckboxesAndDropdownsTest.java`
- `src/test/java/tests/FileUploadTest.java`
- `src/test/java/tests/DynamicLoadingTest.java`

### Verification

- Required test cases are represented.
- Tests are readable and short.
- No direct WebDriver element code exists in tests.

## Phase 8: Data-Driven Testing

### Tasks

1. Create login data provider.
2. Read credentials from configuration.
3. Execute valid and invalid login cases using the same test method.

### Deliverables

- `src/test/java/dataProviders/LoginDataProvider.java`

### Verification

- Login test runs with multiple credential sets.
- Data is externalized from test logic.

## Phase 9: Screenshot Handling

### Tasks

1. Create screenshot utility.
2. Generate timestamped screenshot names.
3. Save screenshots in `screenshots/`.
4. Trigger screenshot capture only on failure.

### Deliverables

- `src/test/java/utils/ScreenshotUtil.java`
- `screenshots/`

### Verification

- Failed tests create screenshot files.
- Screenshot filenames include test name and timestamp.

## Phase 10: Reporting

### Tasks

1. Add ExtentReports dependency.
2. Create `ExtentManager`.
3. Create TestNG listener.
4. Log pass, fail, and skipped statuses.
5. Attach screenshots on failure.
6. Flush report after suite execution.

### Deliverables

- `src/test/java/utils/ExtentManager.java`
- `src/test/java/listeners/TestListener.java`
- `reports/`

### Verification

- HTML report is generated after execution.
- Report includes pass/fail status.
- Failure screenshots are attached.

## Phase 11: TestNG Suite Configuration

### Tasks

1. Create `testng.xml`.
2. Register TestNG listener.
3. Add all test classes.
4. Configure suite execution.

### Deliverables

- `src/test/resources/testng.xml`

### Verification

- `mvn test` executes the suite file.
- All test classes are picked up.

## Phase 12: Final Validation

### Tasks

1. Run Maven test execution.
2. Fix compile errors.
3. Fix failing tests caused by locator or wait issues.
4. Confirm report generation.
5. Confirm screenshot generation by intentionally checking failure behavior if needed.
6. Review code for framework rules.

### Verification Checklist

- `mvn test` runs successfully.
- No `Thread.sleep`.
- No `findElement` inside test classes.
- No hardcoded URL in test classes.
- No hardcoded credentials in test classes.
- Extent report is generated.
- Failure screenshot logic works.
- Browser closes after each test.

## Execution Command

If Maven is installed globally:

```bash
mvn test
```

If using IntelliJ bundled Maven on this machine:

```powershell
& 'C:\Program Files\JetBrains\IntelliJ IDEA 2026.1\plugins\maven\lib\maven3\bin\mvn.cmd' test
```

## Suggested Timeline

| Time | Activity |
| --- | --- |
| 20 minutes | Maven setup, dependencies, folders |
| 25 minutes | Config, driver, base classes |
| 30 minutes | Waits, screenshots, reports, listener |
| 45 minutes | Page object classes |
| 45 minutes | TestNG test classes |
| 20 minutes | Data provider and retry analyzer |
| 30 minutes | Run, debug, and stabilize suite |
| 15 minutes | Documentation and presentation notes |

## Risks and Mitigation

| Risk | Mitigation |
| --- | --- |
| Browser driver mismatch | Use WebDriverManager |
| Dynamic elements load slowly | Use explicit waits and FluentWait |
| Flaky test failures | Use retry analyzer and stable waits |
| Missing evidence for failures | Capture screenshot in listener |
| Hardcoded values reduce maintainability | Store values in config properties |
| Tests become hard to read | Keep locators and actions inside page classes |

## Completion Criteria

The implementation is complete when:

- Framework follows POM structure.
- All required test modules are implemented.
- Test execution is controlled by TestNG XML.
- Browser and URL are controlled by config.
- Explicit waits are used instead of sleeps.
- Screenshots are captured on failure.
- ExtentReports HTML report is generated.
- The suite can be executed with Maven.
