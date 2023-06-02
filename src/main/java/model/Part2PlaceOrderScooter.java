package model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Part2PlaceOrderScooter {
    private final WebDriver driver;

    //Поле ввода даты доставки
    private final By deliveryDateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле ввода срока аренды
    private final By rentDurationInput = By.className("Dropdown-control");
    //Значение 2 суток в выпадающем списке
    private final By DropDown1Day = By.xpath(".//div[@class='Dropdown-menu']/div[1]");
    private final By dropDown2Days = By.xpath(".//div[@class='Dropdown-menu']/div[2]");
    private final By dropDown3Days = By.xpath(".//div[@class='Dropdown-menu']/div[3]");
    //Чек бокс для цвета серая безысходность
    private final By checkBoxColourGrey = By.id("grey");
    //Чек бокс для цвета черный жемчуг
    private final By checkBoxColourBlack = By.id("black");
    //Поле ввода для комментариев по доставке
    private final By orderCommentsInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка завершения заполнения формы Заказать
    private final By placeOrderButtonFinal = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");

    public Part2PlaceOrderScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void setDeliveryDate(String date) {
        driver.findElement(deliveryDateInput).clear();
        driver.findElement(deliveryDateInput).sendKeys(date);
        driver.findElement(deliveryDateInput).sendKeys(Keys.ENTER);
    }

    public void setRentDuration(By duration) {
        driver.findElement(rentDurationInput).click();
        driver.findElement(dropDown2Days).click();
    }

    public void setScooterColour(By colour) {
        driver.findElement(checkBoxColourGrey).click();
    }

    public void setOrderComment(String comment) {
        driver.findElement(orderCommentsInput).sendKeys(comment);
    }

    public void fillInClientData(String date, By duration, By colour, String comment) {
        setDeliveryDate(date);
        setRentDuration(duration);
        setScooterColour(colour);
        setOrderComment(comment);
    }

    public void clickPlaceOrderButton() {
        driver.findElement(placeOrderButtonFinal).click();
    }
}
