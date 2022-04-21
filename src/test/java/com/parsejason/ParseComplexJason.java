package com.parsejason;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ParseComplexJason {
	public static void main(String[] args) {

		// Print No of courses returned by API
		JsonPath js = new JsonPath(Payload.coursePurchase());
		System.out.println("print No. of courses return by API");
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print Purchase Amount
		int TotalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("purchaseAmount :" + TotalAmount);

		// Print Title of the first course
		String TitleFirstCourse = js.getString("courses[0].title");
		System.out.println("TitleFirstCourse :" + TitleFirstCourse);

		// Print All course titles and their respective Prices
		for (int i = 0; i < count; i++) {
			String Title = js.getString("courses[" + i + "].title");
			String price = js.getString("courses[" + i + "].price");
			System.out.println(Title + " :" + price);
		}
		// Print no of copies sold by RPA Course

		for (int i = 0; i<count;i++) 
		{
			String courseTitles = js.get("courses[" + i + "].title");
			if (courseTitles.equalsIgnoreCase("RPA")) 
			{
				int copies = js.get("courses[" + i + "].copies");
				System.out.println("Copies of RPA :" + copies);
				break;
			}

		}
		
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		int TotalSum =0;
		for(int i=0;i<count;i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies=js.get("courses[" + i + "].copies");
			TotalSum = TotalSum+(price*copies);
			
			}
		System.out.println("TotalSum :"+TotalSum);
		if(TotalSum==TotalAmount) {
			System.out.println("Sum of all Course prices matches with Purchase Amount");
		}



	}

}
