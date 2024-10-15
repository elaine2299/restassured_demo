package localAPIs;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class LocalAPI {

    @Test(priority = 1)
    public void get(){

        baseURI = "http://localhost:3000";

        given()
                .get("/users").
        then()
                .statusCode(200)
                .log()
                .all();
    }

    /* @Test
    public void post(){
        JSONObject request = new JSONObject();
        request.put("fname", "Crizel");
        request.put("subjectID", "2");

        baseURI = "http://localhost:3000/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString()).
        when()
                .post("/users").
        then()
                .statusCode(201);
    }

    @Test   --- This test will fail as PUT request is used to update all the fields for that user and not just one field as per the script below
    public void put(){
        JSONObject request2 = new JSONObject();
        request2.put("id", "5");

        baseURI = "http://localhost:3000/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request2.toJSONString()).
         when()
                .put("/users?fname=Crizel").
                then()
                .statusCode(400);
    }
    */

    @Test(priority = 2)
    public void patch(){
        JSONObject request3 = new JSONObject();
        request3.put("fname", "Evaristo");
        System.out.println(request3.toJSONString());

        baseURI = "http://localhost:3000";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request3.toJSONString()).
        when()
                .patch("/users/4").
        then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test(priority = 3)
    public void get2(){

        baseURI = "http://localhost:3000";

        given()
                .get("/users").
                then()
                .statusCode(200)
                .log()
                .all();
    }


}
