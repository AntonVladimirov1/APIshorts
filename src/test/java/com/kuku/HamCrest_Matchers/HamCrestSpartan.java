package com.kuku.HamCrest_Matchers;

import com.kuku.Utility.Hooks_Spartans;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamCrestSpartan extends Hooks_Spartans {

    @Test
    public void test1() {

        given().accept(ContentType.JSON).pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(is(200))
                .contentType("application/json")
                .body("id",is(15),"name",equalToIgnoringCase("meta"),"gender",is("Female"));

    }

    @Test
    public void test2() {

        given().accept(ContentType.JSON).pathParam("id",15)
                .when()
                    .get("/api/spartans/{id}")
                .then()
                    .assertThat() // --> its just syntactic sugar(not necessary)
                        .statusCode(200)
                        .contentType("application/json")
                        .body("id",is(15),"name",is("Meta"),"gender",equalToIgnoringCase("female"),"phone",is(1938695106));

    }

    @Test
    public void test3() {

      Response response = given().accept(ContentType.JSON).pathParam("id",15)
                //.log().all() // Request logs
                .when()
                .get("/api/spartans/{id}").prettyPeek()// printing Response object
                .then()
                //.log().all() // Response logs
                .log().ifValidationFails() //; log().ifError ; log().ifStatusCodeIsEqualTo

                .statusCode(200)
                .contentType("application/json")
                .body("id",is(15),
                        "name",is("Meta"),
                        "gender",equalToIgnoringCase("female"),
                        "phone",is(1938695106))
                .extract().response();

        Object id = response.path("id");
        System.out.println("id = " + id);


    }

    @Test
    public void test4() {

        JsonPath jsonPath = given().accept(ContentType.JSON).pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is(15),
                        "name", is("Meta"),
                        "gender", equalToIgnoringCase("female"),
                        "phone", is(1938695106))
                .extract().jsonPath();

        // api actual data (id,name)
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        System.out.println("actID = " + id);
        System.out.println("actName = " + name);

        // assume expected data from DB (using DB_Utils, saving variables)
        int expIDdb = 15;
        String expNameDb = "Meta";

        // compare api vs DB
        assertThat(id,is(expIDdb));
        assertThat(name,is(expNameDb));


    }
}
