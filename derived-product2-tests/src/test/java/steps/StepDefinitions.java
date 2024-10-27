package steps;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.DP2HomePage;
import utilities.BrowserFactory;
import utilities.ExtentManager;
import utilities.ReusableLibrary;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static utilities.BrowserFactory.*;

public class StepDefinitions {
    private final Set<String> uniqueLinks = new HashSet<>();
    private final Set<String> duplicateLinks = new HashSet<>();
    private final WebDriver driver = getDriver();
    DP2HomePage dp2HomePage = new DP2HomePage(driver);

    @Given("I open application")
    public void iOpenApplication() {
        try {
            driver.get(getBaseUrl());
            ExtentManager.getTest().info(BrowserFactory.getApplication() +
                    " opened successfully at <b><font color='green'>" +
                    getBaseUrl() + "</font></b>");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to open application: " + e.getMessage());
            throw new RuntimeException("Test failed during application opening.", e);
        }
    }

    @And("I close the application")
    public void iCloseTheApplication() {
        try {
            cleanupDriver();
            ExtentManager.getTest().info("Application closed successfully.");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to close the application: " + e.getMessage());
        }
    }

    @Then("I store all hyperlinks of the footer links into a CSV file")
    public void iStoreAllHyperlinksOfTheFooterLinksIntoACSVFile() {
        List<String[]> linkData = new ArrayList<>(); // To store links and their status for CSV

        try {
            List<WebElement> links = dp2HomePage.retrieveFooterLinks();
            for (WebElement link : links) {
                String url = link.getAttribute("href");
                if (url != null) {
                    if (!uniqueLinks.add(url)) {
                        duplicateLinks.add(url);
                        linkData.add(new String[]{url, "Duplicate"});
                    } else {
                        linkData.add(new String[]{url, "Unique"});
                    }
                }
            }
            ReusableLibrary.writeLinksToCsv(linkData);

        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to retrieve or process footer links: " + e.getMessage());
            throw new RuntimeException("Test failed during footer link processing.", e);
        }
    }

    @And("I report if any duplicate hyperlinks are present in the footer")
    public void iReportIfAnyDuplicateHyperlinksArePresentInTheFooter() {
        if (!duplicateLinks.isEmpty()) {
            ExtentManager.getTest().info("Duplicate links found:");
            ExtentManager.getTest().info(MarkupHelper.createUnorderedList(duplicateLinks).getMarkup());
        } else {
            ExtentManager.getTest().info("No duplicate links found in the footer.");
        }
    }
}
