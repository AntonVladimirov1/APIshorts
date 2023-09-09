package com.kuku.apiTest_Spartans;

import com.kuku.JUNIT5.Hooks.Hooks_HR;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class Practice_JsonPath extends Hooks_HR {



    @DisplayName("GET/request to countries using Jpath")
    @Test
    public void test1() {

        Response response = get("/countries");
        //response.prettyPrint();

        assertEquals(HttpStatus.SC_OK,response.statusCode());

        JsonPath jsonPath = response.jsonPath();

                // get 3rd country name
        System.out.println("jsonPath.get(\"items[2].country_name\") = " + jsonPath.get("items[2].country_name"));

                // get 3rd and 4th country names together
        System.out.println("jsonPath.get(\"items[2,3].country_name\") = " + jsonPath.get("items[2,3].country_name"));

                //* get all country names where region_id is 1 - like DB query

        List<Object> list = jsonPath.get("items.findAll {it.region_id==1}.country_name");
        System.out.println("countries list = " + list);


    }
    @DisplayName("GET/all employees?limit=200 with JsonPath")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).and().queryParam("limit", 200)
                .when().get("/employees");
        //response.prettyPrint();

        assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        List<Map<String,Object>> employees = jsonPath.get("items");
        for (Map<String, Object> employee : employees) {
            System.out.println("employee = " + employee);
        }


        List<Object> list = jsonPath.getList("items.email");
        //System.out.println(list);
        List<Object> list1 = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
        //System.out.println("IT_PROG emails - "+list1);
        List<Object> list2 = jsonPath.getList("items.findAll {it.salary>15000}.first_name");
        //System.out.println(list2);
        Object Max = jsonPath.get("items.max {it.salary}.first_name");
        //System.out.println(Max);
        Object Min = jsonPath.get("items.min {it.salary}.first_name");
        //System.out.println(Min);
    }
}
