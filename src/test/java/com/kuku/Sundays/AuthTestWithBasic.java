package com.kuku.Sundays;

import com.kuku.Hooks.Hooks_SpartanAuth;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class AuthTestWithBasic extends Hooks_SpartanAuth {

    @Test
    public void positive_basicAuth() {

        given().log().uri()
                .auth().basic("user","user")
                .when().get("api/spartans")
                .then().statusCode(200);

    }

    @Test
    public void negative_basicAuth() {

        given().log().uri()
                .auth().basic("use","use") // incorrect cred.
                .when().get("api/spartans")
                .then().statusCode(401);

    }

    @Test
    public void basicAuth_withHeader() {

        given().log().uri()
                .header("Authorization","Basic YWRtaW46YWRtaW4=")
                .when().get("api/spartans")
                .then().statusCode(200);

    }
}
