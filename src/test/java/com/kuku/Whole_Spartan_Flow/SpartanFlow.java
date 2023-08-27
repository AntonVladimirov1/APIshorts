package com.kuku.Whole_Spartan_Flow;

import com.kuku.POJO.Spartan;
import com.kuku.Utility.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanFlow extends Hooks_Spartans {

    static int id;

    @Test
    @Order(1)
    public void post_spartan_map(){

    //         POST      Create a Spartan Flow to run below testCases dynamically

        Map<String,Object> spartan = new LinkedHashMap<>();
        spartan.put("name","API Flow POST");
        spartan.put("gender","Male");
        spartan.put("phone",1231231231l);

        JsonPath jp = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan)
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        id = jp.getInt("data.id");
    }

    @Test
    @Order(2)
    public void get_spartan_with_id(){

    //             GET       Spartan with spartanID


        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body("name",is("API Flow POST"));
    }

    @Test
    @Order(3)
    public void put_spartan_with_id(){

    //           PUT       Spartan with spartanID

        Map<String,Object> spartan = new LinkedHashMap<>();
        spartan.put("name","API PUT Flow");
        spartan.put("gender","Female");
        spartan.put("phone",3213213213l);

        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(spartan)
                .pathParam("id",id)
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }


    @Test
    @Order(4)
    public void get_updatedSpartan_with_id(){

    //        GET        Spartan with spartanID

        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body("name",is("API PUT Flow"));
    }

    @Test
    @Order(5)
    public void delete_spartan_with_id(){

    //         DELETE       Spartan with spartanID

        given().pathParam("id",id)
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(6)
    public void get_deletedSpartan_with_id(){

     //        GET       Spartan with spartanID

        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(404);
    }

}
