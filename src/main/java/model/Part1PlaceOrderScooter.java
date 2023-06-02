package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Part1PlaceOrderScooter {
    private final WebDriver driver;
    //Поле ввода имени
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле ввода адреса доставки
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле ввода контактного телефона
    private final By cellPhoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Поле поиска по выпадающему списку станций метро
    private final By metroStationInput = By.className("select-search__input");
    //Кнопка Далее в первой секции формы заказа
    private final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']//button");
    //Выпадающий список станций метро
    private final By stationsDropDownList = By.xpath(".//*[@class='select-search__select']");

    public Part1PlaceOrderScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserName(String name) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setUserSurname(String surname) {
        driver.findElement(surnameInput).clear();
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void setUserAddress(String address) {
        driver.findElement(addressInput).clear();
        driver.findElement(addressInput).sendKeys(address);
    }

    public void setUserMetroStation(String metroStation) {
        driver.findElement(metroStationInput).click();
        driver.findElement(metroStationInput).sendKeys(metroStation);
        driver.findElement(stationsDropDownList).click();
    }

    public void setUserPhoneNumber(String phoneNumber) {
        driver.findElement(cellPhoneInput).clear();
        driver.findElement(cellPhoneInput).sendKeys(phoneNumber);
    }

    public void fillInClientData(String name, String surname, String address, String phoneNumber, String metroStation) {
        setUserName(name);
        setUserSurname(surname);
        setUserAddress(address);
        setUserMetroStation(metroStation);
        setUserPhoneNumber(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}
