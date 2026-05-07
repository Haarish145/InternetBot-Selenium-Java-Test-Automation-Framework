package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.ScreenshotUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestListener implements ITestListener {
    private final ExtentReports extentReports = ExtentManager.getReporter();
    private final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        extentTest.set(extentReports.createTest(displayName(result)));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest().pass("Test passed");
        extentTest.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = currentTest();
        test.fail(result.getThrowable());
        try {
            WebDriver driver = DriverFactory.getDriver();
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (Exception exception) {
            test.warning("Screenshot capture failed: " + exception.getMessage());
        }
        extentTest.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest().skip(result.getThrowable());
        extentTest.remove();
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        extentTest.remove();
    }

    private ExtentTest currentTest() {
        ExtentTest test = extentTest.get();
        if (test == null) {
            throw new IllegalStateException("Extent test was not initialized for this thread.");
        }
        return test;
    }

    private String displayName(ITestResult result) {
        Object[] parameters = result.getParameters();
        if (parameters.length == 0) {
            return result.getMethod().getMethodName();
        }

        String joinedParameters = Arrays.stream(parameters)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return result.getMethod().getMethodName() + " [" + joinedParameters + "]";
    }
}
