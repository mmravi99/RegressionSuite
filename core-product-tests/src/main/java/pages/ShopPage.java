package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopPage extends BasePage {
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(xpath = "//div[@data-trk-id='all-departments']//li[8]//a[1]")
    private WebElement jacketCategoryLink;
    @FindBy(xpath = "//div[@class='pagination-component']//li[@class='next-page']//i[@role='presentation']")
    private WebElement nextPageButton;
    @FindBy(xpath = "//div[@data-trk-id='product-grid']/div/div[2]/div")
    private List<WebElement> productElements;
    @FindBy(xpath = "//div[@data-talos='itemCount']")
    private WebElement itemCount;

    public ShopPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }
    public void navigateToJacketCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(jacketCategoryLink)).click();
    }
    public int getTotalPages() {
        String itemCountText = wait.until(ExpectedConditions.visibilityOf(itemCount)).getText().substring(10);
        int totalItems = Integer.parseInt(itemCountText);
        return totalItems / 72;
    }
    public Map<String, String> captureProductDetails() throws IOException {
        Map<String, String> productDetailsMap = new HashMap<>();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jacket-details.txt"))) {
            int totalPages = getTotalPages();

            for (int i = 1; i <= totalPages; i++) {
                wait.until(ExpectedConditions.visibilityOfAllElements(productElements));

                for (int k = 1; k <= productElements.size(); k++) {
                    WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-trk-id='product-grid']/div/div[2]/div[" + k + "]/div/div[2]/div[2]")));
                    WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-trk-id='product-grid']/div/div[2]/div[" + k + "]/div/div[2]/div[1]/div/div/div[1]/span[1]/span/span/span")));

                    String name = nameElement.getText();
                    String price = priceElement.getText();

                    writer.write(name + "\t" + price);
                    writer.newLine();

                    productDetailsMap.put(name, price);
                }
                wait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();
            }
        }
        return productDetailsMap;
    }



}
