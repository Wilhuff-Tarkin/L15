package pages;

import lombok.Getter;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class CategoryPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger("category page");
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

    @Getter
    @FindBy(css = ".product_list.grid.row")
    private WebElement productGrid;

    private String iframeCssLocator = ".fancybox-iframe" ;




    public void scrollToProducts() {
        scrollTo(productImageContainers.get(0));
    }

    public void hoverOverProduct(int index) {
        hoverOverElement(productImageContainers.get(index));
        waitUntilVisible(quickViewButton);
    }









    public void goToProductPageViaQuickView() {
        clickOnElement(quickViewButton);
    }

    private CategoryPage getToWomenCategoryPage() {
        val homePage = new HomePage(driver);
        homePage.waitUntilCategoriesAreVisible();
        homePage.hoverOverElement(homePage.getWomenCategory());
        homePage.waitUntilVisible(homePage.getCategoryThumbnail());
        homePage.getBlousesLink().click();
        val categoryPage = new CategoryPage(driver);
        categoryPage.waitUntilVisible(categoryPage.getCategoryHeading());
        return categoryPage;
    }

    public QuickViewProductPage openQuickView(int index) {
        hoverOverProduct(index);
        goToProductPageViaQuickView();
        switchToIframeAlt(iframeCssLocator);
        return new QuickViewProductPage(driver);

    }
}


