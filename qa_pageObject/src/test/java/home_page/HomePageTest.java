package home_page;

import common.CommonTest;
import core.hotline.page_object.appliances_page.AppliancesPage;
import core.hotline.page_object.common.utils.PropertyUtils;
import core.hotline.page_object.common.utils.WebDriverUtils;
import core.hotline.page_object.device_page.DevicePage;
import core.hotline.page_object.home_page.HomePage;
import core.hotline.page_object.search_result_page.SearchResultPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class HomePageTest extends CommonTest {


    public HomePageTest() throws IOException {
    }

    @Before
    public void webDriverSetUp() throws IOException {
        driverSetUp();
        HomePage homePage = new HomePage(getDriver());
        String pagePage = openPage(homePage);
        getDriver().get(pagePage);
    }

    @Test
    public void searchBarTesting(){
        HomePage homePage = new HomePage(getDriver());
        String searchCondition = "Samsung";
        SearchResultPage searchResultPage = homePage.searchByText(searchCondition);
        List<String> titles = searchResultPage.getSearchResluts();
        for(String title:titles){
            assertTrue(String.format("There is incorrect item text. ER: [%s]. AR: [%s]",
                    searchCondition, title),
                    title.contains(searchCondition));
        }
    }

    @Test
    public void LoginFormAppearanceTesting(){
        HomePage homePage = new HomePage(getDriver());
        homePage.openLoginForm();
        homePage.checkLoginFormIsShown();
    }

    @Test
    public void getListOfCategoriesInFileTesting(){

    }

    @After
    public void webDriverTearDown(){
        tearDown();
    }
}
