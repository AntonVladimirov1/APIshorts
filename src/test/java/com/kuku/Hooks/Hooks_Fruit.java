package com.kuku.Hooks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class Hooks_Fruit {

    @BeforeAll
    public static void initHR(){
        baseURI="https://api.predic8.de/shop/v2";
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

}
