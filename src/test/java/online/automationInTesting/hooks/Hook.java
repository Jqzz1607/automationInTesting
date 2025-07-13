package online.automationInTesting.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import online.automationInTesting.helpers.Browsers;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Configuration;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static online.automationInTesting.pages.BasePage.*;

public class Hook extends Browsers {

    public static String testEnvironment;
    private static final ThreadLocal<StringBuilder> logBuffer = ThreadLocal.withInitial(StringBuilder::new);

    @Before
    public void setUp(Scenario scenario) {
        try {
            testEnvironment = System.getProperty("env", "uat");
            String browserName = new Configuration().getPropertiesParameter(testEnvironment, "browser");

            success("🚀 Starting scenario", scenario.getName());
            success("🌍 Test Environment", testEnvironment);
            success("🧪 Browser", browserName);

            launchBrowser(browserName);

            String baseUrl = getBaseUrl(testEnvironment);
            success("🔗 Base URL loaded", baseUrl);

        } catch (IOException e) {
            report("❌ Error during setup", scenario.getName(), e);
            throw new RuntimeException("Failed to launch browser", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (driver != null) {
                captureAndAttachScreenshot(scenario);
                captureScreenshotForExtentReport(scenario);
                success("📸 Screenshot captured for scenario", scenario.getName());
            }
        } catch (Exception e) {
            report("❌ Unable to capture screenshot", scenario.getName(), e);
        } finally {
            closeBrowserSafely(scenario);

            // Output accumulated log
            StringBuilder sb = logBuffer.get();
            System.out.println(sb.toString());
            logBuffer.remove();

            // Clean up WebDriver
            driver = null;
        }
    }

    private void captureAndAttachScreenshot(Scenario scenario) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(src);

        scenario.attach(fileContent, "image/png", scenario.getName());
        captureScreenshot(scenario.getName());
    }

    private void captureScreenshotForExtentReport(Scenario scenario) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
        String screenshotPath = "screenshots/" + fileName;

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);
        FileUtils.copyFile(src, destFile);

        com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter.addTestStepLog(
                "📎 Screenshot saved at: <a href='" + destFile.getAbsolutePath() + "'>Click to view</a>"
        );
        com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(destFile.getAbsolutePath());
    }

    private void closeBrowserSafely(Scenario scenario) {
        try {
            closeBrowser();
            success("🛑 Browser closed for scenario", scenario.getName());
        } catch (Exception e) {
            report("❌ Error closing browser", scenario.getName(), e);
        }
    }
}
