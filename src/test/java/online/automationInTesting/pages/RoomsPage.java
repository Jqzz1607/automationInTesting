package online.automationInTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RoomsPage extends BasePage{

    public RoomsPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "#root-container > div > div.container.my-5 > div > div.col-lg-4 > div > div > form > button.btn.btn-primary.w-100.mb-3")
    WebElement clickReserveNow;



    public RoomReservationPage reserveNow(){
//        scrollByVisibleElement(clickReserveNow);
        clickElementJS(clickReserveNow);
        return new RoomReservationPage(driver);

    }





}
