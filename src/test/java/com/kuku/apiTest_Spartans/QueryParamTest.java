package com.kuku.apiTest_Spartans;

import com.kuku.JUNIT5.Hooks.Hooks_Spartans;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class QueryParamTest extends Hooks_Spartans {

   /* Given accept type JSON
    And query param value are: gender|female, nameContains|J
    When user sends request to /api/spartans/search
    Then response status = 200
    And response cont type JSON
    And in response should be "Female", "Janette"
    */

    @Test
    public void queryParam(){
        int expStatusCode = 200;

        Response response = RestAssured.given().accept(ContentType.JSON).and().queryParam("nameContains", "J").and().queryParam("gender", "Female").when().get("/api/spartans/search");

        Assertions.assertEquals(expStatusCode,response.statusCode());
        System.out.println("status: "+response.statusCode());

        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());
        System.out.println("contType: "+response.contentType());

        Assertions.assertTrue(response.asString().contains("Female"));
        Assertions.assertTrue(response.asString().contains("Janette"));

        response.prettyPrint();

    }

    @Test
    public void queryParam2(){

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","J");

        int expStatusCode = 200;
        // api/spartans/search?gender=Female&nameContains=J --> this is what behind the scene
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");

        Assertions.assertEquals(expStatusCode,response.statusCode());
        System.out.println("status: "+response.statusCode());

        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());
        System.out.println("contType: "+response.contentType());

        Assertions.assertTrue(response.asString().contains("Female"));
        Assertions.assertTrue(response.asString().contains("Janette"));

        response.prettyPrint();

    }

}
