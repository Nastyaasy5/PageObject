package core.rozetka.page_object.rozetka_comparison_page;

import core.common.AbstractPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RozetkaComparisonPage extends AbstractPage {

    @FindBy(xpath = "//span[text()='Сравнить эти товары']")
    private WebElement compareItemsBtn;

    @FindAll(@FindBy(xpath = "//div[contains(text(), 'Тип матрицы')]/..//div[@class='comparison-t-cell']"))
    private List<String> matrixTypeList;

    private WebDriver driver;

    public RozetkaComparisonPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public void openListOfParameters(){
        compareItemsBtn.click();
    }

    public void comparePropertiesAreSame(){
        final String[] VALUES_TO_COMPARE = {"Тип матрицы", "Количество точек касания", "Поддержка 3G"};
        Map<String, String> firstItemMap = new HashMap<>();
        Map<String, String> secondItemMap = new HashMap<>();
        for (String title : VALUES_TO_COMPARE){
            List<WebElement> propertiesList = driver.findElements(By
                            .xpath("//div[contains(text(), '" + title + "')]/..//div[@class='comparison-t-cell']"));
            firstItemMap.put(title, propertiesList.get(0).getText());
            secondItemMap.put(title, propertiesList.get(1).getText());

            compareMapsAreEqual(firstItemMap, secondItemMap);
        }
    }

    public void compareMapsAreEqual(Map map1, Map map2){
            Assert.assertTrue("Selected roperties are different for products!", map1.entrySet().containsAll(map2.entrySet())
                    && map2.entrySet().containsAll(map1.entrySet()));
        }
    }