package com.parsejason;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	@Test
	public void SumOfCourses() {
		
		JsonPath js = new JsonPath(Payload.coursePurchase());
		int count =js.get("courses.size()");
		int sum=0;
		
		for(int i=0;i<count;i++) {
			int price=js.get("courses[].price");
			int copies=js.get("courses[].copies");
			 int amount=price*copies;
			 System.out.println(amount);
			 
			 sum= sum+amount;
			 
		}
		System.out.println(sum);
		int purchaseAmount=js.get("dashboard.purchaseAmount");
		Assert.assertEquals(sum,purchaseAmount);
		

	}

}
