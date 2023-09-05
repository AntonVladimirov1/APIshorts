package com.kuku.JUNIT5;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CSVFileSource_with_Locations {

    @ParameterizedTest
    @CsvFileSource(resources = "/data/locations.csv",numLinesToSkip = 1)

    public void assert_CSV_file_with_Response(String state,String city,int numberOfPlaces) {

       // int placesResponse =
                given().log().uri()
                .baseUri("https://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .when().get("/us/{state}/{city}")
                .then().statusCode(200)
                        .body("places",hasSize(numberOfPlaces)) // assertion (hamcrest)
                        .body("'state abbreviation'",equalTo(state))
                        .body("'place name'",equalTo(city));
        // .extract().jsonPath().getList("places").size();
        // System.out.println("placesResponse = " + placesResponse);

       // assertEquals(numberOfPlaces,placesResponse);

    }
}
