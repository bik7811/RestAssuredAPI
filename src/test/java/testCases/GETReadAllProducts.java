package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class GETReadAllProducts {
	
	@Test
	public void readAllProducts() {
		Response response = 
		given()
			.baseUri("http://techfios.com/api-prod/api/product")
			.header("Content-Type", "application/json; charset = UTF-8")
		.when()
			.get("read.php")
		
		.then()
			.extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code is "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
//		String responseBody = response.getBody().asString();
		String responseBody = response.getBody().prettyPrint();
		System.out.println(responseBody);
	}
}
