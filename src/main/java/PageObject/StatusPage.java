package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StatusPage {
    private final WebDriver driver;

    private final By notFoundImage = By.xpath(".//img[@alt='Not found']");

    public StatusPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNotFoundImageDisplayed() {
        return driver.findElement(notFoundImage).isDisplayed();
    }
}