package com.kuku.Authorization;

import com.kuku.Utility.BookItUtils3;
import com.kuku.JUNIT5.Hooks.Hooks_BookIt;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Auth_withBearerToken2 extends Hooks_BookIt {

    //String email = "lfinnisz@yolasite.com";
    //String password = "lissiefinnis";
    String accessToken = BookItUtils3.getTokenByUserType("team leader");

    @Test
    public void test1() { //    "/api/campuses"

        given().accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);
    }

    @Test
    public void test2() {  //    "/api/users/me"

        given().accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().get("/api/users/me").prettyPeek()
                .then().statusCode(200);
    }

    @Test
    public void test3() {  //*   auth method is cool

        given().accept(ContentType.JSON)
                .auth().oauth2(accessToken)
                .when().get("/api/users/me").prettyPeek()
                .then().statusCode(200);
    }
}
