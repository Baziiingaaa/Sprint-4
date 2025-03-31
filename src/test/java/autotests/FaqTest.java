package autotests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FaqTest {
    private WebDriver driver;
    private String baseUrl = "https://qa-scooter.praktikum-services.ru/";
    private final int NUMBER_OF_QUESTIONS = 8;
    private List<String> errorMessages;  // Список для хранения сообщений об ошибках

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseUrl);
        errorMessages = new ArrayList<>(); // Инициализируем список ошибок

        // Принятие куки
        try {
            WebElement cookieButton = driver.findElement(By.id("rcc-confirm-button"));
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка куки не найдена.");
        }
    }

    @Test
    public void testFAQDropdown() {
        // Локатор для всех элементов
        List<WebElement> questionItems = driver.findElements(By.xpath("//div[@class='accordion']/div[@class='accordion__item']"));

        // Массив ожидаемых ответов
        String[] expectedAnswers = new String[NUMBER_OF_QUESTIONS];
        expectedAnswers[0] = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        expectedAnswers[1] = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
        expectedAnswers[2] = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
        expectedAnswers[3] = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
        expectedAnswers[4] = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
        expectedAnswers[5] = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
        expectedAnswers[6] = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
        expectedAnswers[7] = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

        // Проверка каждого вопроса
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            try {
                // Находим вопрос и ответ в текущем элементе
                WebElement questionHeader = questionItems.get(i).findElement(By.xpath("./div[1]"));
                WebElement answerElement = questionItems.get(i).findElement(By.xpath("./div[2]/p"));

                // Кликаем на вопрос
                questionHeader.click();

                // Явное ожидание ответа
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOf(answerElement));

                // Получаем фактический ответ
                String actualAnswer = answerElement.getText();

                // Сравниваем ожидаемый и фактический ответ
                assertEquals("Ответ для вопроса " + (i + 1) + " не совпадает.", expectedAnswers[i], actualAnswer);
            } catch (AssertionError e) {
                // Добавляем сообщение об ошибке в список
                errorMessages.add("Вопрос " + (i + 1) + ": " + e.getMessage());
            } catch (Exception e) {
                errorMessages.add("Вопрос " + (i + 1) + ": Произошла ошибка при выполнении теста - " + e.getMessage());
            }
        }

        // Проверяем, были ли ошибки
        if (!errorMessages.isEmpty()) {
            StringBuilder errorMessageBuilder = new StringBuilder("Обнаружены ошибки в следующих вопросах:\n");
            for (String message : errorMessages) {
                errorMessageBuilder.append(message).append("\n");
            }
            throw new AssertionError(errorMessageBuilder.toString());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}