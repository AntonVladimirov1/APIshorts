package com.kuku.JUNIT5;

import com.kuku.Utility.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Request_Spec extends Hooks_Spartans {

    RequestSpecification requestSpecification;

    @BeforeEach
    public void setUp(){
        requestSpecification = given()
                .log().uri()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }

    @Test
    public void test() {
        requestSpecification.get("/api/spartans/2").prettyPeek()
                .then()
                .statusCode(200);
    }
}
