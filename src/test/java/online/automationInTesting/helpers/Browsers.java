package online.automationInTesting.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Browsers extends Driver {
    private WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private WebDriver initFirefox() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private WebDriver initChromeHeadless() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu", "--headless");
        return new ChromeDriver(options);

    }

    private WebDriver initFirefoxHeadless() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-gpu", "--headless");
        return new FirefoxDriver(options);
    }

    public void launchBrowser(String browser) {
        switch (browser) {
            case "Chrome":
                driver = initChromeDriver();
                break;
            case "Firefox":
                driver = initFirefox();
                break;
            case "ChromeHeadless":
                driver = initChromeHeadless();
                break;
            case "FirefoxHeadless":
                driver = initFirefoxHeadless();
                break;
            default:
                driver = initChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    public void closeBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
