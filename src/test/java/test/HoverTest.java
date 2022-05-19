package test;

import base.TestBase;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;

public class HoverTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger("Hover test");

    @Test
    void shouldOpenSubCategoryPage() {
        log.info("Opening sub category page...");
        base.getToBlousesSubCategoryPage();
        assertThat("Category not opened", driver.getTitle().equals(testEnvironment.returnValueAsString("blousesCategoryTitle")));
    }

    @Test
    void shouldOpenQuickViewProductPage() {
        log.info("Opening product quick view...");
        val productPage = base.getProductViaQuickView();
        assertThat("Product page not opened", !productPage.getShortDescription().getText().isEmpty());
    }

    @Test
    void bigPictureShouldChangeAsPerHover() {
        log.info("Checking if big picture changes as per hover...");
        val productPage = base.getProductViaQuickView();
        assertThat("Main picture do not display thumbnail picture after hovering over thumbnails",
                productPage.checkAllThumbnails());
    }
}
