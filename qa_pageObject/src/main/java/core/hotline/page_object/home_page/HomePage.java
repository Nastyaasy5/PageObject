package core.hotline.page_object.home_page;

import core.common.AbstractPage;
import core.common.annotations.DefaultPath;
import core.hotline.page_object.search_result_page.SearchResultPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DefaultPath(defaultPath = "")
public class HomePage extends AbstractPage {


    @FindBy(id = "searchbox")
    private WebElement searchBar;

    @FindBy(id = "doSearch")
    private WebElement searchBtn;

    @FindBy(xpath = "//b[text()='Войти']/..")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@id=\"lightbox-form\"]")
    private WebElement loginform;

    @FindAll({@FindBy(xpath = "//ul[contains(@class, \"menu\")]//li//a//b")})
    private List<WebElement> categoriesList;

    @FindAll({@FindBy(xpath = "//div[@class=\"m-sub-box\" and contains(@style,'block;')]//ul//li")})
    private List<WebElement> subCategoriesList;


    private static final String CATEGORY_SEPARATOR = "------------------------";

    private WebDriver driver;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public SearchResultPage searchByText(String text) {
        searchBar.clear();
        searchBar.sendKeys(text);
        searchBtn.click();
        return new SearchResultPage(driver);
    }

    public void openLoginForm() {
        loginBtn.click();
        waitForElementToBeVisible(loginBtn);
    }

    public void checkLoginFormIsShown() {
        Assert.assertTrue("Login form is not shown",
                loginform.getAttribute("style").contains("block"));
    }

    public Map<String, List<String>> getListOfCategories(){
        Map<String, List<String>> allCategoriesMap = new HashMap<String, List<String>>();

        for(WebElement category : categoriesList.subList(0,2)){
            //Used categoriesList.subList(0,2) to decrease time of execution of the test
            moveToElement(category);
            hover(category);
            String categoryTitle = CATEGORY_SEPARATOR + LINE_SEPARATOR + category.getText() +
                    ":" + LINE_SEPARATOR + CATEGORY_SEPARATOR + LINE_SEPARATOR;

            List<String> subCategoriesTitlesList = getListOfSubTitles();
            allCategoriesMap.put(categoryTitle, subCategoriesTitlesList);
        }
        return allCategoriesMap;
    }

    public List<String> getListOfSubTitles() {
        return subCategoriesList.stream()
                .filter(webElement->!webElement.getText().contains("Гид покупателя"))
                .map(item -> item.getText() + LINE_SEPARATOR)
                .collect(Collectors.toList());
    }

    public void getListOfCategoriesInFile() {
        Map<String, List<String>> allCategoriesMap = getListOfCategories();
        String fileName = "D:\\Git\\PageObject\\qa_pageObject\\src\\main\\resources\\All categories.txt";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            for (Map.Entry<String, List<String>> entry : allCategoriesMap.entrySet()) {
                String key = entry.getKey();
                byte[] keyBuffer = key.getBytes();
                fos.write(keyBuffer, 0, keyBuffer.length);

                for (String value : entry.getValue()) {
                    byte[] valueBuffer = value.getBytes();
                    fos.write(valueBuffer, 0, valueBuffer.length);
                }
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
