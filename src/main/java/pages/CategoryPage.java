package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CategoryPage extends BasePage {
    @Getter
    @FindBy(css = ".page-heading.product-listing")
    private WebElement categoryHeading;
    @Getter
    @FindBy(css = ".left-block .product-image-container")
    private List<WebElement> productImageContainers;
    @Getter
    @FindBy(css = "a.quick-view")
    private WebElement quickViewButton;
    @Getter
    @FindBy(css = ".product_list.grid.row")
    private WebElement productGrid;

    private final String iframeCssLocator = ".fancybox-iframe";

    public CategoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void hoverOverProduct(int index) {
        hoverOverElement(productImageContainers.get(index));
        waitUntilVisible(quickViewButton);
    }

    public void goToProductPageViaQuickView() {
        clickOnElement(quickViewButton);
    }

    public QuickViewProductPage openQuickView(int index) {
        hoverOverProduct(index);
        goToProductPageViaQuickView();
        switchDriverTo(iframeCssLocator);
        return new QuickViewProductPage(driver);
    }
}


