package com.kuku.Deserialization_Pojo;

import com.kuku.JUNIT5.Hooks.Hooks_HR;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class HRDeserialization extends Hooks_HR {

    @Test
    public void test1() {

        Response response = given().log().uri().accept(ContentType.JSON)
                .when()
                .get("/locations")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Map<String,Object>> allLinks = jsonPath.getList("items.links"); // all links of all locations
        System.out.println("allLinks = " + allLinks);
        System.out.println("=================================================================================");

        Map<String,Object> firstLocation = jsonPath.getMap("items[0]"); // info of first location only
        System.out.println("firstLocation = " + firstLocation);
        System.out.println("=================================================================================");

        List<Map<String,Object>> allLocations = jsonPath.getList("items"); // all info of all locations
        for (Map<String, Object> locations : allLocations) {
            System.out.println("locations = " + locations);
        }
        System.out.println("=================================================================================");

        List<Map<String,Object>> links = (List<Map<String, Object>>) allLocations.get(0).get("links");
        System.out.println("links = " + links);
        System.out.println("links.get(0).get(\"href\") = " + links.get(0).get("href"));
        System.out.println("=================================================================================");



    }
}
