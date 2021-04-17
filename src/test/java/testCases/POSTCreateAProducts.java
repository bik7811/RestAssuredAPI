package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
public class POSTCreateAProducts {
	
	@Test
	public void createAProduct() {
		
		HashMap payload = new HashMap();
		payload.put("name", "HP Laptop Elite Pro");
		payload.put( "description", "Super fast laptop");
		payload.put("price", "1199");
		payload.put("category_name", "Electronics");
		payload.put( "category_id", "2");
		
		
		Response response = 
		given()
			.baseUri("https://techfios.com/api-prod/api/product")
			.header("Content-Type", "application/json; charset = UTF-8")
			.body(payload)
		.when()
			.post("/create.php")
		
		.then()
			.extract().response();
		
		
		String responseBody = response.getBody().asString();
//		String responseBody = response.getBody().prettyPrint();
		System.out.println(responseBody);
		
		JsonPath js = new JsonPath(responseBody);
		String message = js.getString("message");
		Assert.assertEquals(message, "Product was created.");
	}
}
