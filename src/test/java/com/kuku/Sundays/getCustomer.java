package com.kuku.Sundays;

import com.kuku.JUNIT5.Hooks.Hooks_Fruit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class getCustomer extends Hooks_Fruit {

    @Test
    public void test() {

        Response response = given().log().uri().accept(ContentType.JSON)
                .when()
                .get("/customers")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Map<String, Object> allData = jsonPath.getMap("");
        System.out.println("allData = " + allData);
        System.out.println("======================================================================");

        Map<String,Object> meta = (Map<String, Object>) allData.get("meta");
        System.out.println("meta = " + meta);
        meta.get("limit");
        System.out.println("======================================================================");

       List<Map<String,Object>> allCustomers = (List<Map<String, Object>>) allData.get("customers");
        System.out.println("allCustomers = " + allCustomers);
        System.out.println("allCustomers.get(0) = " + allCustomers.get(0));

        System.out.println("allCustomers.get(0).get(\"id\") = " + allCustomers.get(0).get("id"));
        assertEquals(6,allCustomers.get(0).get("id"));
        List<Object> ids = allCustomers.stream().map(eachCustomer -> eachCustomer.get("id")).collect(Collectors.toList());
        System.out.println("ids = " + ids);

    }

    @Test
    public void test2() {

        Response response = given().log().uri().accept(ContentType.JSON)
                .when()
                .get("/customers")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();


    }
}
