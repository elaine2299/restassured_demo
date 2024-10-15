package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostExamples {

    // Get Request
    @Test
    public void getTest(){
        baseURI = "https://reqres.in/api";
        given()
                .get("/users?page=2").
        then()
                .statusCode(200)
                .body("data[1].first_name", equalTo("Lindsay"))
                .body("data.first_name", hasItems("Lindsay", "Tobias"));
    }

    // Post Request
    @Test
    public void postTest(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "TestName1");
        map.put("job", "TestJob1");
        /* Add \ before " to get the name is ""
        map.put("\"job\"", "TestJob");
        */
        System.out.println("Response without using JSONObject: " + map);

        JSONObject request1 = new JSONObject(map);
        System.out.println("Response using JSONObject: " + request1.toJSONString());

        // Post Request using JSON Library
        JSONObject request2 = new JSONObject();
        request2.put("name", "TestName2");
        request2.put("job", "TestJob2)");
        System.out.println(request2.toJSONString());

        baseURI = "https://reqres.in";

        given()
                .body(request2.toJSONString()).
        when()
                .post("/users").
        then()
                .statusCode(201)
                .log().all();
    }
}
