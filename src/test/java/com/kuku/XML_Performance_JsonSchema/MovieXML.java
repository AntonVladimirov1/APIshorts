package com.kuku.XML_Performance_JsonSchema;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class MovieXML {

    @Test
    public void test1() {

        Response response = given()
                .queryParams("apikey", "81815fe6")
                .queryParams("r", "xml")
                .queryParams("t", "Back to the future")
                .when()
                .get("http://www.omdbapi.com").prettyPeek();

        XmlPath xmlPath = response.xmlPath();

        System.out.println("Movie name = " + xmlPath.getString("root.movie.@title"));
        System.out.println("year = " + xmlPath.getString("root.movie.@year"));
        System.out.println("Director = " + xmlPath.getString("root.movie.@director"));

    }

    @Test
    public void test2() {

        Response response = given()
                .queryParams("apikey", "81815fe6")
                .queryParams("r", "xml")
                .queryParams("s", "Harry Potter")
                .when()
                .get("http://www.omdbapi.com").prettyPeek();

        XmlPath xmlPath = response.xmlPath();

        // verify - totalResults = 134
        Object totalResult = xmlPath.get("root.@totalResults");
        assertThat(totalResult,is("134"));

        // verify - if Harry Potter in every title
        List<String> allTitles = xmlPath.getList("root.result.@title");
        assertThat(allTitles,everyItem(containsString("Harry Potter")));
        System.out.println("allTitles = " + allTitles);

        List<Integer> allYears =  xmlPath.getList("root.result.@year");


    }
}
