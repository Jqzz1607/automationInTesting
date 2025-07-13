package online.automationInTesting.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RoomReservationPage extends BasePage{

    public RoomReservationPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#root-container > div > div.container.my-5 > div > div.col-lg-4 > div > div > form > button.btn.btn-primary.w-100.mb-3")
    WebElement clickReserveNow;



    @FindBy(css ="#root-container > div > div.container.my-5 > div > div.col-lg-4 > div > div > form > div.input-group.mb-3.room-booking-form > input")
    WebElement firstName;

    @FindBy(css = "#root-container > div > div.container.my-5 > div > div.col-lg-4 > div > div > form > div:nth-child(2) > input")
    WebElement lastName ;

    @FindBy(css = "#root-container > div > div.container.my-5 > div > div.col-lg-4 > div > div > form > div:nth-child(3) > input")
    WebElement emailAddress;

    @FindBy(css = "#root-container > div > div.container.my-5 > div > div.col-lg-4 > div > div > form > div:nth-child(4) > input")
    WebElement phone;

    @FindBy(css = "#root-container > div > div.container.my-5 > div > div.col-lg-4 > div > div > h2")
    WebElement reservationConfirmed;


    public void fillReservationForm(String foreName, String surName, String email, String number){

//        scrollByVisibleElement(firstName);
        sendTextIntoFields(firstName, foreName);
        sendTextIntoFields(lastName,surName);
        sendTextIntoFields(emailAddress,email);
        sendTextIntoFields(phone,number);
        scrollToElement(clickReserveNow);
        clickElementJS(clickReserveNow);
//        clickOnElement(clickReserveNow);



    }

    public void confirmReservation(String msg) throws InterruptedException {

        scrollToTopOfPage(reservationConfirmed);

        String actualMessage = readText(reservationConfirmed);
        Assert.assertTrue(actualMessage.contentEquals(msg));

    }





}
