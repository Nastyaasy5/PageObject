package core.hotline.page_object.appliances_page;

import core.hotline.page_object.common.AbstractPage;
import core.hotline.page_object.common.annotations.DefaultPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

@DefaultPath(defaultPath = "/bt/")
public class AppliancesPage extends AbstractPage {

    @FindBy(xpath = "//div[text()='Выберите категорию']")
    private WebElement categoryDdl;

    @FindBy(xpath = "//select//option[text()=\"Выберите производителя\"]/..")
    private WebElement manufacturerSelect;

    @FindBy(xpath = "//select//option[text()=\"Выберите производителя\"]/../..")
    private WebElement manufacturerDdl;

    @FindBy(xpath = "//select//option[text()=\"Выберите модель\"]/..")
    private WebElement modelSelect;

    @FindBy(xpath = "//select//option[text()=\"Выберите модель\"]/../..")
    private WebElement modelDdl;

    @FindBy(xpath = "//a[text()='Показать']")
    private WebElement showBtn;

    @FindBy(xpath = "//h1[contains(@class, 'title')]")
    private WebElement productTitle;

    @FindAll({@FindBy(xpath = "//div[@class='select']")})
    private List<WebElement> DdlList;

    private WebDriver driver;

    public AppliancesPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public AppliancesPage() {
        super(null);
    }

    public void selectCategory(String selection) {
        waitForElementToBeEnabled(categoryDdl);
        categoryDdl.click();
        findAndSelectInDdl(selection);
    }

    public void selectManufacturer(String selection)  {
        waitForAttributeToBe(manufacturerSelect);
        manufacturerDdl.click();
        findAndSelectInDdl(selection);
    }

    public void selectModel(String selection) {
        waitForAttributeToBe(modelSelect);
        modelDdl.click();
        findAndSelectInDdl(selection);
    }

    public void showResults() {
        showBtn.click();
    }

    public void findAndSelectInDdl(String selection){
        WebElement element = driver.findElement(By.xpath("//div[@class='dropdown']//li[text()='" + selection + "']"));
        element.click();
    }

    public List<String> getSelectedValuesList() {
        List<String> selectedValuesList = new ArrayList<String>();
        for (WebElement ddl : DdlList) {
            selectedValuesList.add(ddl.getText());
        }
        return selectedValuesList;
    }

    public String getSelectedValues(){
        String selectedValuesString = new String();
        List<String> selectedValuesList = getSelectedValuesList();
        for (String value : selectedValuesList){
            selectedValuesString = selectedValuesString + " " + value;
        }
        String selectedValues = selectedValuesString.substring(1, selectedValuesString.length());
        selectedValues = selectedValues.replaceAll(",", "");
        selectedValues = selectedValues.replaceAll("Электрочайники", "Электрочайник");
        return selectedValues;
    }

    public String getProductTitle(){
        return productTitle.getText();
    }
}


