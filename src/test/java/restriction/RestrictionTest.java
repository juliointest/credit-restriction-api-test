package restriction;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestrictionTest {
    @Test
    public void testCPFWithRestrictionShouldBeAlerted() {
        baseURI = "http://localhost";
        port = 8088;
        basePath = "/api";

        given().
            pathParam("cpf", "97093236014").
        when().
            get("/v1/restrictions/{cpf}").
        then().
            statusCode(HttpStatus.SC_OK)
            .body("message", is("CPF 97093236014 has a restriction"));
    }

    @Test
    public void testInexistentCPFShouldNotBeFound() {
        baseURI = "http://localhost";
        port = 8088;
        basePath = "/api";

        given().
            pathParam("cpf", "85893827200").
        when().
            get("/v1/restrictions/{cpf}").
        then().
            statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
