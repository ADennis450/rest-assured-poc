package stepDefinitions;

import helpers.RequestHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;

public class MyStepdefs {
    @Given("I am using the {string}")
    public void iAmUsingThe(String apiBaseUri) throws IOException {
        RequestHelper.setApiRequest(apiBaseUri);
    }

    @And("I send a GET request to the endpoint {string}")
    public void iSendAGETRequestToTheEndpoint(String endpoint) {
        Response apiResponse = RequestHelper.getApiRequest().when().get(endpoint);
        RequestHelper.setApiResponse(apiResponse);
    }

    @Then("the response code is {int}")
    public void theResponseCodeIs(int responseCode) {
        RequestHelper.getApiResponse().then().statusCode(responseCode);
    }

    @And("the {string} property on the response object is {string}")
    public void thePropertyOnTheResponseObjectIs(String property, String expectedValue) {
        try {
            RequestHelper.getApiResponse().then().body(property, equalTo(expectedValue)).log();
            ScenarioContext.getScenarioContext.log(RequestHelper.getApiResponse().then().extract().response().body().prettyPrint());
        } catch (AssertionError error) {
            throw new AssertionError(error.getMessage());
        }
    }

    @Then("the response matches the {string} schema")
    public void theResponseMatchesTheSchema(String schemaName) {
        try {
            File schemaFile = new File(
                    Paths.get(System.getProperty("user.dir"), "src/test/resources/schemas/", schemaName + ".json"
                    ).toString());
            RequestHelper.getApiResponse().then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
        } catch (AssertionError error) {
            throw new AssertionError(error.getMessage());
        }
    }
}
