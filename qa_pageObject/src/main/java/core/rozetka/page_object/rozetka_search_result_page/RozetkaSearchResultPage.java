package core.rozetka.page_object.rozetka_search_result_page;

import core.common.AbstractPage;
import org.junit.Assert;
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

    @FindBy(xpath = "//span[@class='hub-i-count']")
    private WebElement compareCounter;

    @FindBy(xpath = "//span[@class='hub-i-count' and text()='2']")
    private WebElement compareCounter2;

    //private final int QNTY_OF_ADDED_PRODUCTS = Integer.parseInt(compareCounter.getText());

    private WebDriver driver;

    public RozetkaSearchResultPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public void addItemsInCompareList() {
        addItemInCompareList(iPhone7CompareBtn);
        waitForElementToBeShown(compareCounter);

        addItemInCompareList(iPhone7PlusCompareBtn);
        //Ниже костыль
        waitForElementToBeShown(compareCounter2);
    }

    public void addItemInCompareList(WebElement element){
        moveToElement(element);
        hover(element);
        element.click();
    }
    public void openComparisonPage(){
        compareBtn.click();
    }
}
