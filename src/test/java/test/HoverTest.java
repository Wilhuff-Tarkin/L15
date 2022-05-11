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
    void shouldOpenProductPage() {

        val womenCat = getToWomenCategoryPage();
        val productPage = getProductViaQuickview(womenCat);
        assertThat("Product page not opened", productPage.getIframe().isDisplayed());

    }

    private QuickViewProductPage getProductViaQuickview(CategoryPage womenCat) {
        womenCat.scrollToProducts();
        womenCat.hoverOverProduct(0);
        womenCat.goToProductPageViaQuickView(0);
        val productPage = new QuickViewProductPage(driver);
        //tu brakuje waita

        productPage.switchToIframe();
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
//
//
////        ztestBasesprawdz czy Title jest iwdoczny
//
//
//        assercja czy zdjecie pani w bluzce jest widoczne;
//
//        System.out.println("all good");
//
//    }
//
//    przejscie do kategori(){
//        hover to women (wait na reklame)
//        hover to blouses (wait na tooltip)
//        click
//        scroll to picutre
//    }
//
//    @Test
//    void shouldChangePicturesOnHover() {
//przejscie do kateogri();
//hover na zdjecie()
//        quickviewClieck
//                newproductPage
//
//        System.out.println("all good");
//
//    }


}
