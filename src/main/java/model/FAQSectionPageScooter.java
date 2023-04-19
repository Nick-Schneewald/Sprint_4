package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FAQSectionPageScooter {
    private final WebDriver driver;
    //Хедер основной страницы
    private final By headerOfHomeScreen = By.className("Header_Header__214zg");
    //Секция FAQ основной страницы
    private final By fourthPartOfHomeScreen = By.cssSelector(".Home_FourPart__1uthg");
    //Выбранный пользователем вопрос FAQ
    private By questionPanel;
    //Открывшийся ответ FAQ
    private By actualAnswerLabel;

    public FAQSectionPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPageLoading() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(headerOfHomeScreen));
    }

    public void scrollDownToActualView() {
        WebElement element = driver.findElement(fourthPartOfHomeScreen);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
    }

    public String getActualAnswersForFAQ(String actualQuestion) {

        questionPanel = By.xpath(".//div[text() = '" + actualQuestion + "']");
        String questionIDValue = driver.findElement(questionPanel).getAttribute("id");
        actualAnswerLabel = By.xpath(".//div[@aria-labelledby='" + questionIDValue + "']");

        driver.findElement(questionPanel).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(actualAnswerLabel));
        return driver.findElement(actualAnswerLabel).getText();
    }
}
