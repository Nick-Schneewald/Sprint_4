package ru.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderModalWindow {
    private WebDriver driver;

    //Кнопка Да подтвержения заказа
    private By yesButtonFinalConfirmation = By.xpath((".//div[@class='Order_Modal__YZ-d3']//button[text()='Да']"));
    //Модальное окно подтвержения заказа
    private By orderConfirmationModalWindow = By.xpath(".//div[@class='Order_Modal__YZ-d3']");
    //Заголовок окна Заказ оформлен
    private By captionOrderConfirmed = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[text()='Заказ оформлен']");

    public OrderModalWindow(WebDriver driver) {
        this.driver = driver;
    }

    public void clickYesButton(){
        driver.findElement(yesButtonFinalConfirmation).click();
    }
    public void waitTillYesButtonIsClickable(){
        new WebDriverWait(driver,3)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(yesButtonFinalConfirmation)));
    }

    public void waitTillWindowsIsShown(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(orderConfirmationModalWindow));
    }
    public boolean isConfirmationWindowDisplayed(){
        return driver.findElement(captionOrderConfirmed).isDisplayed();
    }
}
