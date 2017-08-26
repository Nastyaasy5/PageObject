package device_page;

import common.CommonTest;
import core.hotline.page_object.appliances_page.AppliancesPage;
import core.hotline.page_object.device_page.DevicePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    public void getProductDescriptionTesting(){
        DevicePage devicePage = new DevicePage(getDriver());
        devicePage.getDescription();
    }


    @After
    public void webDriverTearDown(){
            tearDown();
        }
}