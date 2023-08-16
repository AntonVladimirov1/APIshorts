package com.kuku.Utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class Hooks_Spartans {

    @BeforeAll
    public static void initSpartans(){
        baseURI="http://54.82.83.115:8000";  // Spartan
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

}