package com.kuku.Sundays;

import com.kuku.Utility.BookItUtils1;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Auth_withBearerToken extends BookItUtils1 {

    @Test
    public void test() {

        given().accept(ContentType.JSON)
                .when()
                .header("Authorization",token)
                .get("/api/campuses").prettyPeek()
                .then()
                .statusCode(200);

    }
}
