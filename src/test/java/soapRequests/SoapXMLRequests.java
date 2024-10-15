/* Approcah 1 -  Using IOUtils.toString() from the org.apache.commons.io package. PS: This approach does not work for the code specified below.
package soapRequests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.internal.util.IOUtils;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SoapXMLRequests {

    @Test
    public void validateSoapXML() throws IOException {
        File file = new File("./SoapRequests/Add.xml");

        if (file.exists()) {
            System.out.println(">>> File Exists");
        } else {
            System.out.println(">>> File Not Found");
            return; // Exit if file doesn't exist
        }

        // Reading the XML file content
        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
        fileInputStream.close(); // Close the input stream

        baseURI = "https://ecs.syr.edu/";

        // Sending the SOAP request
        given()
                .contentType("text/xml")
                .accept(ContentType.XML)
                .body(requestBody)
        .when()
                .post("/faculty/fawcett/Handouts/CSE686/code/calcWebService/Calc.asmx?op=Add") // Specify the correct endpoint
        .then()
                .statusCode(200) // Check for expected response status
                .log()
                .all(); // Log the response
    }
}

 */

// Approach 2 - Using the java.nio.file.Files class
package soapRequests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SoapXMLRequests {

    @Test
    public void validateSoapXML() throws IOException {
        File file = new File("./SoapRequests/Add.xml");

        if (file.exists()) {
            System.out.println(">>> File Exists");
        } else {
            System.out.println(">>> File Not Found");
            return; // Exit if file doesn't exist
        }

        // Reading the XML file content
        String requestBody = new String(Files.readAllBytes(file.toPath()), "UTF-8");

        baseURI = "https://ecs.syr.edu/";

        // Sending the SOAP request
        given()
                .contentType("text/xml")
                .accept(ContentType.XML)
                .body(requestBody)
        .when()
                .post("/faculty/fawcett/Handouts/CSE686/code/calcWebService/Calc.asmx?op=Add") // Specify the correct endpoint
        .then()
                .statusCode(200) // Check for expected response status
                .log().all() // Log the response
        .and()
                .body("//*:AddResult.text()", equalTo("22")); // Adjusted for XML namespaces
    }
}
