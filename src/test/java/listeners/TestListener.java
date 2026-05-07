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

public class TestListener implements ITestListener {
    private final ExtentReports extentReports = ExtentManager.getReporter();
    private final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        extentTest.set(extentReports.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.fail(result.getThrowable());
        try {
            WebDriver driver = DriverFactory.getDriver();
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (Exception exception) {
            test.warning("Screenshot capture failed: " + exception.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        extentTest.remove();
    }
}
