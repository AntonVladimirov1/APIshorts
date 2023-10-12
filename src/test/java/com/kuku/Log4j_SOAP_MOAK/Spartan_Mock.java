package com.kuku.Log4j_SOAP_MOAK;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Spartan_Mock {

    @BeforeAll
    public static void init(){
        baseURI="https://e1663fb3-2144-4d53-9299-3a16bef24dc7.mock.pstmn.io";
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.TEXT)
                .log().all()
                .when()
                .get("/api/hello")
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals("Hello from Sparta",response.asString());
        response.prettyPeek();
    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200).contentType(ContentType.JSON)
                .extract().response();

        response.prettyPeek();
    }
}
