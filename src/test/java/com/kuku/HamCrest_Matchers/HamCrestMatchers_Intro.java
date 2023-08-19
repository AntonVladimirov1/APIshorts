package com.kuku.HamCrest_Matchers;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class HamCrestMatchers_Intro {


    @Test
    public void numbers() {

        // this is JUnit 5 assertion method
        assertEquals(9,6+3);


        // this is HamCrest_Matchers Matcher methods
        assertThat(6+3, is(9));
        assertThat(6+3,is(equalTo(9)));
        assertThat(6+3,equalTo(9));

        // negative assertion
        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        assertThat(5+6,is(greaterThan(10)));
        assertThat(5+6,greaterThan(10));
        assertThat(5+6,greaterThanOrEqualTo(11));
        assertThat(5+6,lessThan(12));
        assertThat(5+6,lessThanOrEqualTo(11));

    }

    @Test
    public void testStrings() {

        String msg = "API is fun!";

        assertThat(msg,is("API is fun!"));
        assertThat(msg,equalTo("API is fun!"));
        assertThat(msg,equalToIgnoringCase("api is fun!"));
        assertThat(msg,startsWith("API"));
        assertThat(msg,startsWithIgnoringCase("api"));
        assertThat(msg,endsWith("fun!"));
        assertThat(msg,endsWithIgnoringCase("FuN!"));
        assertThat(msg,containsString("is"));
        assertThat(msg,containsStringIgnoringCase("IS"));
        assertThat(msg,not("jhgjhgjhg"));

    }

    @Test
    public void testCollections() {

        List<Integer> numberList = new ArrayList<>(Arrays.asList(3,5,48,32,75,89));

        assertThat(numberList,hasSize(6));
        assertThat(numberList,hasItem(75));
        assertThat(numberList,hasItems(48,89,5));

        assertThat(numberList,everyItem(greaterThanOrEqualTo(1)));

        assertThat(numberList,containsInRelativeOrder(3,5,48));
        assertThat(numberList,containsInAnyOrder(75,48,32,3,5,89));

    }
}
