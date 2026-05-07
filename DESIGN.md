# InternetBot Framework Design

## Objective

InternetBot is designed as a reusable Selenium-Java automation framework for testing The Internet Herokuapp. The framework focuses on maintainability, clear Page Object Model separation, explicit waits, configurable execution, evidence capture, and readable TestNG test cases.

## Architecture

```text
InternetBot
|
+-- src/test/java
|   |
|   +-- base
|   |   +-- BaseTest.java
|   |   +-- BasePage.java
|   |
|   +-- pages
|   |   +-- LoginPage.java
|   |   +-- AlertPage.java
|   |   +-- CheckboxPage.java
|   |   +-- UploadPage.java
|   |   +-- DynamicPage.java
|   |
|   +-- tests
|   |   +-- AuthenticationTest.java
|   |   +-- JavaScriptAlertsTest.java
|   |   +-- CheckboxesAndDropdownsTest.java
|   |   +-- FileUploadTest.java
|   |   +-- DynamicLoadingTest.java
|   |
|   +-- utils
|   |   +-- DriverFactory.java
|   |   +-- ConfigReader.java
|   |   +-- WaitUtility.java
|   |   +-- ScreenshotUtil.java
|   |   +-- ExtentManager.java
|   |   +-- RetryAnalyzer.java
|   |
|   +-- listeners
|   |   +-- TestListener.java
|   |
|   +-- dataProviders
|       +-- LoginDataProvider.java
|
+-- src/test/resources
|   +-- config.properties
|   +-- testng.xml
|   +-- upload-sample.txt
|   +-- upload-unsupported.exe
|
+-- reports
+-- screenshots
+-- pom.xml
```

## Design Pattern

The framework uses Page Object Model.

Each web page or feature area has a page class. Page classes own:

- Element locators
- Selenium interactions
- Page-specific validations
- Reusable user actions

Test classes own:

- Test flow
- Assertions
- Data provider usage

This keeps tests readable and prevents direct WebDriver element code inside test classes.

## Component Responsibilities

### Base Layer

`BaseTest` controls test setup and teardown.

- Initializes WebDriver before every test method
- Reads the base URL from configuration
- Opens the application under test
- Quits WebDriver after every test method

`BasePage` provides common page-level actions.

- Wait for elements
- Click elements
- Type text
- Attach files
- Handle alerts
- Select dropdown values
- Read text and element state

### Utility Layer

`DriverFactory` manages browser creation.

- Reads browser type from `config.properties`
- Supports Chrome, Firefox, and Edge
- Uses WebDriverManager
- Supports headless execution
- Stores WebDriver in `ThreadLocal` for scalable execution

`ConfigReader` centralizes configuration access.

- Reads `config.properties`
- Avoids hardcoded URLs, credentials, and timeouts in test code

`WaitUtility` provides synchronization.

- Uses `WebDriverWait`
- Uses `ExpectedConditions`
- Uses `FluentWait`
- Avoids `Thread.sleep`

`ScreenshotUtil` captures evidence.

- Saves screenshots in `screenshots/`
- Adds timestamps to filenames

`ExtentManager` configures ExtentReports.

- Generates HTML reports in `reports/`
- Adds browser and application metadata

`RetryAnalyzer` retries failed tests once to reduce one-off flakiness.

### Listener Layer

`TestListener` listens to TestNG execution events.

- Creates ExtentReports test entries
- Marks pass, fail, and skipped tests
- Captures screenshots on failure
- Attaches screenshots to the HTML report
- Flushes the report at the end of execution

### Data Provider Layer

`LoginDataProvider` supplies login data to authentication tests.

- Valid credentials
- Invalid credentials
- Expected login outcome

## Execution Flow

```text
mvn test
   |
   v
Maven Surefire Plugin
   |
   v
src/test/resources/testng.xml
   |
   v
TestListener starts ExtentReports
   |
   v
BaseTest reads config and starts browser
   |
   v
Test class calls page object methods
   |
   v
Page class performs Selenium actions with explicit waits
   |
   v
Test class performs assertions
   |
   v
Listener records pass/fail status
   |
   v
On failure, screenshot is captured and attached
   |
   v
BaseTest closes browser
   |
   v
Extent HTML report is generated
```

## Test Coverage Design

| Module | Page Object | Test Class | Covered Scenarios |
| --- | --- | --- | --- |
| Authentication | `LoginPage` | `AuthenticationTest` | Valid login, invalid login, success message, logout |
| JavaScript Alerts | `AlertPage` | `JavaScriptAlertsTest` | Alert accept, confirm dismiss, prompt text, result update |
| Checkbox and Dropdown | `CheckboxPage` | `CheckboxesAndDropdownsTest` | Checkbox selection, toggle, dropdown selection, option count |
| File Upload | `UploadPage` | `FileUploadTest` | Valid upload, page title, unsupported file upload handling |
| Dynamic Pages | `DynamicPage` | `DynamicLoadingTest` | Dynamic loading, loaded text, disappearing elements |

## Configuration Design

All environment-sensitive values are stored in `src/test/resources/config.properties`.

```properties
browser=chrome
baseUrl=https://the-internet.herokuapp.com
timeout=10
headless=false
validUsername=tomsmith
validPassword=SuperSecretPassword!
```

Benefits:

- No hardcoded URLs in test classes
- Browser can be changed without code edits
- Headless mode can be enabled for CI
- Credentials are isolated from test logic

## Reporting Design

The reporting flow uses ExtentReports.

```text
Test starts -> Extent test node created
Test passes -> pass status recorded
Test fails -> failure status recorded -> screenshot captured -> screenshot attached
Suite ends -> report flushed to reports/
```

Generated artifacts:

- HTML report: `reports/InternetBot_<timestamp>.html`
- Failure screenshot: `screenshots/<testName>_<timestamp>.png`

## Maintainability Rules

- Tests must not use `findElement`.
- Tests must not contain locators.
- Tests must not use direct browser interaction logic except through page objects and base setup.
- No `Thread.sleep`.
- Common logic belongs in base or utility classes.
- Page-specific behavior belongs in page classes.
- Data belongs in config files or data providers.

## Scalability

The framework can be extended by adding:

- New page class in `pages`
- New test class in `tests`
- New data provider in `dataProviders`
- New utility in `utils` only when behavior is shared
- New suite entries in `testng.xml`

Because WebDriver is stored in `ThreadLocal`, the framework is prepared for future parallel TestNG execution.

## Hackathon Explanation

Use this summary during presentation:

InternetBot follows a layered Selenium framework design. TestNG controls execution, `BaseTest` controls browser setup and teardown, page classes encapsulate all locators and Selenium interactions, utility classes handle configuration, waits, screenshots, reports, retries, and WebDriver creation. The framework avoids hardcoded values and `Thread.sleep`, uses explicit waits and FluentWait, captures screenshots on failure, and generates ExtentReports for execution evidence.
