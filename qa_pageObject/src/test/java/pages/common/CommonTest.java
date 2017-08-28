package pages.common;

import core.common.AbstractPage;
import core.common.utils.PropertyUtils;
import core.common.utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommonTest {

    private WebDriver driver;

    public CommonTest() throws IOException {
        PropertyUtils propertyUtils = new PropertyUtils();
        //String browserDriver = propertyUtils.getProperty("browser.driver");
        driver = new ChromeDriver();
        driverSetUp();
    }

    //Before
    public void driverSetUp() {
        System.setProperty("propertyUtils.getProperty(\"webdriver.browser.driver\")",
                "propertyUtils.getProperty(\"browser.driver.file\")");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    protected WebDriver getDriver(){
        return driver;
    }

    //After
    public void tearDown(){
        driver.close();
    }

    protected <T extends AbstractPage> String openPage(T page) throws IOException {
        return WebDriverUtils.getDefaultPathByPage(page);
    }
}
