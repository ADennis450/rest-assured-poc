package helpers;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class RequestHelper {
    private static RequestSpecification request;
    private static Response apiResponse;

    private static String getApiBaseUrl(String apiName) throws IOException {
        String formattedApiName = apiName.replaceAll("\\s", "").toLowerCase();
        JSONObject baseUrls = new JSONObject(
                new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir"), "/src/test/resources/ApiBaseUrls.json")))
        );
        return (String) baseUrls.get(formattedApiName);
    }

    //region Get & Set ApiRequest
    public static void setApiRequest(String apiName) throws IOException {
        String apiBaseUri = getApiBaseUrl(apiName);
        request = given()
                .baseUri(apiBaseUri);
    }

    public static RequestSpecification getApiRequest() {
        return request;
    }
    //endregion

    //region Get & Set ApiResponse
    public static void setApiResponse(Response response) {
        apiResponse = response;
    }

    public static Response getApiResponse() {
        return apiResponse;
    }
    //endregion
}
