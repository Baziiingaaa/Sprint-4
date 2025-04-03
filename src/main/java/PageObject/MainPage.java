package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    // Верхняя кнопка "Заказать"
    private final By upperOrderButton = By.className("Button_Button__ra12g");
    // Нижняя кнопка "Заказать"
    private final By lowerOrderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    // Кнопка "Статус заказа"
    private final By orderStatusButton = By.className("Header_Link__1TAG7");
    // Поле ввода номера заказа
    private final By orderNumberInput = By.xpath(".//input[@placeholder='Введите номер заказа']");
    // Кнопка "Go!" для проверки статуса
    private final By goButton = By.xpath(".//button[text()='Go!']");
    // Логотип Самоката
    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    // Логотип Яндекса
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");
    // Раздел "Вопросы о важном"
    private final By faqSection = By.className("Home_FAQ__3uVm4");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
    }

    public void clickLowerOrderButton() {
        driver.findElement(lowerOrderButton).click();
    }

    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    public void enterOrderNumber(String orderNumber) {
        driver.findElement(orderNumberInput).sendKeys(orderNumber);
    }

    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void expandFaqQuestion(int index) {
        driver.findElements(By.xpath(".//div[@class='accordion__button']")).get(index).click();
    }

    public String getFaqAnswerText(int index) {
        return driver.findElements(By.xpath(".//div[@class='accordion__panel']")).get(index).getText();
    }

    public void scrollToFaqSection() {
        WebElement faqElement = driver.findElement(faqSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqElement);
    }
}