package online.automationInTesting.stepDefinations;

import online.automationInTesting.pages.BasePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import online.automationInTesting.pages.HomePage;
import online.automationInTesting.pages.RoomReservationPage;
import online.automationInTesting.pages.RoomsPage;
import org.openqa.selenium.support.PageFactory;



public class HomePageStepDef extends BasePage {


    HomePage homePage = PageFactory.initElements(driver, HomePage.class);

    RoomsPage roomsPage = PageFactory.initElements(driver, RoomsPage.class);

    RoomReservationPage roomReservationPage = PageFactory.initElements(driver, RoomReservationPage.class);

    @Given("I navigate to Shady B&B page")
    public void iNavigateToShadyBBPage() {

        homePage.navigateToHomePage();

    }

    @Then("navbar link {string} is visible")
    public void navbarLinkTxtIsVisible(String linkName) {
        homePage.getNavbarLinkTxt(linkName);
    }



    @Then("{string} title is displayed")
    public void titleIsDisplayed(String pgTitle) {
        homePage.getTitlePgTxt(pgTitle);
    }

    @Then("the browser should have {string}")
    public void theBrowserShouldHave(String apiFeature) {
        homePage.ensureApiFeatureAvailable(apiFeature);
    }

    @Then("the browser should have at least one navigation entry in {string}")
    public void theBrowserShouldHaveAtLeastOneNavigationEntry(String navigationEntry) {
        homePage.ensureNavigationEntryExists(navigationEntry);

    }




    @When("I book a room with valid details {string}, {string},{string},{string}")
    public void iBookARoomWithValidDetails(String firstName, String lastName, String email, String phone) {

        homePage.checkInDate();
        homePage.checkOutDate();
        homePage.clickReserveNowBttn();
        homePage.bookNow();
        roomsPage.reserveNow();
        roomReservationPage.fillReservationForm(firstName, lastName, email, phone);



    }



    @Then("I should see a booking confirmation {string}")
    public void iShouldSeeABookingConfirmation(String msg) throws InterruptedException {

        roomReservationPage.confirmReservation(msg);
    }
}