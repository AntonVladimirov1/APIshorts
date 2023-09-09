package com.kuku.JUNIT5;

import com.kuku.Hooks.Hooks_Spartans;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Request_Spec_method extends Hooks_Spartans {


    public static RequestSpecification getRequestSpec(){

        RequestSpecification requestSpecification = given()
                .log().uri()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
        return requestSpecification;
    }

    @Test
    public void getSingleSpartan() {

                getRequestSpec().get("/api/spartans/2").prettyPeek();
    }

    @Test
    public void getAllSpartan() {

                getRequestSpec().get("/api/spartans");
    }
}
