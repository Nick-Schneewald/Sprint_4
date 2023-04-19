package ru.praktikum;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import model.FAQSectionPageScooter;

@RunWith(Parameterized.class)
public class FAQSectionChromeTest {
    private final WebDriver driver;
    private final String question;
    private final String answer;

    private final boolean result;

    public FAQSectionChromeTest(String question, String answer, boolean result) {
        this.driver = new ChromeDriver();
        this.question = question;
        this.answer = answer;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] getFAQs(){
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой.",true},
                {"Сколько это стоит? И как оплатить?", null, false},
                {"Хочу сразу несколько самокатов! Так можно?","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",true},
                {"Хочу сразу несколько самокатов! Так можно?", null, false},
                {"Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",true},
                {"Как рассчитывается время аренды?", null, false},
                {"Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее.",true},
                {"Можно ли заказать самокат прямо на сегодня?", null, false},
                {"Можно ли продлить заказ или вернуть самокат раньше?","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",true},
                {"Можно ли продлить заказ или вернуть самокат раньше?", null, false},
                {"Вы привозите зарядку вместе с самокатом?","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",true},
                {"Вы привозите зарядку вместе с самокатом?", null, false},
                {"Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",true},
                {"Можно ли отменить заказ?", null, false},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.",true},
                {"Я жизу за МКАДом, привезёте?", null, false}
        };
    }
    @Test
    public void checkFAQSectionOnProperAnswer() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        FAQSectionPageScooter objFAQSection = new FAQSectionPageScooter(driver);

        objFAQSection.waitForPageLoading();
        objFAQSection.scrollDownToActualView();

        assertEquals(result, objFAQSection.getActualAnswersForFAQ(question).trim().equals(answer));
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
