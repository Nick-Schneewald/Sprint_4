package ru.praktikum;

import model.HomePageScooter;
import model.OrderModalWindow;
import model.Part1PlaceOrderScooter;
import model.Part2PlaceOrderScooter;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ScooterPlaceOrderFirefoxTest {
    private static final ArrayList<By> rentDays = new ArrayList<>(
            Arrays.asList(
                    By.xpath(".//div[@class='Dropdown-menu']/div[1]"),
                    By.xpath(".//div[@class='Dropdown-menu']/div[2]"),
                    By.xpath(".//div[@class='Dropdown-menu']/div[3]"),
                    By.xpath(".//div[@class='Dropdown-menu']/div[4]"),
                    By.xpath(".//div[@class='Dropdown-menu']/div[5]"),
                    By.xpath(".//div[@class='Dropdown-menu']/div[6]"),
                    By.xpath(".//div[@class='Dropdown-menu']/div[7]")
            )
    );
    private static final ArrayList<By> colours = new ArrayList<>(Arrays.asList(By.id("grey"), By.id("black")));
    private final WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String metroStation;
    private final String date;
    private final String duration;
    private final String colour;
    private final String comment;
    private final boolean result;

    public ScooterPlaceOrderFirefoxTest(String name, String surname, String address, String phoneNumber, String metroStation, String date, String duration, String colour, String comment, boolean result) {
        this.driver = new FirefoxDriver();
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

    @Parameterized.Parameters(name = "{index}. Тестовые данные: {0} {1} {2} {3} {4} {5} {6} {7} {8} Ожидаемый результат: {9}")
    public static Object[][] getOrderCredentials() {
        return new Object[][]{
                {"Иосиф", "Бродский", "ул.Комнатная,12-10", "+79178888888", "Верхние Котлы", "16.04.2023", "двое суток", "серая безысходность", "Пожалуйста, позвоните за час", true},
                {"Семён", "Семёныч", "ул.Лесная, 14-2", "+78003553535", "Сокольники", "20.04.2023", "трое суток", "серая безысходность", "Спасибо, лучше вы к нам...", true},
                {"Иван", "Петров", "ул. Ленина, 17-3", "+77777777777", "Фрунзенская", "10.03.2021", "двое суток", "черный жемчуг", "ыва ыва фыфва", false}
        };
    }

    @Test
    public void testWorkflowWithHeaderOrderButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.waitTillHeaderOrderButtonIsClickable();
        objHomePage.clickHeaderPlaceOrderButton();

        Part1PlaceOrderScooter objForm1stPart = new Part1PlaceOrderScooter(driver);
        objForm1stPart.fillInClientData(name, surname, address, phoneNumber, metroStation);
        objForm1stPart.clickNextButton();

        Part2PlaceOrderScooter objForm2ndPart = new Part2PlaceOrderScooter(driver);
        objForm2ndPart.fillInClientData(date, mapDurationToSelectors(duration), mapColoursToSelectors(colour), comment);
        objForm2ndPart.clickPlaceOrderButton();

        OrderModalWindow objModalWindow = new OrderModalWindow(driver);
        objModalWindow.waitTillYesButtonIsClickable();
        objModalWindow.clickYesButton();
        objModalWindow.waitTillWindowsIsShown();

        assertEquals(result, objModalWindow.isConfirmationWindowDisplayed());
    }

    @Test
    public void testWorkflowWithMainOrderButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.scrollDownToOrderButton();
        objHomePage.waitTillMainOrderButtonIsClickable();
        objHomePage.clickMainPlaceOrderButton();

        Part1PlaceOrderScooter objForm1stPart = new Part1PlaceOrderScooter(driver);
        objForm1stPart.fillInClientData(name, surname, address, phoneNumber, metroStation);
        objForm1stPart.clickNextButton();

        Part2PlaceOrderScooter objForm2ndPart = new Part2PlaceOrderScooter(driver);
        objForm2ndPart.fillInClientData(date, mapDurationToSelectors(duration), mapColoursToSelectors(colour), comment);
        objForm2ndPart.clickPlaceOrderButton();

        OrderModalWindow objModalWindow = new OrderModalWindow(driver);
        objModalWindow.waitTillYesButtonIsClickable();
        objModalWindow.clickYesButton();
        objModalWindow.waitTillWindowsIsShown();

        assertEquals(result, objModalWindow.isConfirmationWindowDisplayed());
    }

    //additional method for mapping input duration to By-locator
    public By mapDurationToSelectors(String inbDuration) {
        By result;
        if (inbDuration.equals("сутки")) {
            result = rentDays.get(0);
        } else if (inbDuration.equals("двое суток")) {
            result = rentDays.get(1);
        } else if (inbDuration.equals("трое суток")) {
            result = rentDays.get(2);
        } else if (inbDuration.equals("четверо суток")) {
            result = rentDays.get(3);
        } else if (inbDuration.equals("пятеро суток")) {
            result = rentDays.get(4);
        } else if (inbDuration.equals("шестеро суток")) {
            result = rentDays.get(5);
        } else if (inbDuration.equals("семеро суток")) {
            result = rentDays.get(6);
        } else {
            result = rentDays.get(0);
        }
        return result;
    }

    //additional method for mapping input colour to By-locator
    public By mapColoursToSelectors(String inbColour) {
        By result;
        if (inbColour.equals("чёрный жемчуг")) {
            result = colours.get(0);
        } else if (inbColour.equals("серая безысходность")) {
            result = colours.get(1);
        } else {
            result = colours.get(0);
        }
        return result;
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
