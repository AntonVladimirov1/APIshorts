package com.kuku.JUNIT5;

import com.kuku.JUNIT5.Hooks.Hooks_NewSpartansAuth;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Spec_Test extends Hooks_NewSpartansAuth {

    @Test
    public void getAllSpartans() {

        given().log().all()
                    .accept(ContentType.JSON)
                    .auth().basic("admin","admin")
                .when()
                    .get("/spartans")
                .then()  // <---------------------------------Hard Assertion---
                    .statusCode(200)
                    .contentType(ContentType.JSON);
    }

    @Test
    public void SpecAllSpartans() {

        given().spec(reqAdminSpec)
                .when()
                    .get("spartans")
                .then()
                    .spec(resSpec);
    }

    @Test
    public void getAsAdmin() {

        given()
                .spec(reqAdminSpec)  //<------------------------- Admin request specification
                .and().pathParam("id",10)
        .when()
                .get("/spartans/{id}").prettyPeek()
        .then()
                .spec(resSpec)
                .body("id",is(10));
    }

    @Test
    public void getAsUser() {

        given()
                .spec(reqUserSpec)  //<------------------------- User request specification
                .and().pathParam("id",10)
                .when()
                .get("/spartans/{id}").prettyPeek()
                .then()
                .spec(resSpec)
                .body("id",is(10));
    }

    @Test
    public void getDynamicSpec() {

        given()
                .spec(dynamicReqSpec("user","user"))  //<-------- Dynamic method Specification (user or admin or ....)
                .and().pathParam("id",10)
                .when()
                .get("/spartans/{id}").prettyPeek()
                .then()
                .spec(dynamResSpec(200))
                .body("id",is(10));
    }
}
