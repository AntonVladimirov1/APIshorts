package com.kuku.Utility;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class Hooks_NewSpartansAuth {

    public static RequestSpecification reqAdminSpec;
    public static ResponseSpecification resSpec;
    public static RequestSpecification reqUserSpec;

    @BeforeAll
    public static void initSpartans(){
        baseURI="http://54.82.83.115";
        port = 7000;
        basePath = "/api";

         reqAdminSpec = given().log().all()
                        .accept(ContentType.JSON)
                        .auth().basic(ConfigurationReader.getProperty("admin_username"),ConfigurationReader.getProperty("admin_password"));

        reqUserSpec = given().log().all()
                .accept(ContentType.JSON)
                .auth().basic("user", "user");

         resSpec = expect().statusCode(200)
                        .contentType(ContentType.JSON);
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

    public static RequestSpecification dynamicReqSpec(String userName,String password){
        return given().log().all()
                .accept(ContentType.JSON)
                .auth().basic(userName, password);
    }
    public static ResponseSpecification dynamResSpec(int statCode){
        return expect().statusCode(statCode)
                .contentType(ContentType.JSON);
    }

}
