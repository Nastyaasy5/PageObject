package core.bmw.page_object.details_page;

        import core.common.AbstractPage;
        import core.common.annotations.DefaultPath;
        import org.junit.Assert;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindAll;
        import org.openqa.selenium.support.FindBy;

        import java.io.*;
        import java.util.List;
        import java.util.Scanner;

@DefaultPath(defaultPath = "")
public class DetailsPage extends AbstractPage {

    @FindBy(xpath = "//h5[@aria-controls=\"ui-id-2\"]")
    private WebElement firstSection;

    @FindAll(@FindBy(xpath = "//tbody"))
    private List<WebElement> detailsList;

    private WebDriver driver;

    public DetailsPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public void openDetailsForCar(String carTitle) {
        WebElement car = driver.findElement(By
                .xpath("//h4[@class='ds2-model-card--title' and text()='" + carTitle + "']/../.."));
        car.click();
    }

    public void expandDropDown(String carTitle) {
        WebElement ddl = driver.findElement(By
                .xpath("//a[contains(@title,'" + carTitle + "')]"));
        ddl.click();
    }

    public void selectOptionInDdl(String optionTitle) {
        WebElement option = driver.findElement(By
                .xpath("//div[contains(@class, 'content')]//li//a[text()='" + optionTitle + "']"));
        option.click();

    }

    public void openFirstSection() {
        moveToElement(firstSection);
        firstSection.click();
        //hardcode
        firstSection.click();
        firstSection.click();
    }

    public void getTextInFileFromOpenedSection() {
        String fileName = "D:\\Git\\PageObject\\qa_pageObject\\src\\main\\resources\\All details.txt";
        String fileName2 = "D:\\Git\\PageObject\\qa_pageObject\\src\\main\\resources\\Expected details.txt";
        String detailsText = detailsList.get(0).getText();

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] keyBuffer = detailsText.getBytes();
            fos.write(keyBuffer, 0, keyBuffer.length);

            BufferedReader reader1 = new BufferedReader(new FileReader(fileName));
            BufferedReader reader2 = new BufferedReader(new FileReader(fileName2));

            String line1 = reader1.readLine();
            String line2 = reader2.readLine();

            boolean areEqual = true;

            while (line1 != null || line2 != null)
            {
                if(line1 == null || line2 == null)
                {
                    areEqual = false;
                    break;
                }
                else if(! line1.equalsIgnoreCase(line2))
                {
                    areEqual = false;
                    break;
                }

                line1 = reader1.readLine();
                line2 = reader2.readLine();
            }

            if(areEqual)
            {
                System.out.println("Two files have same content.");
            }
            else
            {
                System.out.println("Two files have different content");
            }

            reader1.close();
            reader2.close();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
