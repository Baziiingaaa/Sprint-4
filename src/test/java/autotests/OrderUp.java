package autotests;

import PageObject.generalPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class OrderUp {
    public WebDriver webDriver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;
    private final String period;
    private final String comment;

    public OrderUp(String name, String surname, String address,
                   String phone, String date, String period, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Хосе", "Радригез", "Москва",
                        "+79601234567", "31.12.2025", "сутки",
                        "Предупредить о приезде"},
        });
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver webDriver = new ChromeDriver();
        String baseUrl = "https://qa-scooter.praktikum-services.ru/";
        webDriver.get(baseUrl); // Открываем страницу baseUrl здесь
        generalPage generalPage = new generalPage(webDriver);
        // Принятие куки (если есть)
        try {
            WebElement cookieButton = webDriver.findElement(By.id("rcc-confirm-button"));
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка куки не найдена.");
        }
    }

    @Test
    public void firstSuccessfulOrder() throws IOException {


        PageObject.generalPage.clickOrderUp();
        PageObject.generalPage.selectBoulevardRokossovskogo();
        PageObject.generalPage.sendingFirstPage(name, surname, address, phone);
        PageObject.generalPage.sendingSecondPage(date, period, comment);
        PageObject.generalPage.clickOrderPlaced();
    }


    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}