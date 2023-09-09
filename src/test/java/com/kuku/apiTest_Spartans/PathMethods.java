package com.kuku.apiTest_Spartans;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.kuku.Hooks.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PathMethods extends Hooks_Spartans {


    /*"id": 3,
    "name": "Fidole",
    "gender": "Male",
    "phone": 6105035231
     */
    @Test
    public void pathMethod(){

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
    }

    @Test
    public void getAllSpartans(){
        List<Integer> name;
        List<String> allIDs;

        Response response = get("api/spartans");
        //response.prettyPrint();

        System.out.println("first id: "+response.path("id[0]"));
        System.out.println("second name: "+response.path("name[1]"));
        System.out.println("last name: "+response.path("name[-1]"));

        name = response.path("name");
        System.out.println(name);

        allIDs = response.path("id");
        System.out.println(allIDs);

    }

}
