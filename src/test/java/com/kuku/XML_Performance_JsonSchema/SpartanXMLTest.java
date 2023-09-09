package com.kuku.XML_Performance_JsonSchema;

import com.kuku.Hooks.Hooks_SpartanAuth;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanXMLTest extends Hooks_SpartanAuth {

    @Test
    public void test1() {

        given().accept(ContentType.XML)
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")//.prettyPeek()
                .then()
                .statusCode(200).contentType(ContentType.XML)
                .body("List.item[0].name",is("Meade"))
                .body("List.item[1].name",is("Nels"));

    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();

        String firstSpartanName = xmlPath.getString("List.item[0].name");
        System.out.println("firstSpartanName = " + firstSpartanName);

        String lastSpartanName = xmlPath.getString("List.item[-1].name");
        System.out.println("firstSpartanName = " + lastSpartanName);

        List<String> allNames = xmlPath.getList("List.item.name");
        System.out.println("allNames = " + allNames);

    }
}
