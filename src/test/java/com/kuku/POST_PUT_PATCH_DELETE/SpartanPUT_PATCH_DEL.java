package com.kuku.POST_PUT_PATCH_DELETE;

import com.kuku.Utility.Hooks_Spartans;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanPUT_PATCH_DEL extends Hooks_Spartans {

    @DisplayName("PUT with Map")
    @Test
    public void test1() {

        // we need to provide the ID in order to update all records under this id
        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","Petya");
        requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone",1234567890);

        int id = 114;
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(requestBodyMap)
                .when()
                .put("/api/spartans/{id}")
                .then().statusCode(204);

    }

    @DisplayName("PATCH with Map")
    @Test
    public void test2() {

        // we need to provide the ID in order to update all records under this id
        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","Petya Petushok");

        int id = 114;
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(requestBodyMap)
                .when()
                .patch("/api/spartans/{id}")
                .then().statusCode(204);

    }

    @DisplayName("DELETE ")
    @Test
    public void test3() {

        // we need to provide the ID in order to Delete all records

        int id = 123;
        given()
                .pathParam("id",id)
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(204);

        //after deleted - same request needs to give 404 (id doesn't exist)
        given()
                .pathParam("id",id)
                .when()
                .get("/api/spartans/{id}")
                .then().statusCode(404);

    }

}
