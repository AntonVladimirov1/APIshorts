package com.kuku.Utility;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class Hooks_HR {

    @BeforeAll
    public static void initHR(){
        baseURI="http://54.82.83.115:1000/ords/hr";  // HR Ords
    }

}
