package search_result_page;

import common.CommonTest;
import core.hotline.page_object.search_result_page.SearchResultPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class SearchResultPageTest extends CommonTest{

    public SearchResultPageTest() throws IOException {
    }

    @Before
    public void webDriverSetUp() throws IOException {
        driverSetUp();
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        String pagePage = openPage(searchResultPage);
        getDriver().get(pagePage);
    }

    @Test
    public void viewedItemsTesting() {
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        searchResultPage.checkViewedItems();
    }

    @After
    public void webDriverTearDown(){
        tearDown();
    }
}
