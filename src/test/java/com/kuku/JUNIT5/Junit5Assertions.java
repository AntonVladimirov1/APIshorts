package com.kuku.JUNIT5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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

        assertAll("Soft Assert",
                ()->assertEquals(10,5+5),
                ()->assertEquals(10,5+3),
                ()->assertEquals(10,5+4));

    }
}
