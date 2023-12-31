package com.kuku.JUNIT5;

import com.kuku.Hooks.Hooks_NewSpartansAuth;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;

public class Delete_RBAC_Test extends Hooks_NewSpartansAuth {

    @ParameterizedTest
    @CsvFileSource(resources = "/data/Delete_RBAC.csv",numLinesToSkip = 1)

    public void verify_Id_3(String userName,String password,int id,int statusCode){

        given()
                .spec(dynamicReqSpec(userName,password))
                .pathParam("id",id)
                .when()
                .delete("/spartans/{id}").prettyPeek()
                .then()
                .spec(dynamResSpec(statusCode));
    }


}
