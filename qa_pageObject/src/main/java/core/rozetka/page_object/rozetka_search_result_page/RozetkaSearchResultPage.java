package core.rozetka.page_object.rozetka_search_result_page;

import core.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RozetkaSearchResultPage extends AbstractPage {

    @FindBy(xpath = "//div[@data-view_type='catalog_with_hover' and .//*[contains(text(),'Apple iPhone 7 128GB')]]//div[@name='comparison_new_catalog']")
    private WebElement iPhone7CompareBtn;

    @FindBy(xpath = "//div[@data-view_type='catalog_with_hover' and .//*[contains(text(),'Apple iPhone 7 Plus 128GB')]]//div[@name='comparison_new_catalog']")
    private WebElement iPhone7PlusCompareBtn;

    @FindBy(id = "comparison")
    private WebElement compareBtn;

    private WebDriver driver;

    public RozetkaSearchResultPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public void addItemsInCompareList() {
        hover(iPhone7CompareBtn);
        waitForElementToBeShown(iPhone7CompareBtn);
        iPhone7CompareBtn.click();
        hover(iPhone7PlusCompareBtn);
        waitForElementToBeShown(iPhone7PlusCompareBtn);
        iPhone7PlusCompareBtn.click();
    }

    public void openComparisonPage(){
        compareBtn.click();
    }
}
