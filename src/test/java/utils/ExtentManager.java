package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ExtentManager {
    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getReporter() {
        if (extentReports == null) {
            try {
                Files.createDirectories(Path.of("reports"));
            } catch (Exception exception) {
                throw new IllegalStateException("Unable to create reports directory.", exception);
            }
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/InternetBot_" + timestamp + ".html");
            sparkReporter.config().setReportName("InternetBot Automation Report");
            sparkReporter.config().setDocumentTitle("InternetBot Test Results");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Application", ConfigReader.get("baseUrl"));
            extentReports.setSystemInfo("Browser", ConfigReader.get("browser"));
        }
        return extentReports;
    }
}
