package test;

import base.TestBase;
import lombok.val;
import org.junit.jupiter.api.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.QuickViewProductPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class HoverTest extends TestBase {

    @Test
    void shouldOpenCategoryPage() {
        getToWomenCategoryPage();
        assertThat("Category not opened", driver.getTitle().equals("Blouses - My Store"));
    }

    @Test
    void shouldOpenQuickViewProductPage() {
        val productPage = getProductViaQuickView();
        assertThat("Product page not opened", productPage.getIframe().isDisplayed());
    }

    @Test
    void bigPictureShouldChangeAsPerHover() {
        val productPage = getProductViaQuickView();
        productPage.checkAllThumbnails();
    }

    private QuickViewProductPage getProductViaQuickView() {
        val womenCat = getToWomenCategoryPage();
        womenCat.hoverOverProduct(0);
        womenCat.goToProductPageViaQuickView(0);
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
