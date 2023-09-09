package com.kuku.JUNIT5;

import com.kuku.Hooks.Hooks_Spartans;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class CSVFileSource_with_Names extends Hooks_Spartans {

    @ParameterizedTest
    @CsvFileSource(resources = "/data/names.csv",numLinesToSkip = 1)
    void getTheNamesFromCSVFile(String name,String gender,long phone){

        Map<String,Object> spartan = new HashMap<>();
        spartan.put("name",name);
        spartan.put("gender",gender);
        spartan.put("phone",phone);

        System.out.println("spartan = " + spartan);

        given().log().uri()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(spartan)
                    .post("/api/spartans").prettyPeek()
                .then().statusCode(201);
    }

}
