package com.kuku.Hooks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public class Hooks_SpartanAuth {

    @BeforeAll
    public static void initSpartans(){
        baseURI="http://54.82.83.115:7000";  // Spartan
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

}
