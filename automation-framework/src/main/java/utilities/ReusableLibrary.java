package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReusableLibrary {
    private static List<String> windowHandlesList = new ArrayList<>();

    public static void switchToChildWindow(WebDriver driver) {
        Iterator<String> windowHandles = driver.getWindowHandles().iterator();
        String mainWindowHandle = driver.getWindowHandle();

        while (windowHandles.hasNext()) {
            String childWindowHandle = windowHandles.next();
            if (!childWindowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(childWindowHandle);
                windowHandlesList.add(childWindowHandle);
                break;
            }
        }
    }

    public static void switchBackToParentWindow(WebDriver driver) {
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(0));
    }

    public static void switchBackToPreviousWindow(WebDriver driver) {
        if (windowHandlesList.size() > 1) {
            String currentWindow = driver.getWindowHandle();
            windowHandlesList.remove(currentWindow); // Remove current window from history

            driver.switchTo().window(windowHandlesList.get(windowHandlesList.size() - 1));
        } else {
            System.out.println("No previous window to switch back to.");
        }
    }

    public static void waitForPageToLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public static  void waitForPageToLoad(int s) throws InterruptedException {
        Thread.sleep(s);
    }
    public static void writeLinksToCsv(List<String[]> linkData) {
        try (FileWriter csvWriter = new FileWriter("links_report.csv")) {
            csvWriter.append("URL,Status\n");
            for (String[] row : linkData) {
                csvWriter.append(String.join(",", row)).append("\n");
            }
            ExtentManager.getTest().info("Links written to CSV file successfully: <b><font color='green'>links_report.csv</font></b>");
        } catch (IOException e) {
            ExtentManager.getTest().fail("Failed to write links to CSV file: " + e.getMessage());
            throw new RuntimeException("Error during CSV writing.", e);
        }
    }
    public static void launchApplication(WebDriver driver,String url){
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    }

}
