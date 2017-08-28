package pages.hotline_pages.device_page;

import pages.common.CommonTest;
import core.hotline.page_object.device_page.DevicePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class SamsungDevicePageTest extends CommonTest {

    public SamsungDevicePageTest() throws IOException {
    }

    @Before
    public void webDriverSetUp() throws IOException {
        driverSetUp();
        DevicePage devicePage = new DevicePage(getDriver());
        String pagePage = openPage(devicePage);
        getDriver().get(pagePage);
    }


    @Test
    public void pricesInRangeTesting() {
        DevicePage devicePage = new DevicePage(getDriver());
        devicePage.openAllProposals();
        devicePage.checkPricesInRange();
    }

    @Test
    public void getProductDescriptionInFileTesting(){
        DevicePage devicePage = new DevicePage(getDriver());
        devicePage.getDescriptionInFile();
    }


    @After
    public void webDriverTearDown(){
            tearDown();
        }
}
