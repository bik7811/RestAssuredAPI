package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;
public class GETReadSingleProduct {
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void readSingleProduct() {
		Response response = 
		given()
			.baseUri("http://techfios.com/api-prod/api/product")
			.header("Content-Type", "application/json; charset = UTF-8")
			.queryParam("id", "1209")
		.when()
			.get("read_one.php")
		
		.then()
			.extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code is "+statusCode);
//		Assert.assertEquals(statusCode, 201);
		softAssert.assertEquals(statusCode, 201);
		
		String responseBody = response.getBody().asString();
//		System.out.println(responseBody);
		
		String responseHeader = response.header("Content-Type");
		System.out.println(responseHeader);
		softAssert.assertEquals(responseHeader, "application/jso", "Header Mismatch");
		
		long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("ResponseTime is "+responseTime);
		
		if(responseTime<=500) {
			System.out.println("Response time is within range.");
		}
		else {
			System.out.println("Response time is not within range");
		}
		
		JsonPath js = new JsonPath(responseBody);
		js.prettyPrint();
		
		String productID = js.getString("id");
		String productName = js.getString("name");
		String productDescription = js.getString("description");
		Assert.assertEquals(productID, "1209");
		Assert.assertEquals(productName, "HP Laptop Elite Pro");
		Assert.assertEquals(productDescription, "Super fast laptop");
		
		softAssert.assertAll();// this will tell you where it fails
								// without this test would not fail
		
	}
}
