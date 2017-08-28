package core.rozetka.page_object.rozetka_comparizon_page;

import core.common.AbstractPage;
import core.hotline.page_object.search_result_page.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RozetkaComparizonPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='btn-link-to-compare']")
    private WebElement compareItemsBtn;

    private WebDriver driver;

    public RozetkaComparizonPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public void openListOfParameters(){
        compareItemsBtn.click();
    }
}