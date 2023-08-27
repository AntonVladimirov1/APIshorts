package com.kuku.CydeoDeserialPOJO;

import com.kuku.POJO.Student;
import com.kuku.POJO.Students;
import com.kuku.Utility.Hooks_Cydeo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CydeoTrainingPojo extends Hooks_Cydeo {

    @Test
    public void test1() {

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("id",2)
                .when().get("/student/{id}");
                assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        // Deserialize (from json object to java class)
        Student student = jsonPath.getObject("students[0]", Student.class);

        System.out.println("FirstName() = " + student.getFirstName());
        System.out.println("Batch() = " + student.getBatch());
        System.out.println("EmailAddress() = " + student.getContact().getEmailAddress());
        System.out.println("Street() = " + student.getCompany().getAddress().getStreet());
        System.out.println("ZipCode() = " + student.getCompany().getAddress().getZipCode());

        //  assertion with jsonPath
        assertEquals("Mark",jsonPath.getString("students[0].firstName"));
        assertEquals(13, jsonPath.getInt("students[0].batch"));
        assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));

        //  assertion with Deserialization(Pojo classes)
        assertEquals("Mark",student.getFirstName());
        assertEquals(13,student.getBatch());
        assertEquals("mark@email.com",student.getContact().getEmailAddress());
        assertEquals("777 5th Ave",student.getCompany().getAddress().getStreet());
        assertEquals(33222,student.getCompany().getAddress().getZipCode());

    }

    @Test
    public void test2() {

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("id",2)
                .when().get("/student/{id}");
        assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        // Deserialize (from json object to java class)
        Students students = jsonPath.getObject("", Students.class);
        // Here we deserialize everything to Students class which is holding list of Student
        Student student = students.getStudents().get(0);

        // if there is no path, we can use response.as method for deserialization
        //  Students studentsWithAs = response.as(Students.class);

        System.out.println("FirstName() = " + student.getFirstName());
        System.out.println("Batch() = " + student.getBatch());
        System.out.println("EmailAddress() = " + student.getContact().getEmailAddress());
        System.out.println("Street() = " + student.getCompany().getAddress().getStreet());
        System.out.println("ZipCode() = " + student.getCompany().getAddress().getZipCode());

        //  assertion with jsonPath
        assertEquals("Mark",jsonPath.getString("students[0].firstName"));
        assertEquals(13, jsonPath.getInt("students[0].batch"));
        assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));

        //  assertion with Deserialization(Pojo classes)
        assertEquals("Mark",student.getFirstName());
        assertEquals(13,student.getBatch());
        assertEquals("mark@email.com",student.getContact().getEmailAddress());
        assertEquals("777 5th Ave",student.getCompany().getAddress().getStreet());
        assertEquals(33222,student.getCompany().getAddress().getZipCode());

    }

    @Test
    public void test3() {

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("id",2)
                .when().get("/student/{id}");
        assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        com.kuku.POJO.ready.Student student = jsonPath.getObject("students[0]", com.kuku.POJO.ready.Student.class);

        System.out.println("student.JoinDate() = " + student.getJoinDate());
        System.out.println("student.startDate = " + student.getCompany().startDate);
        System.out.println("student.City() = " + student.getCompany().getAddress().getCity());

    }


}

