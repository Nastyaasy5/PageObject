package pages.rozetka_pages.test_page;

import core.rozetka.page_object.rozetka_comparizon_page.RozetkaComparizonPage;
import core.rozetka.page_object.rozetka_home_page.RozetkaHomePage;
import core.rozetka.page_object.rozetka_search_result_page.RozetkaSearchResultPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.common.CommonTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestPage extends CommonTest {


    public TestPage() throws IOException {
    }

    @Before
    public void webDriverSetUp() throws IOException {
        driverSetUp();
        getDriver().get("https://rozetka.com.ua/");
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    @Test
    public void comparePropertiesTesting(){
        RozetkaHomePage homePage = new RozetkaHomePage(getDriver());
        RozetkaSearchResultPage searchResultPage = new RozetkaSearchResultPage(getDriver());
        RozetkaComparizonPage comparizonPage = new RozetkaComparizonPage(getDriver());

        homePage.searchByText("Iphone 7");
        searchResultPage.addItemsInCompareList();
        searchResultPage.openComparisonPage();
        comparizonPage.openListOfParameters();

    }

    @After
    public void webDriverTearDown(){
        tearDown();
    }
}