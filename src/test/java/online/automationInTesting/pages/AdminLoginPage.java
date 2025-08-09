package online.automationInTesting.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage extends BasePage{

    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "doLogin")
    WebElement loginButton;

    @FindBy(css = "div[role='alert']")
    WebElement loginError;


// loging
    public void login(String username, String password) {

        sendTextIntoFields(usernameField, username);
        sendTextIntoFields(passwordField, password);
        clickOnElement(loginButton);

    }

    public void getLoginErrorMsg(String msg) {


        String actualMessage = readText(loginError);
        Assert.assertTrue(actualMessage.contentEquals(msg));
    }





}
