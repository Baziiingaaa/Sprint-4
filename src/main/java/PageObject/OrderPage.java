package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    // Локаторы для первой страницы заказа
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By metroElement = By.className("Order_Text__2broi");
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Локаторы для второй страницы заказа
    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-arrow");
    private final By blackCheckbox = By.id("black");
    private final By greyCheckbox = By.id("grey");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    private final By confirmButton = By.cssSelector("div#root > div > div:nth-of-type(2) > div:nth-of-type(5) > div:nth-of-type(2) > button:nth-of-type(2)");
    private final By orderSuccessModal = By.className("Order_ModalHeader__3FDaJ");
    private final By orderSuccessMessage = By.xpath("//*[contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstPage(String name, String surname, String address, String metroStation, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroInput).sendKeys(metroStation);
        driver.findElement(metroElement).click();
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    public void fillSecondPage(String date, String period, String color, String comment) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(dateInput));

        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(By.xpath(String.format(".//div[@class='Dropdown-option' and text()='%s']", period))).click();

        if ("black".equals(color)) {
            driver.findElement(blackCheckbox).click();
        } else {
            driver.findElement(greyCheckbox).click();
        }

        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmButton));
        driver.findElement(confirmButton).click();
    }

    public boolean isOrderSuccessModalDisplayed() {
        return driver.findElement(orderSuccessModal).isDisplayed();
    }

    public String getOrderSuccessMessage() {
        return driver.findElement(orderSuccessMessage).getText();
    }
}