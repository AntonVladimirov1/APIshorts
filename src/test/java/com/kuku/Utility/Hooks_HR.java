package com.kuku.Utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public abstract class Hooks_HR {

    @BeforeAll
    public static void initHR(){
        baseURI="http://54.82.83.115:1000/ords/hr";  // HR Ords
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

}
