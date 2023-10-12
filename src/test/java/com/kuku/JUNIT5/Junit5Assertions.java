package com.kuku.JUNIT5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Junit5Assertions {

    //* Hard assertion

    @Test
    public void hardAssert() {
        assertEquals(10,5+5);
        System.out.println("---First Assert is Done---");
        assertEquals(10,5+3);
        System.out.println("---Second Assert is Done---");
        assertEquals(10,5+5);
        System.out.println("---Third Assert is Done---");
    }

    //* Soft assertion

    @Test
    public void softAssert() {


        assertAll("Soft Assert",      // lambda expression ()->
                ()->assertEquals(10,5+5,"1 assert"),
                ()->assertEquals(10,5+3,"2 assert"),
                ()->assertEquals(10,5+4,"3 assert"));

    }
}
