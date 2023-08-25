package com.kuku.CydeoDeserialPOJO;

import com.kuku.Utility.Hooks_Cydeo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CydeoTrainingPojo extends Hooks_Cydeo {

    @Test
    public void test1() {

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("id",2)
                .when().get("/students/{id}");
                assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        assertEquals("Mark",jsonPath.getString("firstName"));
        assertEquals(13, jsonPath.getInt("batch"));
        assertEquals("mark@email.com",jsonPath.getString("contact.emailAddress"));
        assertEquals("https://api.training.cydeo.com/students/2", jsonPath.getString("_links.self.href"));
    }
}
