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
import java.util.ArrayList;
import java.util.List;

public class DP2HomePage extends BasePage {

    private WebDriverWait wait;

    @FindBy(xpath = "//footer[@role='contentinfo']//a")
    private List <WebElement> footerLinks;
    public DP2HomePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }
    public List<WebElement> retrieveFooterLinks() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(footerLinks));

    }
}
