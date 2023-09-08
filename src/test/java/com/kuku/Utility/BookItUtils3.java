package com.kuku.Utility;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookItUtils3 {

   /* public static String getToken(String userType){

        String accessToken = given()
                .queryParams(credByUserType(userType))
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().response().path("accessToken");

        return  accessToken;
    }

    */

    public static String getTokenByUserType(String userType){

        userType = userType.replace(" ","_");

        String email = ConfigurationReader.getProperty(userType+ "_email");
        String password = ConfigurationReader.getProperty(userType+ "_password");

       /* String email = "";
        String password = "";

        switch (userType) {
            case "teacher":
                email = Environment.TEACHER_EMAIL;
                password = Environment.TEACHER_PASSWORD;
                break;

            case "team-member":
                email = Environment.MEMBER_EMAIL;
                password = Environment.MEMBER_PASSWORD;
                break;
            case "team-leader":
                email = Environment.LEADER_EMAIL;
                password = Environment.LEADER_PASSWORD;
                break;
            default:
            throw new RuntimeException("Invalid userType Entry :\n>> " + userType+ " <<");

        */

        Map<String,String> credentials = new HashMap<>();
        credentials.put("email",email);
        credentials.put("password",password);

        String accessToken = given()
                .queryParams(credentials)
                .when()
                .get("/sign").path("accessToken");

        return  accessToken;
    }

}
