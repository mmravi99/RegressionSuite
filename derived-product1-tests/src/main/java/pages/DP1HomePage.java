package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DP1HomePage extends BasePage{
    private WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@class, 'swiper-wrapper')]/div[contains(@class, 'swiper-slide')]")
    private List<WebElement> slides;
    public DP1HomePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public  int getTotalSlides(){
        return slides.size();
    }

}
