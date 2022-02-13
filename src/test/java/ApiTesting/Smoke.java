package ApiTesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Smoke {
    @DisplayName("Testcase for main functionality and app reliability over multiple consecutive requests")
    @RepeatedTest(10)
    public void verifyValidRequest() {
        given().when().get("input").then().statusCode(200)
                .body(equalTo("npt"));}
}
