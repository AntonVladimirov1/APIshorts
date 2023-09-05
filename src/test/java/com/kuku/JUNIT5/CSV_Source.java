package com.kuku.JUNIT5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CSV_Source {

    @ParameterizedTest
    @CsvSource({"1,2,pop","2,3,oops","opa,kuku,zopa"})

    public void test1(String loca,String coca,String joka){
        System.out.println(loca+" "+coca+" "+joka);
        assertTrue(loca.length()<4);

    }

    @ParameterizedTest
    @CsvSource({"NY,New York","CO,Denver","VA,Fairfax","MA,Boston","MD,Annapolis"})

    public void test2(String state,String city){

        int placeCount = given().log().uri()
                .baseUri("https://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places.'place name'", everyItem(containsString(city)))
                .extract().jsonPath().getList("places").size();
        System.out.println("placeCount = " + placeCount);


    }

}
