package com.kuku.POST_PUT_PATCH_DELETE;

import com.kuku.POJO.Spartan;
import com.kuku.Utility.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SpartanPOST extends Hooks_Spartans {

    int id;

    @DisplayName("with String request body")
    @Test
    public void test1() {

        String requestBody = "{\n" +
                "    \"name\": \"Mashka\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1234567890\n" +
                "}";

        JsonPath jsonPath = given().log().uri()
                .accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/spartans").prettyPeek()             // putting here POST method
                .then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("Mashka",jsonPath.get("data.name"));
        assertEquals("Female",jsonPath.get("data.gender"));
        assertEquals(1234567890l,jsonPath.getLong("data.phone"));

        // when I want to get id out of the response body, to delete or send get request later on

        id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);

    }

    @DisplayName("with Map request body")
    @Test
    public void test2() {

        //creating one map and put info that we want to send as a JSON request body
        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","Boris");
        requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone",1234567890);

        JsonPath jsonPath = given().log().uri()
                .accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(requestBodyMap)
                .when()
                .post("/api/spartans").prettyPeek()             // putting here POST method
                .then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("Boris",jsonPath.get("data.name"));
        assertEquals("Male",jsonPath.get("data.gender"));
        assertEquals(1234567890l,jsonPath.getLong("data.phone"));

        // when I want to get id out of the response body, to delete or send get request later on

        id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);

    }

    @DisplayName("with Spartan POJO")
    @Test
    public void test3() {

        Spartan spartan = new Spartan();
        spartan.setName("Olga");
        spartan.setGender("Female");
        spartan.setPhone(9876543210l);

        System.out.println("spartan = " + spartan);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/api/spartans").prettyPeek()             // putting here POST method
                .then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("Olga",jsonPath.get("data.name"));
        assertEquals("Female",jsonPath.get("data.gender"));
        assertEquals(9876543210l,jsonPath.getLong("data.phone"));

        // when I want to get id out of the response body, to delete or send get request later on

       id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);

    }

    @DisplayName("with Spartan POJO and GET same Spartan")
    @Test
    public void test4() {

        Spartan spartanPOST = new Spartan();
        //empty spartanPOST object created, and we use setters to set some values
        spartanPOST.setName("Olga");
        spartanPOST.setGender("Female");
        spartanPOST.setPhone(9876543210l);

        System.out.println("spartan = " + spartanPOST);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(spartanPOST)
                .when()
                .post("/api/spartans").prettyPeek()             // putting here POST method
                .then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("Olga",jsonPath.get("data.name"));
        assertEquals("Female",jsonPath.get("data.gender"));
        assertEquals(9876543210l,jsonPath.getLong("data.phone"));

        // we want to get id out of the response body, to delete or send get request later on

        id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);
        System.out.println("==================================================================================");

        // Send GET request for created object ^^^
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();
        //empty spartanGET object created
        Spartan spartanGET = response.as(Spartan.class);
        System.out.println("spartanGET = " + spartanGET);

        assertEquals(spartanPOST.getName(),spartanGET.getName());
        assertEquals(spartanPOST.getGender(),spartanGET.getGender());
        assertEquals(spartanPOST.getPhone(),spartanGET.getPhone());

    }


}

