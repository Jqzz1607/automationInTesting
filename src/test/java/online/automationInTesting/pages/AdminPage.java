package online.automationInTesting.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends  BasePage{

    public AdminPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "a.navbar-brand")
    WebElement dashboardHeader;

    public void getAdminPGHeader(String msg) {


        String actualMessage = readText(dashboardHeader);
        Assert.assertTrue(actualMessage.contentEquals(msg));
    }











}
