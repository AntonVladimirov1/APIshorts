package com.kuku.JUNIT5;

import com.kuku.Hooks.Hooks_BookIt;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Spec_Bookit_Test extends Hooks_BookIt {

    @Test
    public void test1() {

        given()
                .spec(dynamicReqSpec("teacher"))
                //.log().all()
                //.accept(ContentType.JSON)
                //.auth().oauth2(BookItUtils3.getTokenByUserType("teacher"))
            .when()
                .get("/api/users/me").prettyPeek()
            .then()
                //.statusCode(200).contentType(ContentType.JSON);
                .spec(dynamResSpec(200));
    }
}
