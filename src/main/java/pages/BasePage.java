package pages;

import lombok.val;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {

    private static final Logger log = LoggerFactory.getLogger(BasePage.class);
    final int WAIT_DURATION = 15;
    protected WebDriver driver;
    protected WebDriverWait wait;
    private Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION));
        actions = new Actions(driver);
    }

    public void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void hoverOverElement(WebElement element) {
        waitUntilVisible(element);
        actions.moveToElement(element).perform();
    }

    public void waitUntilVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException timeout) {
            log.error("Element " + element.getAttribute("title") + " was not visible after " + WAIT_DURATION + " seconds");
            assert false;
        }
    }

    public void switchDriverTo(String locator) {

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        } catch (TimeoutException timeout) {
            log.error("Waited " + WAIT_DURATION + " seconds for iframe");
        }
        WebElement Iframe = driver.findElement(By.cssSelector(locator));
        driver.switchTo().frame(Iframe);
    }
}