package com.kuku.apiTest;

import com.kuku.Utility.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonPath extends Hooks_Spartans {

    io.restassured.path.json.JsonPath jsonPath;

    /*"id": 3,
    "name": "Fidole",
    "gender": "Male",
    "phone": 6105035231
     */

    @Test
    public void getSingleSpartan() {

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 3).when().get("/api/spartans/{id}");

        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        jsonPath = response.jsonPath();
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println(id);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);

        assertEquals(3, id);
        assertEquals("Fidole", name);
        assertEquals("Male", gender);
        assertEquals(6105035231l, phone);
    }
    @DisplayName("JsonPath - getting all Spartans")
    @Test
    public void getAllSpartans(){

        Response response = get("/api/spartans");
       // response.prettyPrint();

        jsonPath = response.jsonPath();

        Object firstID = jsonPath.get("id[0]");
        Object secondName = jsonPath.get("name[1]");
        Object lastName = jsonPath.get("name[-1]");
        List<Object> allNames = jsonPath.getList("name");
        List<Object> allIDs = jsonPath.getList("id");

        System.out.println(firstID);
        System.out.println(secondName);
        System.out.println(lastName);
        System.out.println("all names:\n" +allNames);
        System.out.println("all IDs:\n" +allIDs);

        // can also get any Header value in response
        System.out.println(response.header("Connection"));

        // verify - all existing Headers in response
        List<Header> headers = response.headers().asList();
        System.out.println(headers);

        // verify whats in Body
        System.out.println(response.body().asString().contains("Fidole"));


    }

}
