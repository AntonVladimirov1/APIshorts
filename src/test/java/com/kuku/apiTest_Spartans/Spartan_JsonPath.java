package com.kuku.apiTest_Spartans;

import com.kuku.Utility.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class Spartan_JsonPath extends Hooks_Spartans {

    @Test
    public void jsonPathMethod(){

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 3).when().get("/api/spartans/{id}");
        //response.prettyPrint();

        //same as 200 vvv
        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());
        System.out.println(response.statusCode() + " + " + response.contentType());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println(id);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);

        assertEquals(3,id);
        assertEquals("Fidole",name);
        assertEquals("Male",gender);
        assertEquals(6105035231l,phone);

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("========================================================");

           // Json path
        JsonPath jsonPath = response.jsonPath();

        int id2 = jsonPath.getInt("id");
        Object name2 = jsonPath.get("name");
        Object gender2 = jsonPath.get("gender");
        Object phone2 = jsonPath.get("phone");

        System.out.println(id2);
        System.out.println(name2);
        System.out.println(gender2);
        System.out.println(phone2);

        // Same assertion

    }
}
