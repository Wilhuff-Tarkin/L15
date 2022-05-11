package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuickViewProductPage extends BasePage{


    @Getter
    @FindBy(css = "#bigpic")
    private WebElement bigProductPic;


    @Getter
    @FindBy(css = "#thumbs_list_frame")
    private WebElement thumbnailPics;

    @Getter
    @FindBy(css = "#product_reference")
    private WebElement productId;

    @Getter
    @FindBy(css = ".fancybox-iframe")
    private WebElement iframe;



    public QuickViewProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void waitUntilLoaded() {
        waitUntilVisible(productId);
        //just checkin
    }

    public void switchToIframe() {
        switchTo(iframe);
    }
}
