package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class AllRequestTypes {

    @BeforeClass
    public void baseURI(){
        baseURI = "https://reqres.in";
    }

    @Test(priority = 1)
    public void getTest(){
        given()
                .get("/api/users/2").
        then()
                .statusCode(200);
    }

    @Test (priority = 2)
    public void postTest(){

        JSONObject request = new JSONObject();
        request.put("name", "Test Name");
        request.put("designation", "QA");

        given()
                .body(request.toJSONString()).
        when()
                .post("/api/users").
        then()
                .statusCode(201)
                .log()
                .all();
    }

    @Test(priority = 3)
    public void putTest(){
        JSONObject request = new JSONObject();
        request.put("name", "Test Name");
        request.put("designation", "QA");

        given()
                .body(request.toJSONString()).
        when()
                .put("/api/users/2").
        then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test (priority = 4)
    public void patchTest(){
        JSONObject request = new JSONObject();
        request.put("name", "Test Name");
        request.put("designation", "QA");

        given()
                .body(request.toJSONString()).
                when()
                .patch("/api/users/2").
        then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test (priority = 5)
    public void deleteTest(){
        when()
                .delete("/api/users?page=2").
        then()
                .statusCode(204);
        System.out.println("Test Done");
    }
}

