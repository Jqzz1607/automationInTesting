package online.automationInTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HomePage extends BasePage{


    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css= "#booking > div > div > div > form > div > div:nth-child(1) > div > div > input")
    WebElement checkInBox;

    @FindBy(css= "#booking > div > div > div > form > div > div:nth-child(2) > div > div > input")
    WebElement checkOutBox;

    @FindBy(css = "div[aria-label='Choose Monday, 21 July 2025']")
    WebElement checkInDate;

    @FindBy(css="input.form-control.react-datepicker-ignore-onclickoutside")
    WebElement date_input;

    @FindBy(css="#booking > div > div > div > form > div > div.col-8.mt-4 > button")
    WebElement availabilityBttn;

    @FindBy(css ="#navbarNav > ul > li:nth-child(5) > a")
    WebElement contactLink;

    @FindBy(css ="#name")
    WebElement nameFiled;
    @FindBy(css ="#email")
    WebElement emailField;
    @FindBy(css ="#phone")
    WebElement phoneField;
    @FindBy(css ="#subject")
    WebElement subjectFiled;
    @FindBy(css ="#description")
    WebElement messageField ;

    @FindBy(css= "button[class='btn btn-primary']")
    WebElement submitButton;

    @FindBy(css=".card-body.p-4 p:nth-child(3)")
    WebElement successMessage;

    @FindBy(css=".alert.alert-danger")
    WebElement errorMessage;

    @FindBy(css = "li:nth-child(6) a:nth-child(1)")
    WebElement AdminLink;

    @FindBy(css = "ul.navbar-nav.ms-auto li a.nav-link")
    List<WebElement> navbarLinks;

    @FindBy(css = "#rooms > div > div.row.g-4 > div:nth-child(1) > div > div.card-footer.bg-white.d-flex.justify-content-between.align-items-center > a")
    WebElement bookNowBttn;

    @FindBy(css="a[class='navbar-brand d-flex align-items-center'] span")
    WebElement titlePgTxt;

    @FindBy(css = "button[class='btn btn-primary w-100 py-2']")
    WebElement clickReserveNow;




    public void navigateToHomePage() {
        BasePage.launchUrl();
    }


    public AdminLoginPage navigateToAdminPG(){

        clickOnElement(AdminLink);
        return new AdminLoginPage(driver);
    }


    public void checkInDate() {

        scrollByVisibleElement(availabilityBttn);
        enterCheckInDateJS(checkInBox);

    }

    public void checkOutDate() {

        enterCheckOutDateJS(checkOutBox);

    }



    public void clickReserveNowBttn(){

        clickElementJS(clickReserveNow);

    }

    public RoomsPage bookNow() {


           clickElementJS(bookNowBttn);
            return new RoomsPage(driver);

    }



    public void openContactform(){

        clickOnElement(contactLink);
    }



    public void fillForm(String name, String email, String phone, String subject, String message) throws InterruptedException {


        sendTextIntoFields(nameFiled,name);
        sendTextIntoFields(emailField,email);
        sendTextIntoFields(phoneField, phone);
        sendTextIntoFields(subjectFiled,subject);
        sendTextIntoFields(messageField, message);
        scrollToBottomOfPage(submitButton);

    }

    public void submitForm(){

        clickOnElement(submitButton);
    }

    public void getSuccessMessage(String msg) {

        String actualMessage = readText(successMessage);
        assertTrue(actualMessage.contentEquals(msg));
    }

    public void getErrorMessage(String msg) {


        String actualMessage = readText(errorMessage);
        assertTrue(actualMessage.contentEquals(msg));
    }

    public String getNavbarLinkTxt(String expectedText) {
        for (WebElement link : navbarLinks) {
            highlightElement(link);
            String txt = readText(link);
            if (txt.trim().equalsIgnoreCase(expectedText)) {
                return txt;
            }
        }
        return null;
    }

    public void getTitlePgTxt(String msg){

        String actualMessage = readText(titlePgTxt);
        assertTrue(actualMessage.contentEquals(msg));

    }


    public Map<String, Object> getResult() {
        Map<String, Object> result = CheckPerformanceApiSupportAvailable();
        return result;
    }

    public  void ensureApiFeatureAvailable(String apiFeature) {
        switch (apiFeature) {
            case "window.performance":
                assertTrue("Performance API not available", (Boolean) getResult().get("hasPerformance"));
                break;
            case "performance.timing":
                assertTrue("Legacy timing API not available", (Boolean) getResult().get("hasTiming"));
                break;
            case "performance.getEntriesByType":
                assertTrue("Navigation Timing Level 2 API not available", (Boolean) getResult().get("hasEntries"));
                break;
            default:
                fail("Unknown API feature: " + apiFeature);
        }
    }

    public void ensureNavigationEntryExists(String call) {
        assertTrue("No navigation entries found", (Boolean) getResult().get("navEntryExists"));

    }












}
