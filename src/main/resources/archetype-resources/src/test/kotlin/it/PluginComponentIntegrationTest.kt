package it

import org.junit.Test
import io.restassured.RestAssured

class PluginComponentIntegrationTest {

    @Test
    fun indexSuccess() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = ${httpPort}
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()

        RestAssured.given()
            .get()
            .then()
            .statusCode(200)
    }

}
