package com.kuku.Log4j_SOAP_MOAK;

import com.kuku.Hooks.Hooks_Spartans;
import com.kuku.POJO.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class SpartanFlow_logging extends Hooks_Spartans {  // logging object in Hooks_Spartans

    static int spartanID;
    static Spartan spartanPost ;
    static Spartan spartanPut ;

    @Order(1)
    @Test
    public void POST() {

        spartanPost = new Spartan();
        spartanPost.setName("API POST Flow");
        spartanPost.setGender("Male");
        spartanPost.setPhone(8877445596l);

        log.info("POST SPARTAN ---> "+ spartanPost);

        spartanID = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanPost).
                when().post("/api/spartans").
                then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!")).extract().jsonPath().getInt("data.id");

        //System.out.println(spartanID + " is created");

        log.info(spartanID + " is created");
    }

    @Order(2)
    @Test
    public void GETSpartan_01() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", spartanID).
                when()
                .get("/api/spartans/{id}").
                then()
                .statusCode(200)
                .body("name", is(spartanPost.getName())).extract().response();

        log.info("GET SPARTAN --> "+response.asString());
    }

    @Order(3)
    @Test
    public void PUT() {

        spartanPut = new Spartan();
        spartanPut.setName("API PUT Flow");
        spartanPut.setGender("Male");
        spartanPut.setPhone(8877445596l);

        log.info("PUT SPARTAN --> "+spartanPut);

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", spartanID)
                .body(spartanPut).
                when().put("/api/spartans/{id}").
                then()
                .statusCode(204);

        //System.out.println(spartanID + " is updated");

        log.info(spartanID + " is updated");
    }

    @Order(4)
    @Test
    public void GETSpartan_02() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", spartanID).
                when()
                .get("/api/spartans/{id}").
                then()
                .statusCode(200)
                .body("name", is(spartanPut.getName())).extract().response();

        log.info("GET SPARTAN --> "+response.asString());
    }

    @Order(5)
    @Test
    public void DELETE() {

        given()
                .pathParam("id", spartanID)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        System.out.println(spartanID + " is deleted");

        log.info(spartanID + " is deleted");
    }

    @Order(6)
    @Test
    public void GETSpartan() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", spartanID).
                when()
                .get("/api/spartans/{id}").
                then()
                .statusCode(404).extract().response();

        System.out.println(spartanID + " is not exist");

        log.info("GET SPARTAN is NOT FOUND --> "+response.asString());
        log.info(spartanID + " is not exist");
    }

}
