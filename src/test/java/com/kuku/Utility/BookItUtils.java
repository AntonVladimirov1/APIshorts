package com.kuku.Utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class BookItUtils {

    protected static String token;

    @BeforeAll
    public static void init_getToken(){

        baseURI= "https://api.qa.bookit.cydeo.com";

        String email="lfinnisz@yolasite.com";
        String password="lissiefinnis";

        token = given()
                .queryParam("email",email)
                .queryParam("password",password)
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

}
