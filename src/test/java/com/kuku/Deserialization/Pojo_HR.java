package com.kuku.Deserialization;

import com.kuku.POJO.HrRegions;
import com.kuku.Utility.Hooks_HR;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Pojo_HR extends Hooks_HR {

    @Test
    public void test1() {

        JsonPath jsonPath = get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();
        HrRegions region1 = jsonPath.getObject("items[0]", HrRegions.class);

        System.out.println("region1 = " + region1);


    }
}
