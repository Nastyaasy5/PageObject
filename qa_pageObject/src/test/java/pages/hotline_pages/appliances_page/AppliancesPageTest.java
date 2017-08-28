package pages.hotline_pages.appliances_page;

import pages.common.CommonTest;
import core.hotline.page_object.appliances_page.AppliancesPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class AppliancesPageTest extends CommonTest{

    public AppliancesPageTest() throws IOException {
    }

    @Before
    public void webDriverSetUp() throws IOException {
        driverSetUp();
        AppliancesPage appliancesPage = new AppliancesPage(getDriver());
        String pagePage = openPage(appliancesPage);
        getDriver().get(pagePage);
    }

    @Test
    public void searchResultTitleTesting() {
        AppliancesPage appliancesPage = new AppliancesPage(getDriver());
        appliancesPage.selectCategory("Электрочайники");
        appliancesPage.selectManufacturer("Zanussi");
        appliancesPage.selectModel("ZWA1260");
        String selectedValues = appliancesPage.getSelectedValues();
        appliancesPage.showResults();
        String productTitle = appliancesPage.getProductTitle();
        Assert.assertTrue(String.format("Title is wrong. Title: [%s]. Selected values: [%s]", productTitle, selectedValues), selectedValues.equals(productTitle));

    }

    @After
    public void webDriverTearDown(){
            tearDown();
        }
}
