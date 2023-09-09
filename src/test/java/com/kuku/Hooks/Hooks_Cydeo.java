package com.kuku.Hooks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class Hooks_Cydeo {

    @BeforeAll
    public static void initCydeo(){

        baseURI= "https://api.training.cydeo.com";
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

}
