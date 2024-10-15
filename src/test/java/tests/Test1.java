package tests;

//import io.restassured.RestAssured;
// Static import
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 {
    @Test
    public void test1(){

        // To get the response
        // Rest Assured is a Class - This includes various request types

        // Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        // Using Static import
        Response response = get("https://reqres.in/api/users?page=2");

        // To get the Status Code
        System.out.println("The Response Code is: " + response.getStatusCode());

        // To get the Time
        System.out.println("The Response Time is: " + response.getTime());

        // To get the Reponse Payload/Body
        System.out.println("The Response Payload/Body is: " + response.getBody().asString());

        // To get the Status Line
        System.out.println("The Status Line is: " + response.getStatusLine());

        // To get the Header
        System.out.println("The Content Header is: " + response.getHeader("content-type"));

        // Tp get the Cookies
        System.out.println("The Cookies received from the server are: " + response.getCookies());

        // To validate the response code using assertion
        int requestStatusCode = response.getStatusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(requestStatusCode, expectedStatusCode);

    }

    @Test
    public void test2(){
        baseURI = "https://reqres.in/api";

        //given().get("/users?page=2").then().statusCode(400);

        /* OR
        given()
                .get("/users?page=2").
        then()
                .statusCode(400);
        */

        given()
                .get("/users?page=2").
        then()
                .statusCode(200)
                .body("data[1].id",equalTo(8))
                .log().all();


    }
}
