package pages.bmw_pages.BMW_car_page;

        import core.bmw.page_object.bmw_car_page.BMWCarPage;
        import pages.common.CommonTest;
        import core.bmw.page_object.home_page.HomePage;
        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;

        import java.io.IOException;
        import java.util.concurrent.TimeUnit;


public class BMWCarPageTest extends CommonTest {


    public BMWCarPageTest() throws IOException {
    }

    @Before
    public void webDriverSetUp() throws IOException {
        driverSetUp();
        getDriver().get("https://www.bmw.ua/ru/index.html");
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    @Test
    public void getResultsInListTesting(){
        HomePage homePage = new HomePage(getDriver());
        BMWCarPage BMWCarPage = new BMWCarPage(getDriver());

        homePage.openTab("Автомобили BMW");
        BMWCarPage.checkTabTitleBlue("Автомобили BMW");

        String carTypes[] = {"Кабриолет", "Купе"};
        for (String car:carTypes){
            BMWCarPage.activateCheckBox(car);
        }

        BMWCarPage.getAllTitlesInCategoriesList("Кабриолет");
        BMWCarPage.getAllTitlesInCategoriesList("Купе");
    }

    @After
    public void webDriverTearDown(){
        tearDown();
    }
}
