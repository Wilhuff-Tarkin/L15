package test;

import base.TestBase;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.HomePage;
import pages.QuickViewProductPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class HoverTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger("hover test");

    @Test
    void shouldOpenCategoryPage() {
        log.info("Opening category page...");
        getToWomenCategoryPage();
        assertThat("Category not opened", driver.getTitle().equals("Blouses - My Store"));
    }

    @Test
    void shouldOpenQuickViewProductPage() {
        log.info("Opening product quick view...");
        val productPage = getProductViaQuickView();
        assertThat("Product page not opened", productPage.getIframe().isDisplayed());
    }

    @Test
    void bigPictureShouldChangeAsPerHover() throws InterruptedException {
        log.info("Checking if big picture changes as per hover...");
        val productPage = getProductViaQuickView();
     Thread.sleep(1203);
        productPage.checkAllThumbnails();
    }

    private QuickViewProductPage getProductViaQuickView() {
        val womenCat = getToWomenCategoryPage();
        womenCat.hoverOverProduct(0);
        womenCat.goToProductPageViaQuickView();
        womenCat.switchToIframe();
        val productPage = new QuickViewProductPage(driver);
        return productPage;
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



}
