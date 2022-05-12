package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;

public class BasePage {

    protected Random random = new Random();
    protected WebDriver driver;
    protected WebDriverWait wait;
    private JavascriptExecutor js = (JavascriptExecutor) driver;
    private static final Logger log = LoggerFactory.getLogger(BasePage.class);
    private Actions actions;
    final int WAIT_DURATION = 20;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION));
        actions = new Actions(driver);
    }


    public void scrollTo(WebElement element) {
        //todo general method

    }

    public void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


    public void scrollToElement(WebElement element) {
        int limiter = 0;
        while (!isElementVisible(element) && limiter < 10) {
            js.executeScript("window.scrollBy(0,250)", "");
            limiter++;
        }
        log.info("Scrolled to element: " + element);
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void hoverOverElement(WebElement element) {
        waitUntilVisible(element);
        actions.moveToElement(element).perform();
        log.info("Hover over element: " + element.toString());
    }

    public void waitUntilVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException timeout) {
            log.error("Element " + element.getAttribute("title").toString() + " was not visible after " + WAIT_DURATION + " seconds");
            assert false;
        }
    }

    public void switchTo(WebElement elementOld, WebElement elementNew) {
        try {
            ExpectedCondition<Boolean> stalenessOfOldElement = ExpectedConditions.stalenessOf(elementOld);
            ExpectedCondition<WebElement> visibilityOfNewElement = ExpectedConditions.visibilityOf(elementNew);
            ExpectedCondition<Boolean> readyToSwitch = ExpectedConditions.and(stalenessOfOldElement, visibilityOfNewElement);

            wait.until(readyToSwitch);
        } catch (TimeoutException timeout) {
            log.error("Waited " + WAIT_DURATION + " seconds for element: " + elementNew.toString());
        }

    }
}