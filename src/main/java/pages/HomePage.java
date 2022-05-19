package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @Getter
    @FindBy(css = ".sf-with-ul[title=\"Women\"]")
    private WebElement womenCategory;

    @Getter
    @FindBy(css = ".homeslider-container")
    private WebElement homeSlider;

    @Getter
    @FindBy(css = "#category-thumbnail .imgm")
    private WebElement categoryThumbnail;

    @Getter
    @FindBy(css = ".submenu-container.clearfix.first-in-line-xs li [title=\"Blouses\"]\n")
    private WebElement blousesLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilCategoriesAreVisible() {
        waitUntilVisible(womenCategory);
    }

    public CategoryPage goToBlouses() {
        waitUntilCategoriesAreVisible();
        hoverOverElement(getWomenCategory());
        waitUntilVisible(getCategoryThumbnail());
        getBlousesLink().click();
        return new CategoryPage(driver);
    }
}
