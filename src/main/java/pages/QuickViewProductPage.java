package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class QuickViewProductPage extends BasePage {


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
    @FindBy(css = "#thumbs_list_frame")
    private WebElement thumbs;

    @Getter
    @FindBy(css = "#short_description_content")
    private WebElement shortDescription;

    @Getter
    @FindBy(css = ".pb-center-column.col-xs-12.col-sm-4 [itemprop = name]")
    private WebElement itemName;



    public QuickViewProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



    public boolean checkAllThumbnails() {

        boolean result = false;

        List<WebElement> thumbnails = thumbs.findElements(By.cssSelector("[id^=thumbnail] > a"));

        System.out.println(thumbnails.size());

        for (int i = 0; i < thumbnails.size(); i++) {
            String thumbPic = (thumbnails.get(i).getAttribute("href"));
            hoverOverElement(thumbnails.get(i));
            String bigPic = getBigProductPic().getAttribute("src");
            result = removeSuffix(thumbPic).equals(removeSuffix(bigPic));

            System.out.println(removeSuffix(thumbPic) + " " + removeSuffix(bigPic));
            if (!result) {
                return false;
            }

        }
        return result;
    }

    private String removeSuffix(String pictureUrl) {

        int index = pictureUrl.indexOf("-");
        if (index >= 0) {
            pictureUrl = pictureUrl.substring(0, index);
        }
        return pictureUrl;
    }

    @Override
    public String toString() {
        return "QuickViewProductPage{" +
                "bigProductPic=" + bigProductPic +
                ", productId=" + productId +
                ", iframe=" + iframe +
                ", thumbs=" + thumbs +
                '}';
    }
}
