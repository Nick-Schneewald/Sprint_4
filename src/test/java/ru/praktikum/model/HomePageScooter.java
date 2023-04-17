package ru.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageScooter {

    private WebDriver driver;
    //Кнопка Заказать в заголовке основной страницы
    private By placeOrderHeaderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //Кнопка Заказать в конце второй секции основной страницы
    private By placeOrderMainButton = By.className("Button_Middle__1CSJM");

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }
    public void waitTillHeaderOrderButtonIsClickable(){
        new WebDriverWait(driver,3)
                .until(ExpectedConditions.elementToBeClickable(placeOrderHeaderButton));
    }

    public void waitTillMainOrderButtonIsClickable(){
        new WebDriverWait(driver,3)
                .until(ExpectedConditions.elementToBeClickable(placeOrderMainButton));
    }
    public void clickHeaderPlaceOrderButton() {
        driver.findElement(placeOrderHeaderButton).click();
    }
    public void clickMainPlaceOrderButton() {
        driver.findElement(placeOrderMainButton).click();
    }

    public void scrollDownToOrderButton() {
        WebElement element = driver.findElement(placeOrderMainButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()",element);
    }
}
