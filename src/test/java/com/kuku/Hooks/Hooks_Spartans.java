package com.kuku.Hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class Hooks_Spartans {

    public Logger log = LogManager.getLogger(this.getClass());  //  this is log object - logging all flow info to extended class

    @BeforeAll
    public static void initSpartans(){
        baseURI="http://52.91.163.234:8000";  // Spartan
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

}