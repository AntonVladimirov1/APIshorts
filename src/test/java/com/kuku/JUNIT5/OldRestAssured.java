package com.kuku.JUNIT5;

import com.kuku.JUNIT5.Hooks.Hooks_NewSpartansAuth;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OldRestAssured extends Hooks_NewSpartansAuth {

    @Test
    public void allSpartans() {

        given().log().uri()
                    .accept(ContentType.JSON)
                    .auth().basic("admin","admin")
                .when()
                    .get("/spartans")
                .then()  // <---------------------------------Hard Assertion---
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("id[0]",is(1))
                    .body("id[1]",is(2));
    }

    @Test
    public void allSpartansOldWay() {

        given().accept(ContentType.JSON)
                    .auth().basic("admin","admin")
                .expect() // <----------------------------------Soft Assertion---
                    .statusCode(200).contentType(ContentType.JSON)
                    .body("id[0]",is(1))
                    .body("id[1]",is(2))
                .when()
                    .get("/spartans");

    }
}
