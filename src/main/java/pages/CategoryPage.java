package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CategoryPage extends BasePage {
    public CategoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @Getter
    @FindBy(css = ".page-heading.product-listing")
    private WebElement categoryHeading;

    @Getter
    @FindBy(css = ".left-block .product-image-container")
    private List<WebElement> productImageContainers;

    @Getter
    @FindBy(css = "a.quick-view")
    private WebElement quickViewButton;


    public void scrollToProducts() {
        scrollTo(productImageContainers.get(0));
    }

    public void hoverOverProduct(int index) {
        hoverOverElement(productImageContainers.get(index));
        waitUntilVisible(quickViewButton);
    }

    public void goToProductPageViaQuickView(int i) {
        quickViewButton.click();
    }
}
