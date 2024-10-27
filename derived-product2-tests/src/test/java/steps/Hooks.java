package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.BrowserFactory;
import utilities.ExtentManager;

public class Hooks {
    @Before
    public void setup(Scenario scenario){
        ExtentManager.extent = ExtentManager.getInstance(BrowserFactory.getBrowserType(), BrowserFactory.getBaseUrl(),BrowserFactory.getApplication());
        String scenarioName=scenario.getName();

        ExtentManager.createTest(scenario.getName(),"");
    }

    @After
    public void tearDown(){
        ExtentManager.extent.flush();
    }
}
