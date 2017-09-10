package core.bmw.page_object.home_page;

import core.common.AbstractPage;
import core.common.annotations.DefaultPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultPath(defaultPath = "")
public class HomePage extends AbstractPage {


    @FindBy(id = "searchbox")
    private WebElement searchBar;

    private WebDriver driver;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public void openTab(String tabName){
        WebElement element = driver.findElement(By
                .xpath("//div[@class='ds2-navigation-main--container']//a[text()='" + tabName + "']"));
        waitForElementToBeShown(element);
        element.click();
    }
}
