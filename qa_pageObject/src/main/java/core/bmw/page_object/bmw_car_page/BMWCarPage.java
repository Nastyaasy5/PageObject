package core.bmw.page_object.bmw_car_page;

import core.common.AbstractPage;
import core.common.annotations.DefaultPath;
import org.apache.xpath.SourceTree;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@DefaultPath(defaultPath = "")
public class BMWCarPage extends AbstractPage {

    public static final String ACTIVE_TAB_COLOR = "rgba(6, 83, 182, 1)";

    private WebDriver driver;

    public BMWCarPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public void checkTabTitleBlue(String tabName) {
        WebElement element = driver.findElement(By
                .xpath("//div[@class='ds2-navigation-main--container']//a[text()='" + tabName + "']"));
        String actualTabColor = element.getCssValue("color");
        Assert.assertTrue(String.format("Wrong tab color! Actual: [%s]. Expected: [%s]",
                actualTabColor, ACTIVE_TAB_COLOR), actualTabColor.equals(ACTIVE_TAB_COLOR));
    }

    public void activateCheckBox(String car) {
        WebElement checkBox = driver.findElement(By
                .xpath("//form[contains(@class, 'medium')]//label[text()='" + car + "']"));
        checkBox.click();
    }

    public List<String> getAllTitlesInCategoriesList(String keyWord) {
         List<WebElement> titles = driver.findElements(By
                .xpath("//h4[contains(text(), '" + keyWord + "')]"));

         return titles.stream()
                 .map(title->title.getText())
                 .collect(Collectors.toList());
    }

    public void openDetailsForCar(String carTitle) {
        WebElement car = driver.findElement(By
                .xpath("//h4[@class='ds2-model-card--title' and text()='" + carTitle + "']/../.."));
        car.click();
    }
}
