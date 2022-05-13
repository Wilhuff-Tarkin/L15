package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class QuickViewProductPage extends BasePage{


    @Getter
    @FindBy(css = "#bigpic")
    private WebElement bigProductPic;

    @Getter
    @FindBy(css = "#product_reference")
    private WebElement productId;

    @Getter
    @FindBy(css = ".fancybox-iframe")
    private WebElement iframe;

    @Getter
    @FindBy(css = "#thumbs_list")
    private WebElement thumbs;



//    @Getter
//    @FindBy(css = "#thumbs_list_frame.img-responsive")
//    private List<WebElement> thumbnails = new ArrayList<>();


    public QuickViewProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void checkAllThumbnails() {

        List <WebElement> thumbnails = thumbs.findElements(By.cssSelector(".img-responsive"));

        System.out.println(thumbnails.size());

        for (int i = 0; i < thumbnails.size(); i++) {
            System.out.println(thumbnails.get(i).getText());
        }



    }
}
