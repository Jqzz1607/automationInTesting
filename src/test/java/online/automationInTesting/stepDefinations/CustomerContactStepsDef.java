package online.automationInTesting.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import online.automationInTesting.pages.BasePage;
import online.automationInTesting.pages.HomePage;
import org.openqa.selenium.support.PageFactory;

public class CustomerContactStepsDef extends BasePage {

    HomePage homePage = PageFactory.initElements(driver, HomePage.class);

    @Given("I am on the contact page")
    public void iAmOnContactPage() {

        homePage.navigateToHomePage();
        homePage.openContactform();
    }


    @When("I fill in the contact form with {string}, {string}, {string}, {string}, {string}")
    public void fillInTheContactFormWith(String name, String email, String phone, String subject, String message) throws InterruptedException {
        homePage.fillForm(name, email, phone, subject, message);
    }

    @When("I submit the form")
    public void iSubmitTheForm() throws InterruptedException {
        homePage.submitForm();
    }

    @Then("I should see a confirmation message {string}")
    public void iShouldSeeConfirmation(String msg) {
        homePage.getSuccessMessage(msg);
    }

    @Then("I should see a error message {string}")
    public void iShouldSeeErrorMessage(String msg) {
        homePage.getErrorMessage(msg);
    }


}
