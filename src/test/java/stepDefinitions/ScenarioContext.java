package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ScenarioContext {

    public static Scenario getScenarioContext;
    @Before
    public void initializeScenarioContext(final Scenario scenario){
        getScenarioContext = scenario;
    }
}
