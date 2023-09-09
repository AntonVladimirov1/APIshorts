package com.kuku.Deserialization_Pojo;

import com.kuku.JUNIT5.Hooks.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class DeserializationToCollection extends Hooks_Spartans {

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).pathParam("id", 10)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        // Solution 1 --> convert json object to Map and custed every item
        Map<String,Object> spartanMap = response.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);

        int id = (int) spartanMap.get("id");
        String name = (String) spartanMap.get("name");
        System.out.println("id = " + id);
        System.out.println("name = " + name);

        // Solution 2 --> JsonPath
        JsonPath jsonPath = response.jsonPath();
        Map<String,Object> jsonPathMap = jsonPath.getMap("");
        System.out.println("jsonPathMap = " + jsonPathMap);

        String id1 = (String) jsonPathMap.get("Id");
        System.out.println("id1 = " + id1);

    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans/")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        //* Approach 1 --> With response object

        List<Map<String,Object>> spartanList = response.as(List.class);

        //System.out.println("spartanList = " + spartanList);

      // for (Map<String, Object> map : spartanList) {  // iter - for better performance
          //  System.out.println("map = " + map);
      //  }

       // how to find just any spartan info
        System.out.println("spartanList.get(0) = " + spartanList.get(0));

        // how to find specific spartan info (any key info)
        System.out.println("spartanList.get(1).get(\"name\") = " + spartanList.get(1).get("name"));

        //* Approach 2 --> JsonPath  ( I like this the most!!! )

        JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> listSpartans = jsonPath.get("");
        for (Map<String, Object> listSpartan : listSpartans) {
            System.out.println("listSpartans = " + listSpartan);
        }
        System.out.println("listSpartans = " + listSpartans);

    }
}
