package com.kuku.XML_Performance_JsonSchema;

import com.kuku.JUNIT5.Hooks.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidation extends Hooks_Spartans {

    @Test
    public void test1() {

        given().accept(ContentType.JSON)
                .pathParam("id",20)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/SingleSpartanSchema.json"));
                 //.body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SingleSpartanSchema.json"))); // another filePath

    }
}
