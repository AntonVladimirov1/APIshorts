package com.kuku.HR_Data;

import com.kuku.Utility.Hooks_HR;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HRwithResponsePath extends Hooks_HR {

    @DisplayName("GET/request to countries using Response path")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        // we gonna print (limit, hasMore, second country name, 4th element country name, 3th element href, all countries name)

        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));
        System.out.println("response.path(\"count\") = " + response.path("count"));

    }
}
