package core.common;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AbstractPage {

    protected WebDriver driver;

    @FindBy(xpath = "//span[@data-type=\"visited\"]")
    public WebElement viewedBtn;

    @FindAll({@FindBy(xpath = "//ul[@class=\"list-block\"]//li//div//a[@class=\"g_statistic\"]")})
    public List<WebElement> viewedItemsList;

    public AbstractPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void moveToElement(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement);
        actions.perform();
    }

    public void hoverAndClick(WebElement webElement) {
        Actions builder = new Actions(driver);
        builder.moveToElement(webElement).perform();
        builder.moveToElement(webElement).click().perform();
    }

    public void hover(WebElement webElement) {
        Actions builder = new Actions(driver);
        builder.moveToElement(webElement).perform();
    }

    public void waitForElementToBeShown(WebElement webElement) {
        try {
            if (!webElement.isDisplayed()) {
                driver.wait(10);
            }
        } catch (InterruptedException e) {
        }
    }

    public void waitForElementToBeEnabled(WebElement webElement) {
        try {
            if (!webElement.isEnabled()) {
                driver.wait(5);
            }
        } catch (InterruptedException e) {
        }
    }

    public void waitForAttributeToBe(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(element, "disabled", ""));
    }

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
