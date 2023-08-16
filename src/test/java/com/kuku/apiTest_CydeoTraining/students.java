package com.kuku.apiTest_CydeoTraining;

import com.kuku.Utility.Hooks_Cydeo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class students extends Hooks_Cydeo {

    @Test
    public void test1() {

        Response response = get("/student/2");
        response.prettyPrint();
        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertTrue(response.headers().hasHeaderWithName("Date"));

        JsonPath jsonPath = response.jsonPath();

        Object name = jsonPath.get("students[0].firstName");
        System.out.println("name = " + name);
        Object batch = jsonPath.getInt("students[0].batch");
        System.out.println("batch = " + batch);
        Object companyName = jsonPath.get("students[0].company.companyName");
        System.out.println("companyName = " + companyName);
        Object emailAddress = jsonPath.get("students[0].contact.emailAddress");
        System.out.println("emailAddress = " + emailAddress);

        assertEquals("Mark",name);
        assertEquals(13,batch);
        assertEquals("Cydeo",companyName);
        assertEquals("mark@email.com",emailAddress);


    }
}
