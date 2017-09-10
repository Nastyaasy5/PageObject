package core.hotline.page_object.device_page;

import core.common.AbstractPage;
import core.common.annotations.DefaultPath;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DefaultPath(defaultPath = "/mobile-mobilnye-telefony-i-smartfony/samsung-galaxy-s8-64gb-black/")
public class DevicePage extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class, 'price-block')]//a[@data-id=\"prices\"]")
    private WebElement pricesScaleWE;

    @FindAll({@FindBy(xpath = "//a[@id='gotoshop-price']")})
    private List<WebElement> pricesInStoresWE;

    @FindBy(xpath = "//b[text()=\"Все предложения\"]")
    private WebElement allOffersTab;

    @FindAll({@FindBy(xpath = "//tbody//tr")})
    private List<WebElement> deviceDescriptionsList;

    @FindBy(xpath = "//h1")
    private WebElement deviceTitle;

    private WebDriver driver;

    public DevicePage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public void checkPricesInRange(){
        float toPriceFloat = getToPrice();
        float fromPriceFloat = getFromPrice();
        List<Float> priceList = getAllPricesInStores();

        for (float p : priceList){
            Assert.assertTrue(String.format("Price is out of the range. Price: [%s]. Price range: [%s] - [%s]",
                    p, fromPriceFloat, toPriceFloat),
                    (p<=toPriceFloat && p>=fromPriceFloat));
            }
        }

        public float getFromPrice(){
            getPrices();
            String pricesScale = getPrices();
            String fromPrice = pricesScale.substring(0, pricesScale.indexOf("–"));
            float fromPriceFloat = Float.parseFloat(fromPrice);
            return fromPriceFloat;
        }

        public float getToPrice(){
            String pricesScale = getPrices();
            String toPrice = pricesScale.substring(pricesScale.indexOf("–")+1, pricesScale.length());
            float toPriceFloat = Float.parseFloat(toPrice);
            return toPriceFloat;
        }

        public String getPrices(){
            String pricesScale = pricesScaleWE.getText();
            pricesScale = pricesScale.replaceAll(" ", "");
            pricesScale = pricesScale.replaceAll("[а-я]", "");
            pricesScale = pricesScale.replaceAll(",", ".");
            return pricesScale;
        }


        public List<Float> getAllPricesInStores(){
            List<Float> priceList = new ArrayList<Float>();
            for (WebElement priceInStore : pricesInStoresWE) {
                String price = priceInStore.getText();
                price = price.replaceAll(" ", "");
                price = price.replaceAll(",", ".");
                float priceFloat = Float.parseFloat(price);
                priceList.add(priceFloat);
        }
            return priceList;
    }

    public void openAllProposals(){
        moveToElement(allOffersTab);
        allOffersTab.click();
    }

    public void getDescriptionInFile(){
        String productTitle = deviceTitle.getText();;
        String fileName = "D:\\Git\\PageObject\\qa_pageObject\\src\\main\\resources\\Description for " + productTitle + ".txt";

        try(FileOutputStream fos=new FileOutputStream(fileName))
        {


            for (WebElement description : deviceDescriptionsList) {
                String descriptionText= description.getText() + System.lineSeparator();

                byte[] buffer = descriptionText.getBytes();

                fos.write(buffer, 0, buffer.length);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}


