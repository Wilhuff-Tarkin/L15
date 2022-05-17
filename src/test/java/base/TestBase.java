package base;

import configuration.handler.YamlReader;
import configuration.model.EnvironmentModel;
import configuration.model.YamlModel;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import configuration.handler.YamlReader;
import configuration.model.EnvironmentModel;
import configuration.model.YamlModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;


public class TestBase {

    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    public static String BLOUSES = "http://automationpractice.com/index.php?id_category=7&controller=category";
    public static ChromeOptions options = new ChromeOptions();
    public WebDriver driver;
    protected static EnvironmentModel testEnvironment;
    private static String loadedEnvironmentName;
    protected static YamlModel model;

    @BeforeAll
    static void setup() {

        initializeTestEnvironment();
        logBasicLoadInformation();
        logDetailedLoadInformation();
    }

    private static void initializeTestEnvironment() {
        WebDriverManager.chromedriver().setup();
        log.info(">>>>>  Driver initiated successfully.");
        options.addArguments("Start-maximized");

        model = new YamlReader().loadData();
        loadedEnvironmentName = model.getTestedDataSet();
        testEnvironment = new EnvironmentModel(model.getSpecificTestData(loadedEnvironmentName));
    }

    private static void logDetailedLoadInformation() {
        log.info("Tests will proceed using following data:");
        testEnvironment.getTestPropertiesMap().forEach((k, v) -> log.info("--> " + k + ": " + v));
    }

    private static void logBasicLoadInformation() {
        log.info(">>>> Parsed " + model.getAllTestData().size() + " sets of environment settings");
        log.info(">>>> Configuration loaded successfully. Performing test using test: " + loadedEnvironmentName.toUpperCase(Locale.ROOT));
        log.info(">>>> Environment " + loadedEnvironmentName.toUpperCase(Locale.ROOT) + " contains " + testEnvironment.getTestPropertiesMap().size() + " properties");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(options);
        driver.get(testEnvironment.returnValueAsString("appUrl"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        log.info(">>>>>  Driver closed successfully.");
    }


}
