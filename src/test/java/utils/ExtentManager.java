package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ExtentManager {
    private static final Path REPORTS_DIRECTORY = Path.of("reports");
    private static final DateTimeFormatter REPORT_TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getReporter() {
        if (extentReports == null) {
            try {
                Files.createDirectories(REPORTS_DIRECTORY);
            } catch (Exception exception) {
                throw new IllegalStateException("Unable to create reports directory.", exception);
            }
            String timestamp = LocalDateTime.now().format(REPORT_TIMESTAMP);
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORTS_DIRECTORY.resolve("InternetBot_" + timestamp + ".html").toString());
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
