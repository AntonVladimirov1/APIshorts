package com.kuku.apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PathParamTest {

   /* Given accept type is JSON
    And ID parameter value is 3
    When user sends GET request /api/spartans/{id}
    Then response status code should be 200
    And response content type is application/json
    */

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://54.82.83.115:8000";
    }

    @Test
    public void PathParam() {

        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParam("id", 3).when().get("api/spartans/{id}");
            response.prettyPrint();

        //status code should be 200
        //int statusCode = response.statusCode(); we can declare a variable, but not necessary
        Assertions.assertEquals(200,response.statusCode());
            System.out.println("Status code: "+ response.statusCode());

        //response content type is application/json
        //Assertions.assertEquals("application/json",response.contentType()); - can do like this, but it hard coded
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());
            System.out.println("Content type: "+ response.contentType());

                // can also get any Header value in response
        System.out.println(response.header("Connection"));

                // verify - all existing Headers in response
        List<Header> headers = response.headers().asList();
        System.out.println(headers);

        // verify whats in Body
        System.out.println(response.body().asString().contains("Fidole"));

    }
}
