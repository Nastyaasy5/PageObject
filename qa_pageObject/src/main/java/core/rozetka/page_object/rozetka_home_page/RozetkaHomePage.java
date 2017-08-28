package core.rozetka.page_object.rozetka_home_page;

import core.common.AbstractPage;
import core.hotline.page_object.search_result_page.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RozetkaHomePage extends AbstractPage {

    @FindBy(name = "text")
    private WebElement searchBar;

    @FindBy(name = "rz-search-button")
    private WebElement searchBtn;

    private WebDriver driver;

    public RozetkaHomePage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public SearchResultPage searchByText(String text) {
        searchBar.clear();
        searchBar.sendKeys(text);
        searchBtn.click();
        return new SearchResultPage(driver);
    }
}
