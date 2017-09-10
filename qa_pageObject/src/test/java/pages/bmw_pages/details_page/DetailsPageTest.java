package pages.bmw_pages.details_page;

        import core.bmw.page_object.bmw_car_page.BMWCarPage;
        import core.bmw.page_object.details_page.DetailsPage;
        import pages.common.CommonTest;
        import core.bmw.page_object.home_page.HomePage;
        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;

        import java.io.IOException;
        import java.util.concurrent.TimeUnit;


public class DetailsPageTest extends CommonTest {


    public DetailsPageTest() throws IOException {
    }

    @Before
    public void webDriverSetUp() throws IOException {
        driverSetUp();
        getDriver().get("https://www.bmw.ua/ru/index.html");
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    @Test
    public void compareActAndExpInfoInDetailsTesting() throws InterruptedException {
        HomePage homePage = new HomePage(getDriver());
        BMWCarPage BMWCarPage = new BMWCarPage(getDriver());
        DetailsPage detailsPage = new DetailsPage(getDriver());

        homePage.openTab("Автомобили BMW");
        BMWCarPage.activateCheckBox("Кабриолет");
        BMWCarPage.openDetailsForCar("BMW 2 серии Кабриолет");
        detailsPage.expandDropDown("BMW 2 серии Кабриолет");
        detailsPage.selectOptionInDdl("Двигатели");
        detailsPage.openFirstSection();
        detailsPage.getTextInFileFromOpenedSection();
    }

    @After
    public void webDriverTearDown(){
        tearDown();
    }
}
