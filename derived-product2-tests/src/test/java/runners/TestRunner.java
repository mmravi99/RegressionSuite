package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features/DerivedProduct2Validation.feature",
        glue = "steps"
)
public class TestRunner extends AbstractTestNGCucumberTests {


}
