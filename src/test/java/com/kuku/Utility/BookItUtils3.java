package com.kuku.Utility;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookItUtils3 {

    public static String getToken(String userType){

        String accessToken = given()
                .queryParams(credentialsByUserType(userType))
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().response().path("accessToken");

        return  accessToken;
    }

    public static Map<String,String> credentialsByUserType(String userType){

        userType = userType.replace(" ","_");

        String email = ConfigurationReader.getProperty(userType+ "_email");
        String password = ConfigurationReader.getProperty(userType+ "_password");

       /* String email = "";
        String password = "";

        switch (role) {
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

        */

        Map<String,String> credentials = new HashMap<>();
        credentials.put("email",email);
        credentials.put("password",password);

        return credentials;
    }

}
