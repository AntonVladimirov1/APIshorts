package com.kuku.Deserialization;

import com.kuku.POJO.Spartan;
import com.kuku.POJO.SpartanSearch;
import com.kuku.Utility.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Pojo_Spartans extends Hooks_Spartans {

    @Test
    public void test1() {

        Response response = given().log().uri()
                .accept(ContentType.JSON).pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("=====================RESPONSE=======================");

        Spartan spartan = response.as(Spartan.class);

        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());

        System.out.println("=====================JsonPath=======================");

        JsonPath jsonPath = response.jsonPath();

        Spartan spartanJP = jsonPath.getObject("", Spartan.class);

        System.out.println("spartanJP.getId() = " + spartanJP.getId());
        System.out.println("spartanJP.getName() = " + spartanJP.getName());
        System.out.println("spartanJP.getGender() = " + spartanJP.getGender());
        System.out.println("spartanJP.getPhone() = " + spartanJP.getPhone());

    }

    @Test
    public void test2() {

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Spartan spartJP = jsonPath.getObject("content[5]", Spartan.class);
        System.out.println("spartJP = " + spartJP);
    }

    @Test
    public void test3() {   // POJO

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();

        Spartan search = response.as(Spartan.class); //* <------

        System.out.println("search.getTotalElement() = " + search.getTotalElement());
        System.out.println("search.getContent().get(2) = " + search.getContent().get(2));
        System.out.println("search.getContent().get(2).getName() = " + search.getContent().get(2).getName());
        System.out.println("search.getContent().get(2).getPhone() = " + search.getContent().get(2).getPhone());
        //System.out.println("search.getContent() = " + search.getContent()); // all spartans
        assertEquals("Fidole",search.getContent().get(2).getName());

        List<Spartan> content = search.getContent();
        for (Spartan eachSpartan : content) {
            System.out.println("eachSpartan = " + eachSpartan);
        }

    }

    @Test
    public void test4() { // JsonPath

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath(); //* <-------

        List<Spartan> content = jsonPath.getList("content", Spartan.class);

        for (Spartan allSpartan : content) {
            System.out.println("allSpartan = " + allSpartan);
        }

        System.out.println("content.get(1).getId() = " + content.get(1).getId());


    }


}
