package online.automationInTesting.pages;


import online.automationInTesting.helpers.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Configuration;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.*;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static online.automationInTesting.hooks.Hook.testEnvironment;

public class BasePage extends Driver {

    private static String baseUrl;
    private static final int TIMEOUT = 30;


    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String RESET = "\u001B[0m";


    public static String getBaseUrl(String environment) {
        if (baseUrl == null) {
            try {
                Configuration config = new Configuration();
                baseUrl = config.getPropertiesParameter(environment, "baseUrl");

                if (baseUrl == null || baseUrl.isEmpty()) {
                    throw new RuntimeException("Base URL not found in " + environment + ".properties");
                }

                success("Loaded base URL", baseUrl);

            } catch (IOException e) {
                report("Failed to load base URL for environment: " + environment, environment, e);
                throw new RuntimeException("Failed to read baseUrl", e);
            }
        }
        return baseUrl;
    }



    public static void launchUrl() {
        if (testEnvironment == null) {
            throw new IllegalStateException("Environment not set. Did you forget to call setUp()?");
        }

        try {
            String url = getBaseUrl(testEnvironment);
            driver.navigate().to(url);
            success("Navigated to URL", url);
        } catch (Exception e) {
            report("Failed to launch URL", testEnvironment, e);
            throw e;
        }
    }


