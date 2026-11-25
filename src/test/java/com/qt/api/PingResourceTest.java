package com.qt.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import io.quarkus.test.junit.QuarkusTest;
import java.time.Instant;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PingResourceTest {

    @Test
    void respondsOkWithIsoTimestamp() {
        String timestamp = given()
                .when().get("/ping")
                .then()
                .statusCode(200)
                .body("status", equalTo("ok"))
                .body("service", equalTo("qt-auth-service"))
                .body("version", notNullValue())
                .extract().path("timestamp");
        assertDoesNotThrow(() -> Instant.parse(timestamp));
    }
}
