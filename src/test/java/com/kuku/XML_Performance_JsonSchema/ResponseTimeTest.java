package com.kuku.XML_Performance_JsonSchema;

import com.kuku.Utility.Hooks_SpartanAuth;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ResponseTimeTest extends Hooks_SpartanAuth {

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200).contentType(ContentType.JSON)
                //.time(lessThan(800L)) //  this method storing actual response time
                //.time(greaterThan(100L))
                .time(both(greaterThan(100L)).and(lessThan(800L))) // both in one
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());
        System.out.println("response.getTimeIn(TimeUnit.MICROSECONDS) = " + response.getTimeIn(TimeUnit.MICROSECONDS));

    }
}
