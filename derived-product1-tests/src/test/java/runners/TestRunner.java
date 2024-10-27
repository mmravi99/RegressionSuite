package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.ExtentManager;

@CucumberOptions(
        features = "src/test/java/features/DerivedProduct1Validation.feature",
        glue = "steps"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
