package PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class generalPage {

    private static final String url = "https://qa-scooter.praktikum-services.ru/";

    //Локаторы для формы "Для кого самокат"
    private static final By orderUp = By.xpath(".//button[@class='Button_Button__ra12g']");
    private static final By orderLower = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private static final By nameSending = By.xpath("//input[@placeholder='* Имя']");
    private static final By surnameSending = By.xpath("//input[@placeholder='* Фамилия']"); //
    private static final By addressSending = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By metroSending = By.xpath("//input[@class='select-search__input']");
    private static final By telephoneSending = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static final By next = By.xpath(".//button[text()='Далее']");
    private static final By metroStantionSendingFirst = By.xpath("//li[@class='select-search__row']//button[contains(@class, 'Order_SelectOption__82bhS')]");
    private static final By metroStantionSendingSecond = By.xpath("//button[contains(@class, 'Order_SelectOption__82bhS') and .//div[text()='Красносельская']]");

    //Локаторы для формы "Про аренду"
    private static final By deliveryTime = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private static final By rentalPeriod = By.xpath("//div[@class='Dropdown-placeholder']");
    private static final By rentalDaysOne = By.xpath("//div[@class='Dropdown-option' and text()='сутки']");
    private static final By rentalDaysTwo = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']");
    private static final By commentSending = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private static final By order = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private static final By orderСonfirmation = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    private static final By orderPlaced = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    private static WebDriver webDriver;

    //Конструктор
    public generalPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Метод открытия страницы
    public static void open() {
        webDriver.get(url);
    }

    //Метод нажатия кнопки "Заказать"
    public static void clickOrderUp() {
        webDriver.findElement(orderUp).click();
    }

    //Метод нажатия кнопки "Заказать" нижняя
    public static void clickOrderLower() {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

            WebElement orderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(orderLower));

            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", orderButton);

        } catch (Exception e) {
            System.err.println("Не удалось найти и прокрутить к кнопке 'Заказать' внизу страницы: " + e.getMessage());
            e.printStackTrace();
        }
    }


    //Метод выбора станции метро "Бульвар Рокоссовского"
    public static void selectBoulevardRokossovskogo() {
        webDriver.findElement(metroSending).click();
        WebElement element = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(metroStantionSendingFirst));
        element.click();
    }
    //Метод выбора станции метро "Красносельская"
    public static void selectKrasnoselskaya() {
        webDriver.findElement(metroSending).click();
        WebElement element = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(metroStantionSendingSecond));
        element.click();
    }

    //  метод с учетом параметризации, заполнение первой страницы
    public static void sendingFirstPage(String name, String surname, String address, String phone) {
        webDriver.findElement(nameSending).sendKeys(name);
        webDriver.findElement(surnameSending).sendKeys(surname);
        webDriver.findElement(addressSending).sendKeys(address);
        webDriver.findElement(telephoneSending).sendKeys(phone);
        webDriver.findElement(next).click();
    }

    //  метод с учетом параметризации, заполнение второй страницы, первый тест
    public static void sendingSecondPage(String date, String period, String comment) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

            WebElement deliveryTimeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryTime));
            deliveryTimeInput.sendKeys(date);
            deliveryTimeInput.sendKeys(Keys.ENTER); // Подтверждение даты

            WebElement rentalPeriodDropdown = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriod));
            rentalPeriodDropdown.click();

            WebElement rentalDaysOneOption = wait.until(ExpectedConditions.elementToBeClickable(rentalDaysOne));
            rentalDaysOneOption.click();

            WebElement blackColorCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='black']")));
            blackColorCheckbox.click();

            WebElement commentField = wait.until(ExpectedConditions.visibilityOfElementLocated(commentSending));
            commentField.sendKeys(comment);

            WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(order));
            orderButton.click();

            WebElement orderСonfirm = wait.until(ExpectedConditions.elementToBeClickable(orderСonfirmation));
            orderСonfirm.click();

        } catch (Exception e) {
            System.err.println("Ошибка во втором шаге оформления заказа: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //  метод с учетом параметризации, заполнение второй страницы, второй тест
    public static void sendingSecondPageTwo(String date, String period, String comment) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

            WebElement deliveryTimeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryTime));
            deliveryTimeInput.sendKeys(date);
            deliveryTimeInput.sendKeys(Keys.ENTER); // Подтверждение даты

            WebElement rentalPeriodDropdown = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriod));
            rentalPeriodDropdown.click();

            WebElement rentalDaysOneOption = wait.until(ExpectedConditions.elementToBeClickable(rentalDaysTwo));
            rentalDaysOneOption.click();

            WebElement blackColorCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='grey']")));
            blackColorCheckbox.click();

            WebElement commentField = wait.until(ExpectedConditions.visibilityOfElementLocated(commentSending));
            commentField.sendKeys(comment);

            WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(order));
            orderButton.click();

            WebElement orderСonfirm = wait.until(ExpectedConditions.elementToBeClickable(orderСonfirmation));
            orderСonfirm.click();

        } catch (Exception e) {
            System.err.println("Ошибка во втором шаге оформления заказа: " + e.getMessage());
            e.printStackTrace();
        }
    }


    //Метод проверки успешного заказа
    public static boolean clickOrderPlaced() {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement confirmationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderPlaced));
            String actualText = confirmationElement.getText();
            return actualText.contains("Заказ оформлен");

        } catch (Exception e) {
            System.err.println("Не удалось проверить текст подтверждения заказа: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}




