package com.kuku.Utility;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class Hooks_Spartans {

    @BeforeAll
    public static void initSpartans(){
        baseURI="http://54.82.83.115:8000";  // Spartan
    }

}