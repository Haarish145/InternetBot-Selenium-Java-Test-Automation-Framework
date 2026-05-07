# InternetBot

InternetBot is a Selenium-Java TestNG framework for automating advanced challenges on The Internet Herokuapp.

## Stack

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager
- ExtentReports
- Page Object Model

## Structure

- `src/test/java/base`: reusable `BaseTest` and `BasePage`
- `src/test/java/pages`: Page Object classes with locators and browser actions
- `src/test/java/tests`: TestNG tests with no direct WebDriver element code
- `src/test/java/utils`: driver, config, waits, screenshots, reports, and retry utilities
- `src/test/java/listeners`: TestNG listener for ExtentReports and screenshots on failure
- `src/test/java/dataProviders`: TestNG data providers
- `src/test/resources`: `config.properties`, upload fixtures, and `testng.xml`
- `reports/`: generated ExtentReports HTML output
- `screenshots/`: failure screenshots with timestamps

## Configuration

Edit `src/test/resources/config.properties`:

```properties
browser=chrome
baseUrl=https://the-internet.herokuapp.com
timeout=10
headless=false
```

Supported browsers are `chrome`, `firefox`, and `edge`.

## Execution

```bash
mvn test
```

The Maven Surefire plugin runs `src/test/resources/testng.xml`.

## Covered Modules

- Form Authentication
- JavaScript Alerts
- Checkboxes
- Dropdown
- File Upload
- Dynamic Loading
- Disappearing Elements

## Test Case Scope

The required scope contains 18 explicit test cases:

| Module | Count |
| --- | ---: |
| Authentication | 4 |
| JavaScript Alerts | 4 |
| Checkboxes and Dropdowns | 4 |
| File Upload | 3 |
| Dynamic Loading | 3 |
| Total | 18 |

Detailed mapping is available in `TEST_CASE_SCOPE.md`.

## Presentation Points

- POM keeps locators and UI actions inside page classes.
- `BasePage` centralizes waits, clicks, typing, alerts, dropdowns, and file attachment.
- `DriverFactory` reads browser and headless mode from configuration and uses WebDriverManager.
- `WaitUtility` uses explicit waits and FluentWait; no `Thread.sleep` is used.
- `TestListener` captures timestamped screenshots on failures and attaches them to ExtentReports.
