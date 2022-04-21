package com.basic;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class EndToEndTesting {
	
	public static void main(String[] args) {

		// given - all input details ,
		// when - submit the api resource,http method ,
		// Then - validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body(Payload.addPlace()).when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200)
				.body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.41 (Ubuntu)")
				.extract().response()
				.asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		String newAddress = "Amravati";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "    \"place_id\": \""+placeId+"\",\r\n"
				+ "    \"address\": \"Amravati\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}")
		
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//get place
		String getPlaceResponse =given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();
		System.out.println(getPlaceResponse);
		
		JsonPath js2= new JsonPath (getPlaceResponse);
		String actualAddress =js2.getString("address");
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, newAddress);
	}


}
