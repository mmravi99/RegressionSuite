package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.BrowserFactory;

import static utilities.BrowserFactory.*;

@CucumberOptions(
        features = "src/test/java/features/CoreProductValidation.feature",
        glue = "steps"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }
    @BeforeSuite
    @Parameters("cucumber.filter.tags")
    public void setCucumberTags(String tags) {
        System.setProperty("cucumber.filter.tags", tags);
    }
    static {
        String cucumberTags = System.getProperty("cucumber.filter.tags");
        if (cucumberTags != null) {
            System.setProperty("cucumber.options", "--tags " + cucumberTags);
        }

    }
}
