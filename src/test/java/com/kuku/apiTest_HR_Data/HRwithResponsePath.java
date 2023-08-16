package com.kuku.apiTest_HR_Data;

import com.kuku.Utility.Hooks_HR;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class HRwithResponsePath extends Hooks_HR {

    @DisplayName("GET/request to countries using Response path")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        // we gonna print (limit, hasMore, second country name, 4th element country name, 3th element href, all countries name, all region_ID's equals to 2)

        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));
        System.out.println("response.path(\"count\") = " + response.path("count"));
        System.out.println("response.path(\"items[4].country_name\") = " + response.path("items[4].country_name"));
        System.out.println("response.path(\"items[2].href\") = " + response.path("items[2].links[0].href"));
        Object allCountryNames = response.path("items.country_name");
        System.out.println("allCountryNames - "+allCountryNames);
       // Object allRegionsIDs = response.path("items.region_id");
        List<Object> allRegionsIDs = response.path("items.region_id");
        for (Object id : allRegionsIDs) {
            assertEquals(2,id);
            System.out.println(id);
        }

        System.out.println(allRegionsIDs);
        Object actRegionID = response.path("items[0].region_id");
        System.out.println("actRegionID: "+actRegionID);
        assertEquals(2,actRegionID);


    }
}
