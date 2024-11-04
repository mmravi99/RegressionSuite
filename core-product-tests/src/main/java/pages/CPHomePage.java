package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ReusableLibrary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CPHomePage extends BasePage {
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(xpath="//div[@class='p-2 absolute right-3 hover:cursor-pointer']")
    private WebElement popupWindow; // Pop up Windo
    @FindBy(xpath = "//a[@class='accent-primary-border _link_ob2qz_1 text-2sm']//span[contains(text(),'Shop')]")
    private WebElement menuShop; //Shop Menu
    @FindBy(xpath = "//li[@role='menuitem']//li[@role='menuitem']//a[@title=\"Men's\"]")
    private WebElement menuMen;
    // Elements related to news & features
    @FindBy(xpath = "//a[contains(@class,'_link_ob2qz_1 text-2sm')]//span[contains(text(),'...')]")
    private WebElement moreLink;
    @FindBy(xpath = "//div[@class='dark-primary-background _MainNavAdWrapper_f419v_15']")
    private WebElement slideSection;
    @FindBy(xpath = "//body/div[@id='__next']/main/div/div[2]/div[1]/div[1]")
    private WebElement videoSection;

    public CPHomePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);

    }

    public void waitUntilSlideSection(){
        wait.until(ExpectedConditions.elementToBeClickable(slideSection));
    }
    public void waitUntilVideoSection(){
        wait.until(ExpectedConditions.elementToBeClickable(videoSection));
    }

    public void navigateToMensSection() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(menuShop));
        actions.moveToElement(menuShop).perform();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(menuMen)).click();
    }

    public void closePopUpWindow(){
        wait.until((ExpectedConditions.elementToBeClickable(popupWindow))).click();
    }





    public void navigateToNewsAndFeatures() throws InterruptedException {
        ReusableLibrary.waitForPageToLoad(3000);
        wait.until(ExpectedConditions.elementToBeClickable(moreLink));
        actions.moveToElement(moreLink).perform();
        driver.get("https://www.nba.com/warriors/news");
    }

    public int countVideosOlderThanThreeDays() {

        int count = 0;
        List<WebElement> videoElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//body/div[@id='__next']/main/div[2]/div/div/div/div[2]/div/ul/li")));
        int size = videoElements.size();

        for (int i = 3; i <= size; i++) {
            String durationText = driver.findElement(By.xpath("//body/div[@id='__next']/main/div[2]/div/div/div/div[2]/div/ul/li[" + i + "]/div/div/div/div/div[3]")).getText().split("\n")[0];
            if (durationText.contains("d")) {
                int days = Integer.parseInt(durationText.substring(0, durationText.length() - 1));
                if (days >= 1) {
                    System.out.println("Video Duration: " + durationText);
                    count++;
                }
            }
        }
        System.out.println("Total videos more than 3 days old: " + count);
        return count;
    }
}
