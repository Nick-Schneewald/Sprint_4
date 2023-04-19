package ru.praktikum;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import model.HomePageScooter;
import model.OrderModalWindow;
import model.Part1PlaceOrderScooter;
import model.Part2PlaceOrderScooter;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ScooterPlaceOrderChromeTest {
    private  final WebDriver driver;
    private  final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String metroStation;
    private final String date;
    private final By duration;
    private final By colour;

    private final String comment;
    private final  boolean result;

    private static ArrayList<By> rentDays = new ArrayList<>(Arrays.asList(
            By.xpath(".//div[@class='Dropdown-menu']/div[1]"),
            By.xpath(".//div[@class='Dropdown-menu']/div[2]"),
            By.xpath(".//div[@class='Dropdown-menu']/div[3]"),
            By.xpath(".//div[@class='Dropdown-menu']/div[4]"),
            By.xpath(".//div[@class='Dropdown-menu']/div[5]"),
            By.xpath(".//div[@class='Dropdown-menu']/div[6]"),
            By.xpath(".//div[@class='Dropdown-menu']/div[7]")
    ));
    private static ArrayList<By> colours = new ArrayList<>(Arrays.asList(By.id("grey"),By.id("black")));

    public ScooterPlaceOrderChromeTest(String name, String surname, String address, String phoneNumber, String metroStation, String date, By duration, By colour, String comment,  boolean result){
        this.driver = new ChromeDriver();
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.metroStation = metroStation;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderCredentials(){
        return new Object[][] {
                {"Иосиф","Бродский","ул.Комнатная,12-10","+79178888888","Верхние Котлы","16.04.2023\n\r",rentDays.get(2),colours.get(1),"Пожалуйста, позвоните за час",true},
                {"Семён", "Семёныч", "ул.Лесная, 14-2", "+78003553535", "Сокольники","20.04.2023\n\r",rentDays.get(3),colours.get(1),"Спасибо, лучше вы к нам...", true},
                {"Иван", "Петров", "ул. Ленина, 17-3", "+77777777777", "Фрунзенская","10.03.2021\n\r",rentDays.get(2),colours.get(0),"ыва ыва фыфва",false}
        };
    }

    @Test
    public void testWorkflowWithHeaderOrderButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.waitTillHeaderOrderButtonIsClickable();
        objHomePage.clickHeaderPlaceOrderButton();

        Part1PlaceOrderScooter objForm1stPart = new Part1PlaceOrderScooter(driver);
        objForm1stPart.fillInClientData(name,surname,address,phoneNumber,metroStation);
        objForm1stPart.clickNextButton();

        Part2PlaceOrderScooter objForm2ndPart = new Part2PlaceOrderScooter(driver);
        objForm2ndPart.fillInClientData(date,duration,colour,comment);
        objForm2ndPart.clickPlaceOrderButton();

        OrderModalWindow objModalWindow = new OrderModalWindow(driver);
        objModalWindow.waitTillYesButtonIsClickable();
        objModalWindow.clickYesButton();
        objModalWindow.waitTillWindowsIsShown();

        assertEquals(result,objModalWindow.isConfirmationWindowDisplayed());
    }

    @Test
    public void testWorkflowWithMainOrderButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.waitTillMainOrderButtonIsClickable();
        objHomePage.scrollDownToOrderButton();
        objHomePage.clickMainPlaceOrderButton();

        Part1PlaceOrderScooter objForm1stPart = new Part1PlaceOrderScooter(driver);
        objForm1stPart.fillInClientData(name,surname,address,phoneNumber,metroStation);
        objForm1stPart.clickNextButton();

        Part2PlaceOrderScooter objForm2ndPart = new Part2PlaceOrderScooter(driver);
        objForm2ndPart.fillInClientData(date,duration,colour,comment);
        objForm2ndPart.clickPlaceOrderButton();

        OrderModalWindow objModalWindow = new OrderModalWindow(driver);
        objModalWindow.waitTillYesButtonIsClickable();
        objModalWindow.clickYesButton();
        objModalWindow.waitTillWindowsIsShown();

        assertEquals(result,objModalWindow.isConfirmationWindowDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
