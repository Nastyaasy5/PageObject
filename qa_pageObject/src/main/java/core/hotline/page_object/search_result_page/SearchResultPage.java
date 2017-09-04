package core.hotline.page_object.search_result_page;

import core.common.AbstractPage;
import core.common.annotations.DefaultPath;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.*;

import static org.junit.Assert.assertTrue;

@DefaultPath(defaultPath = "/sr/?q=Samsung")
public class SearchResultPage extends AbstractPage {

    @FindAll({@FindBy(xpath = "//a[@data-eventlabel=\"Product name\"]")})
    private List<WebElement> resultsList;

    @FindBy(xpath = "//h1[@datatype=\"card-title\"]")
    private WebElement productTitle;

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getSearchResluts() {
        List<String> resultsTitles = new ArrayList<String>();
        for(WebElement  element : resultsList){
            resultsTitles.add(element.getText());
        }
        return resultsTitles;
    }

    public List<String> getOpenedProductsList() {
        int qntyOfShownResults = resultsList.size();

        //double qntyOfViewedProducts = Math.random()*(qntyOfShownResults - 1) + 1;
        int qntyOfViewedProducts = 2;
        ArrayList<String> openedProductsList = new ArrayList<String>();
        for(int i=1; i<=qntyOfViewedProducts; i++){
            double randomProduct = Math.random()*(qntyOfShownResults - 1) + 1;
            int randomProuductInt = (int) randomProduct;
            WebElement product = resultsList.get(randomProuductInt);
            moveToElement(product);
            product.click();
            openedProductsList.add(productTitle.getText());
            driver.navigate().back();
        }
        Set<String> set = new HashSet<String>(openedProductsList);
        openedProductsList.clear();
        openedProductsList.addAll(set);
        return openedProductsList;

}
    
    public List<String> getViewedItemTitleList(){
        viewedBtn.click();
        List<String> viewedItemTitleList = new ArrayList<String>();
        for (WebElement viewedItem : viewedItemsList){
            String viewedItemTitle = viewedItem.getText();
            //change " "
            viewedItemTitleList.add(viewedItemTitle);
        }
        return viewedItemTitleList;
    }

    public void checkViewedItems(){
        List<String> openedProductsList = getOpenedProductsList();
        List<String> viewedItemTitleList = getViewedItemTitleList();
        for(String product:openedProductsList){
            Assert.assertTrue(String.format("Product [%s] is not in the list", product),
                    viewedItemTitleList.contains(product));
        }
        Assert.assertTrue(String.format("Incorrect quantity of items in viewed tab. Quantity of opened items: [%s]. " +
                "Quantity of items in viewed tab: [%s]", openedProductsList.size(), viewedItemsList.size()),
                openedProductsList.size()==viewedItemsList.size());
    }
}