    /**
     * =============      waits methods        =============
     */

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        try {

            return getWait().until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            report("🚫 Element not visible", element, e);
            return null;
        }
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        try {

            return getWait().until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            report("🚫 Element not clickable", element, e);
            return null;
        }
    }


    /**
     * =============      action methods        =============
     */


    public void highlightElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'", element);
            success("Element highlighted", element);
        } catch (Exception e) {
            report("Failed to highlight element", element, e);
        }
    }

    public void clickElementJS(WebElement element) {
        try {
            //getWait().until(ExpectedConditions.visibilityOf(element));
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            success("Element clicked via JavaScript", element);
        } catch (Exception e) {
            report("Failed to click element via JavaScript", element, e);
            throw new RuntimeException(e);
        }
    }



    public void sendTextIntoFields(WebElement element, String text) {
        try {
            WebElement el = waitForElementToBeVisible(element);
            if (el != null) {
                el.clear();
                el.sendKeys(text);
                success("⌨️ Entered text: \"" + text + "\"", element);
            }
        } catch (Exception e) {
            report("🚫 Failed to send keys", element, e);
        }
    }

    public void clickOnElement(WebElement element) {
        try {
            WebElement el = waitForElementToBeClickable(element);
            if (el != null) {
                el.click();
                success("🖱️ Clicked on element", element);
            }
        } catch (Exception e) {
            report("🚫 Click failed", element, e);
        }
    }

    public String readText(WebElement element) {
        try {
            WebElement el = waitForElementToBeVisible(element);
            String text;
            if (el != null) {
                text = el.getText();
            } else {
                text = "";
            }
            success("📖 Read text: \"" + text + "\"", element);
            return text;
        } catch (Exception e) {
            report("🚫 Failed to read text", element, e);
            return "";
        }
    }

    public boolean visibleCheck(WebElement element) {
        try {
            boolean result = waitForElementToBeVisible(element) != null && element.isDisplayed();
            success("👁️ Visibility: " + result, element);
            return result;
        } catch (Exception e) {
            report("🚫 Visibility check failed", element, e);
            return false;
        }
    }

    // different ways to scrolling to element

    public void scrollToElement(WebElement element) {
        try {
            Thread.sleep(3000);
            Point location = element.getLocation();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(" + location.getX() + ", " + location.getY() + ");");
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            success("🧭 Scrolled to element", element);
        } catch (Exception e) {
            report("🚫 Scroll failed", element, e);
        }
    }

    public void scrollByVisibleElement(WebElement element) {

        try {
            waitForElementToBeVisible(element);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        } catch (Exception e){

            report("🚫 Scroll failed", element, e);
        }
    }

    public void scrollToBottomOfPage(WebElement element) throws InterruptedException {

        try {

            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            visibleCheck(element);
            success("🧭 Element became visible after scrolling", element);
        }catch (Exception e){

            report("🚫 Scroll to bottom failed", element, e);
        }

    }

    public void scrollToTopOfPage(WebElement element) throws InterruptedException {
        try {
            Thread.sleep(3000); // Optional: let content settle
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Scroll to top
            js.executeScript("window.scrollTo(0, 0);");

            visibleCheck(element); // Optional: wait until element is visible
            success("🧭 Element became visible after scrolling to top", element);
        } catch (Exception e) {
            report("🚫 Scroll to top failed", element, e);
        }
    }



    public  void enterCheckInDateJS(WebElement date) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(date));
            getWait().until(ExpectedConditions.elementToBeClickable(date));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = '13/08/2025';", date);
            js.executeScript("arguments[0].dispatchEvent(new Event('change'));", date);
            success("Check-in date set using JavaScript", date);
        } catch (Exception e) {
            report("Failed to set check-in date", date, e);
            throw new RuntimeException(e);
        }
    }

    public  void enterCheckOutDateJS(WebElement date) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(date));
            getWait().until(ExpectedConditions.elementToBeClickable(date));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = '22/08/2025';", date);
            js.executeScript("arguments[0].dispatchEvent(new Event('change'));", date);
            success("Check-out date set using JavaScript", date);
        } catch (Exception e) {
            report("Failed to set check-out date", date, e);
            throw new RuntimeException(e);
        }
    }


    public static void inputDateJS(WebElement dateElement, String dateValue) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));",
                    dateElement,
                    dateValue
            );

            System.out.println("📅 Date set via JavaScript: " + dateValue);
        } catch (Exception e) {
            System.err.println("🚫 Failed to set date via JavaScript: " + e.getMessage());
            e.printStackTrace();
            captureScreenshot("inputDateJS_error");
            throw new RuntimeException("Failed to set date using JavaScript", e);
        }
    }

    /**
     * =============      Logging       =============
     */

        public static void success(String msg, Object elementRef) {
            String timestamp = now();
            System.out.println(GREEN + "[" + timestamp + "] ✅ " + msg + " ➜ " + elementRef + RESET);
        }

        public static void report(String msg, Object elementRef, Exception e) {
            String timestamp = now();
            System.err.println(RED + "[" + timestamp + "] ❌ " + msg + " ➜ " + elementRef + RESET);
            e.printStackTrace();
            captureScreenshot(msg.replaceAll("[^a-zA-Z0-9]", "_"));
        }

        private static String now() {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }

    /**
     * =============      Take Screenshot method      =============
     */

        public static void captureScreenshot(String reason) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File src = ts.getScreenshotAs(OutputType.FILE);
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                File dest = new File("screenshots/" + reason + "_" + timestamp + ".png");
                dest.getParentFile().mkdirs();
                Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(BLUE + "[INFO] Screenshot saved: " + dest.getAbsolutePath() + RESET);
            } catch (IOException | WebDriverException e) {
                System.err.println("[ERROR] Could not capture screenshot.");
                e.printStackTrace();
            }
        }

    /**
     * =============     get navigation timing methods      =============
     */

    public Map<String, Object> CheckPerformanceApiSupportAvailable() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            Object supportCheck = js.executeScript(
                    "return {" +
                            "  hasPerformance: typeof window.performance !== 'undefined'," +
                            "  hasTiming: typeof window.performance.timing !== 'undefined'," +
                            "  hasEntries: typeof window.performance.getEntriesByType === 'function'," +
                            "  navEntryExists: (typeof performance.getEntriesByType === 'function') && " +
                            "                  performance.getEntriesByType('navigation').length > 0" +
                            "};"
            );

            @SuppressWarnings("unchecked")
            Map<String, Object> result = (Map<String, Object>) supportCheck;


            success("Performance API present", "window.performance");

            if (!(Boolean) result.get("hasTiming")) {
                report("Legacy timing API not available", "performance.timing", null);
            } else {
                success("Legacy timing API available", "performance.timing");
            }

            if (!(Boolean) result.get("hasEntries")) {
                report("Navigation Timing Level 2 API not available", "performance.getEntriesByType", null);
            } else {
                success("Navigation Timing Level 2 API available", "performance.getEntriesByType('navigation')");
            }

            if (!(Boolean) result.get("navEntryExists")) {
                report("No navigation entries found", "performance.getEntriesByType('navigation')", null);
            } else {
                success("Navigation entries present", "performance.getEntriesByType('navigation')[0]");
            }

            return result;

        } catch (Exception e) {
            report("Failed to check performance API support", "window.performance", e);
            return null;
        }
    }



















}



