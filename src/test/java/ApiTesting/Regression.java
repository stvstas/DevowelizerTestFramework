package ApiTesting;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static Utils.ApiConstants.BASE_URL;
import static Utils.ApiConstants.PORT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Regression {
    @BeforeAll
    public static void beforeConfig() {
      RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @DisplayName("Checking vowel filtering functionality")
    @ParameterizedTest
    @CsvFileSource(resources = "/regressionData.csv", numLinesToSkip = 1)
    public void verifyNoVowelsResponse(String input, String expected, String responseCode) {
        RestAssured
                .given()
                .when()
                .get(BASE_URL + PORT + "/" + input)
                .then()
                .statusCode(Integer.parseInt(responseCode))
                .and()
                .body(equalTo(expected));
    }

    @DisplayName("Checking endpoint path-N")
    @Test
    public void verifyPath() {
        RestAssured
                .given()
                .when()
                .get(BASE_URL + PORT + "/input/input")
                .then()
                .statusCode(404);
    }

    @DisplayName("Checking endpoint method-Post")
    @Test
    public void verifyEndpointMethodPost() {
        RestAssured
                .given()
                .when()
                .post(BASE_URL + PORT + "/input")
                .then()
                .statusCode(415);
    }

    @DisplayName("Checking endpoint method-Delete")
    @Test
    public void verifyEndpointMethodDelete(){
        RestAssured
                .given()
                .when()
                .delete(BASE_URL + PORT + "/input")
                .then()
                .statusCode(404);
    }

    @DisplayName("Checking endpoint method-Put")
    @Test
    public void verifyEndpointMethodPut(){
        RestAssured
                .given()
                .when()
                .put(BASE_URL + PORT + "/input")
                .then()
                .statusCode(404);
    }

    @DisplayName("Checking any headers ")
    @Test
    public void verifyMsgTypeHeader() {
        given()
                .accept("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36")
                .when()
                .get(BASE_URL + PORT + "/input")
                .then()
                .statusCode(200);
    }
}
