package core.hotline.page_object.home_page;

import core.hotline.page_object.common.AbstractPage;
import core.hotline.page_object.common.annotations.DefaultPath;
import core.hotline.page_object.search_result_page.SearchResultPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

import static com.sun.jmx.snmp.ThreadContext.contains;

@DefaultPath(defaultPath = "")
public class HomePage extends AbstractPage {

    @FindBy(id = "searchbox")
    private WebElement searchBar;

    @FindBy(id="doSearch")
    private WebElement searchBtn;

    @FindBy(xpath = "//span[@class=\"user ico-sm\"]//a")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@id=\"lightbox-form\"]")
    private WebElement loginform;

    @FindAll({@FindBy(xpath = "//ul[contains(@class, \"menu\")]//li//a//b")})
    private List<WebElement> categoriesList;

    @FindAll({@FindBy(xpath = "//div[@class=\"m-sub-box\" and contains(@style,'block;')]")})
    private String subCategoriesList;

    private WebDriver driver;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public SearchResultPage searchByText(String text) {
        searchBar.clear();
        searchBar.sendKeys(text);
        searchBtn.click();
        return new SearchResultPage(driver);
    }

    public void openLoginForm() {
        loginBtn.click();
    }

    public void checkLoginFormIsShown() {
        waitForElementToBeShown(loginform);
        Assert.assertTrue("Login form is not shown",
                loginform.getAttribute("style").contains("block"));
    }

    public Map<String, String> getListOfCategoriesInFile(){
        
    }
}
