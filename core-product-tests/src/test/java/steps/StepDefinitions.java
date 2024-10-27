package steps;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CPHomePage;
import pages.NewsAndFeaturesPage;
import pages.ShopPage;
import utilities.BrowserFactory;
import utilities.ExtentManager;
import utilities.ReusableLibrary;

import java.io.IOException;
import java.util.Map;

import static utilities.BrowserFactory.*;
import static utilities.JsonUtil.*;

public class StepDefinitions {
    private final WebDriver driver = getDriver();
    private final CPHomePage cpHomePage = new CPHomePage(driver);
    private final ShopPage shopPage = new ShopPage(driver);
    private final NewsAndFeaturesPage newsAndFeaturesPage = new NewsAndFeaturesPage(driver);
    private final JsonNode coreProductTestDta = readJsonAsTree("src/test/resources/testdata/testdata-core-product.json");
    Map<String, Object> tdMapTC001 = getTestDataForTcidAsMap(coreProductTestDta, "TC001");
    Map<String, Object> tcMapTC002 = getTestDataForTcidAsMap(coreProductTestDta, "TC002");

    public StepDefinitions() throws IOException {
    }

    @Given("I open application")
    public void iOpenApplication() {
        try {
            driver.get(getBaseUrl());
            cpHomePage.closePopUpWindow();
            ExtentManager.getTest().info(BrowserFactory.getApplication() + "\t<b><font color='green'>" + getBaseUrl() + "</font></b> opened successfully");
            Assert.assertTrue(driver.getCurrentUrl().contains(getBaseUrl()), "Application URL did not load correctly.");

        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to open application: " + e.getMessage());
            throw new RuntimeException("Test failed during application opening.");  // Mark the test as failed
        }
    }
    @And("I close the application")
    public void iCloseTheApplication() {
        try {
            cleanupDriver();
            ExtentManager.getTest().info("Application closed successfully ");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to close application: " + e.getMessage());
            throw new RuntimeException("Test failed during application closure.");  // Mark the test as failed
        }
    }

    @Then("I attach Jacket's price, title, and Top Seller message in the report")
    public void iAttachJacketSPriceTitleAndTopSellerMessageInTheReport() {
        try {
            ReusableLibrary.switchToChildWindow(driver);

            shopPage.navigateToJacketCategory();
            System.out.println("PAGE TITLE : "+tdMapTC001.get("mensPageTitle"));
            System.out.println("Driver Title : "+driver.getTitle());
            Assert.assertEquals(driver.getTitle(),tdMapTC001.get("mensPageTitle"));
            Map<String, String> productDetails = shopPage.captureProductDetails();
            ExtentManager.getTest().info("Jacket's price, title, and Top Seller message details are copied and available in <b><font color='green'>/core-product-tests/jacket-details.txt</font></b>");
            ExtentManager.getTest().info("Following are the list of details");
            ExtentManager.getTest().info(MarkupHelper.createUnorderedList(productDetails).getMarkup());
            Assert.assertNotNull(productDetails, "Failed to capture product details");
            ReusableLibrary.switchBackToParentWindow(driver);
        } catch (AssertionError e) {
            ExtentManager.getTest().fail("Failed to attach jacket details: " + e.getMessage());
            throw new RuntimeException("Test failed while attaching jacket details.");  // Mark the test as failed
        }catch (IOException e) {
            ExtentManager.getTest().fail("Failed to attach jacket details: " + e.getMessage());
            throw new RuntimeException("Test failed while attaching jacket details.");  // Mark the test as failed
        } catch (Exception e) {
            ExtentManager.getTest().fail("An unexpected error occurred: " + e.getMessage());
            throw new RuntimeException("Test failed due to an unexpected error.");  // Mark the test as failed
        }
    }

    @And("I navigate to {string} category")
    public void iNavigateToCategory(String category) {
        try {
            cpHomePage.navigateToMensSection();
            ExtentManager.getTest().info("Navigated to Men's section");

        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to navigate to Men's section: " + e.getMessage());
            throw new RuntimeException("Test failed during navigation to Men's section.");  // Mark the test as failed
        }
    }

    @And("I navigated to {string}")
    public void iNavigatedTo(String section) {
        try {
            cpHomePage.navigateToNewsAndFeatures();
            ExtentManager.getTest().info("Navigated to News and Features section");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to navigate to News and Features section: " + e.getMessage());
            throw new RuntimeException("Test failed during navigation to News and Features.");  // Mark the test as failed
        }
    }

    @Then("I count the total number of video feeds on the page older than {int} days")
    public void iCountTheTotalNumberOfVideoFeedsOnThePageOlderThanDays(int days) throws InterruptedException {
        try {
            //Map<String, Object> testDataMap = getTestDataForTcidAsMap(coreProductTestDta, "TC002");
            String noOfDays = tcMapTC002.get("noOfDays").toString();
            newsAndFeaturesPage.waitForTableVideo();
            int count = newsAndFeaturesPage.countVideosOlderThanThreeDays(Integer.parseInt(noOfDays));
            if (count > 0) {
                ExtentManager.getTest().info("Total videos more than " + noOfDays + " days old: " +"<b>"+ count+"</b>");
            } else {
                ExtentManager.getTest().info("<b><font color = orange>No videos found that are more than " + noOfDays + " days old.</font></b>");
            }        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to count videos older than " + days + " days: " + e.getMessage());
            throw new RuntimeException("Test failed during counting videos older than " + days + " days.");  // Mark the test as failed
        }
    }
}
