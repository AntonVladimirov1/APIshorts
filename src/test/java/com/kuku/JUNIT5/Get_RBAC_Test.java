package com.kuku.JUNIT5;

import com.kuku.Utility.Hooks_NewSpartansAuth;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get_RBAC_Test extends Hooks_NewSpartansAuth {

    @ParameterizedTest
    @CsvFileSource(resources = "/data/Get_RBAC.csv",numLinesToSkip = 1)

    public void verify_Id_3(String userName,String password,int id,int statusCode){

        given()
                .spec(dynamicReqSpec(userName,password))
                .pathParam("id",id)
           .when()
                .get("/spartans/{id}").prettyPeek()
           .then()
                .spec(dynamResSpec(statusCode))
                .body("id",is(id));

    }

}
