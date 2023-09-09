package com.kuku.JUNIT5.Hooks;

import com.kuku.Utility.BookItUtils3;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class Hooks_BookIt {

    @BeforeAll
    public static void initBookIt(){

        baseURI= "https://api.qa.bookit.cydeo.com";
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

    public static RequestSpecification dynamicReqSpec(String userType){
        return given().log().all()
                .accept(ContentType.JSON)
                .auth().oauth2(BookItUtils3.getTokenByUserType(userType));
    }
    public static ResponseSpecification dynamResSpec(int statCode){
        return expect().statusCode(statCode)
                .contentType(ContentType.JSON);
    }

}
