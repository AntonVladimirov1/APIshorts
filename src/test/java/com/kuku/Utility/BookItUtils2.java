package com.kuku.Utility;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class BookItUtils2 {

    public static String getToken(String email,String password){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        String accessToken = jsonPath.getString("accessToken");

        return  accessToken;
    }


}
