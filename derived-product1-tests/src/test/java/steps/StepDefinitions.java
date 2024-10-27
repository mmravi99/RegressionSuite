package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.DP1HomePage;
import utilities.ExtentManager;

import java.util.List;

import static utilities.BrowserFactory.*;

public class StepDefinitions {
    private final WebDriver driver = getDriver();
    DP1HomePage dp1HomePage = new DP1HomePage(driver);
    int totalSlides=0;

    @Given("I open application")
    public void iOpenApplication() {

        driver.get(getBaseUrl());
        ExtentManager.getTest().info(getBaseUrl()+" Opened");

    }

    @And("I close the application")
    public void iCloseTheApplication() {
        cleanupDriver();
        ExtentManager.getTest().info("Application Opened");

    }

    @Then("I retrieved slide under tickets menu")
    public void iRetreivedSlideUnderTicketsMenu() {
        //List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'swiper-wrapper')]/div[contains(@class, 'swiper-slide')]"));
        totalSlides = dp1HomePage.getTotalSlides();
    }

    @Then("I attached details to the report")
    public void iAttachedDetailsToTheReport() {
        ExtentManager.getTest().info("Total No of Slides are <b>"+totalSlides+"</b>");

    }
}
