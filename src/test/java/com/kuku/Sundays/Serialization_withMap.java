package com.kuku.Sundays;

import com.kuku.Utility.Hooks_Fruit;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Serialization_withMap extends Hooks_Fruit {

    int id;
    @Test
    public void POST_fruit() {

        Map<String,Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name","Super Fruit2");
        requestBody.put("price",150.00);

        Response response = given().log().uri()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post("/products")
                .then().statusCode(201)
                .extract().response();
        response.prettyPrint();

        id = response.path("id");
        System.out.println("id = " + id);
    }
}
