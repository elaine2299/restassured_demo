package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PutPatchAndDelete {

    // Put Request
    @Test
    public void putTest(){
        // Put Request using JSON Library
        JSONObject request = new JSONObject();
        request.put("name", "TestName2");
        request.put("job", "TestJob2)");
        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in";

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString()).
        when()
                .put("/api/users/2").
        then()
                .statusCode(200)
                .log().all();
    }

    // Patch Request
    @Test
    public void patchTest(){
        // Put Request using JSON Library
        JSONObject request = new JSONObject();
        request.put("name", "TestName2");
        request.put("job", "TestJob2)");
        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in";

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString()).
        when()
                .patch("/api/users/2").
        then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void deleteTest(){
        baseURI = "https://reqres.in";

        when()
                .delete("/api/users/2").
        then()
                .statusCode(204)
                .log().all();

    }

}
