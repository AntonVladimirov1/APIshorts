package com.kuku.HamCrest_Matchers;

import com.kuku.Hooks.Hooks_HR;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamCrestHR extends Hooks_HR {

    @Test
    public void test1() {

        List<String> names = new ArrayList<>(Arrays.asList("Alexander","Bruce","David","Valli","Diana"));

        given().log().uri().accept(ContentType.JSON).queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("items.manager_id",everyItem(notNullValue()))
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .body("items.salary",everyItem(greaterThan(3000)))
                .body("items.first_name",equalTo(names)) // <- new ArrayList
                .body("items.email",containsInAnyOrder("DLORENTZ","VPATABAL","DAUSTIN","BERNST","AHUNOLD"));

    }

    @Test
    public void test2() {

        JsonPath jsonPath = given().log().uri().accept(ContentType.JSON)
                .when()
                .get("/regions")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .contentType("application/json")
                .header("Date", notNullValue())
                .body("items[0].region_name", equalTo("Europe"))
                .body("items[0].region_id", is(1))
                .body("items", hasSize(4))
                .body("items.region_name", everyItem(notNullValue()))
                .body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"))
                .body("items.region_id", containsInRelativeOrder(1, 2, 3, 4))
                .extract().jsonPath();

        List<String> regionNames = jsonPath.get("items.region_name");
        System.out.println("allRegionNames = " + regionNames);

        Assertions.assertEquals(1,jsonPath.getInt("items[0].region_id"));
        System.out.println("items[0].region_id = " + jsonPath.getInt("items[0].region_id"));


    }
}
