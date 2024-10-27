package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NewsAndFeaturesPage extends BasePage {
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(xpath = "//h3[normalize-space()='VIDEOS']")
    private WebElement headerVideo;

    @FindBy(xpath = "//body[1]/div[1]/main[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/ul[1]/li")
    private List<WebElement>  tableVideo;

    @FindBy(xpath = "//body[1]/div[1]/main[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/ul[1]/li/div[1]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/time[1]/span[1]")
    private List<WebElement> videoDurationElements;

    public NewsAndFeaturesPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void waitForTableVideo(){
        wait.until(ExpectedConditions.visibilityOfAllElements(tableVideo));
    }
    public int countVideosOlderThanThreeDays(int d) {
        int count = 0;

        for (WebElement durationElement : videoDurationElements) {
            String durationText = durationElement.getText().split("\n")[0];

            if (durationText.contains("d")) {
                int days = Integer.parseInt(durationText.substring(0, durationText.length() - 1));
                if (days >= d) {
                    System.out.println("Video Duration: " + durationText);
                    count++;
                }
            }
        }

        System.out.println("Total videos more than 3 days old: " + count);
        return count;
    }
    public int getVideoCount(){
        return wait.until(ExpectedConditions.visibilityOfAllElements(tableVideo)).size();
    }



}
