package com.basic;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ParseJason {

	public static void main(String[] args) {

		// given - all input details ,
		// when - submit the api resource,http method ,
		// Then - validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response()
				.asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
	}

}
