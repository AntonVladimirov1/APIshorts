package com.kuku.JUNIT5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {60,20,70,30,50})
    public void test1(int number){

        System.out.println("number = " + number);
        assertTrue(number<40);
    }

    @ParameterizedTest(name = "{index} name is {0}")
    @ValueSource(strings = {"mike","rose","anton","maria"})
    public void test2(String name){
        System.out.println("name = " + name);
        assertTrue(name.length()>4);
    }

    @ParameterizedTest(name = "Zip {0} Status code (200)")
    @ValueSource(ints = {77377,77450,55450,22031,22032,22033})
    public void test3(int zipcode){

        Response response = given().log().uri()
                //.baseUri("https://api.zippopotam.us") // we can use this method for BaseURI
                .accept(ContentType.JSON)
                .pathParam("dick", zipcode)
                .when().get("https://api.zippopotam.us/us/{dick}")
                .then().statusCode(200)
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());

    }

}
