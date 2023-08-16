package com.kuku.apiTest_HR_Data;

import com.kuku.Utility.Hooks_HR;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRwithParameters extends Hooks_HR {


    @DisplayName("Get/countries with region_id ")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));

    }
}
