package com.kuku.JUNIT5;

import org.junit.jupiter.api.*;

public class Junit5AnnotationLifeCycle {

    @BeforeAll
    public static void init(){
        System.out.println("------------BeforeAll is running------");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("---BeforeEach is running-----");
    }

    @Test
    public void test1() {
        System.out.println("-----Test 1 is running------");
    }

    @Test
    public void test2() {
        System.out.println("-----Test 2 is running------");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("---AfterEach is running-----");
    }

    @AfterAll
    public static void destroy(){
        System.out.println("------------AfterAll is running------");
    }
}
