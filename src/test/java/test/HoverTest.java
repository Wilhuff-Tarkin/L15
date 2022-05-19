package test;

import base.TestBase;
import pages.BasePage;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.HomePage;
import pages.QuickViewProductPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class HoverTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger("Hover test");

    @Test
    void shouldOpenSubCategoryPage() {
        log.info("Opening sub category page...");
        getToBlousesSubCategoryPage();
        assertThat("Category not opened", driver.getTitle().equals(testEnvironment.returnValueAsString("blousesCategoryTitle")));
    }

    @Test
    void shouldOpenQuickViewProductPage() {
        log.info("Opening product quick view...");
        val productPage = getProductViaQuickView();
        assertThat("Product page not opened", !productPage.getShortDescription().getText().isEmpty());
    }

    @Test
    void bigPictureShouldChangeAsPerHover() {
        log.info("Checking if big picture changes as per hover...");
        val productPage = getProductViaQuickView();
        assertThat("Main picture do not display thumbnail picture after hovering over thumbnails",
                productPage.checkAllThumbnails());
    }

    private QuickViewProductPage getProductViaQuickView() {
        val blousesCategory = new CategoryPage(driver);
        val productPage = blousesCategory.openQuickView(0);
        log.info("Opened quick view for: " + productPage.getItemName());
        return productPage;
    }

    private CategoryPage getToBlousesSubCategoryPage() {
        val homePage = new HomePage(driver);
        val categoryPage = homePage.goToBlouses();
        categoryPage.waitUntilVisible(categoryPage.getCategoryHeading());
        log.info("Opened category: " + categoryPage.getCategoryHeading().getText());
        return categoryPage;
    }

}
