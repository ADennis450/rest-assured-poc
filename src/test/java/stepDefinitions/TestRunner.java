package stepDefinitions;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"stepDefinitions"},
        plugin = {"pretty", "me.jvt.cucumber.report.PrettyReports:target/cucumber-reports/",
                "json:target/cucumber-reports/Cucumber.json"}
)

public class TestRunner {

}

