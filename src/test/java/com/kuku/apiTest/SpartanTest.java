package com.kuku.apiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanTest {

    String spartanBaseURL = "http://54.82.83.115:8000";

    @Test
    public void getAllSpartan(){

    Response response = RestAssured.get(spartanBaseURL + "/api/spartans");

        //status code
        int actStatusCode = response.statusCode();
        System.out.println("Status: "+actStatusCode);
        Assertions.assertEquals(200,actStatusCode);

        //content type
        String contentType = response.contentType();
        System.out.println("Content type: "+contentType);
        Assertions.assertEquals("application/json",contentType);

        //print body
        System.out.println("===========Response as String==========");
        System.out.println(response.asString());

        System.out.println("==========Response Pretty Print==============");
        response.prettyPrint();

    }


}
