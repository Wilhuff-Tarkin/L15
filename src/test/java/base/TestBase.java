package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class TestBase {

    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    public static String automationPracticeURL = "http://automationpractice.com/";
    public static String automationPracticeTitle = "http://automationpractice.com/";
    public static String BLOUSES = "http://automationpractice.com/index.php?id_category=7&controller=category";
    public static ChromeOptions options = new ChromeOptions();
    public WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        log.info(">>>>>  Driver initiated successfully.");
        options.addArguments("Start-maximized");
    }

    public static WebElement getRandomElement(List<WebElement> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(options);
        driver.get(automationPracticeURL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        log.info(">>>>>  Driver closed successfully.");
    }



}
