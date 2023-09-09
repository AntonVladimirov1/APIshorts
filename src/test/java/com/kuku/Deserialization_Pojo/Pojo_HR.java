package com.kuku.Deserialization_Pojo;

import com.kuku.POJO.Employee_XXX;
import com.kuku.POJO.Regions;
import com.kuku.Hooks.Hooks_HR;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Pojo_HR extends Hooks_HR {

    @Test
    public void test1() {

        JsonPath jsonPath = get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        Regions region1 = jsonPath.getObject("items[0]", Regions.class);

        System.out.println("region1 = " + region1);
        System.out.println("region1.getRegion_id() = " + region1.getRegionId()); // reassigned variables in Regions class
        System.out.println("region1.getRegion_name() = " + region1.getRegionName()); // same
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
        System.out.println("region1.getLinks().get(0).getRel() = " + region1.getLinks().get(0).getRel());

    }

    @Test
    public void test2() {

        JsonPath jsonPath = given().log().uri()
                .get("/employees")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        Employee_XXX employee1 = jsonPath.getObject("items[1]", Employee_XXX.class);

        System.out.println("employee1 = " + employee1);

    }

    @Test
    public void test3() {

        JsonPath jsonPath = given().log().uri()
                .get("/regions")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        Regions region1 = jsonPath.getObject("", Regions.class);

        System.out.println("region1.getCount() = " + region1.getCount());
        //System.out.println("region1.getItems() = " + region1.getItems());


    }
}
