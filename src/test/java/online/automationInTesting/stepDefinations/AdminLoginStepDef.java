package online.automationInTesting.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import online.automationInTesting.pages.AdminLoginPage;
import online.automationInTesting.pages.AdminPage;
import online.automationInTesting.pages.BasePage;
import online.automationInTesting.pages.HomePage;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginStepDef extends BasePage {

    HomePage homePage = PageFactory.initElements(driver, HomePage.class);

    AdminLoginPage adminLoginPage = PageFactory.initElements(driver, AdminLoginPage.class);

    AdminPage adminPage = PageFactory.initElements(driver, AdminPage.class);


    @Given("I am on the admin login page")
    public void iAmOnTheAdminLoginPage() {

        homePage.navigateToHomePage();
        homePage.navigateToAdminPG();



    }
    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String userName, String pass) {

        adminLoginPage.login(userName, pass);

    }
    @Then("admin dashboard is displayed as {string}")
    public void adminDashboardIsDisplayed(String msg) {
        adminPage.getAdminPGHeader(msg);

    }


    @Then("I should see login error {string}")
    public void iShouldSeeLoginError(String msg) {
        adminLoginPage.getLoginErrorMsg(msg);

    }



}
