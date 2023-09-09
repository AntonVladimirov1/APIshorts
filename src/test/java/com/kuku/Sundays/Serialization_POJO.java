package com.kuku.Sundays;

import com.kuku.Hooks.Hooks_Fruit;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Serialization_POJO extends Hooks_Fruit {

    int id;
    @Test
    public void POST_fruit() {

        FruitPOJO requestBody = new FruitPOJO("Super Duper",25.00);

        Response response = given().log().uri()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post("/products")
                .then().statusCode(201)
                .extract().response();
        response.prettyPrint();

        System.out.println("==============================================");
        id = response.path("id");
        System.out.println("id = " + id);
    }

}
