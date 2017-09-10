package pages.rozetka_pages;

import core.rozetka.page_object.rozetka_comparison_page.RozetkaComparisonPage;
import core.rozetka.page_object.rozetka_home_page.RozetkaHomePage;
import core.rozetka.page_object.rozetka_search_result_page.RozetkaSearchResultPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.common.CommonTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ComparisonPageTest extends CommonTest {


    public ComparisonPageTest() throws IOException {
    }

    @Before
    public void webDriverSetUp() throws IOException {
        driverSetUp();
        getDriver().get("https://rozetka.com.ua/");
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    @Test
    public void comparePropertiesTesting() throws InterruptedException {
        RozetkaHomePage homePage = new RozetkaHomePage(getDriver());
        RozetkaSearchResultPage searchResultPage = new RozetkaSearchResultPage(getDriver());
        RozetkaComparisonPage comparizonPage = new RozetkaComparisonPage(getDriver());

        homePage.searchByText("Iphone 7");
        searchResultPage.addItemsInCompareList();
        searchResultPage.openComparisonPage();
        comparizonPage.openListOfParameters();
        comparizonPage.comparePropertiesAreSame();
    }

    @After
    public void webDriverTearDown(){
        tearDown();
    }
}