package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateBookingTest extends BaseTest {
	@Test
	public void updatebookingtest() throws JSONException {

		// Create request
		Response responseCreate = CreateBooking();
		responseCreate.print();
        // Get Booking id of new booking
		int bookingid =responseCreate.jsonPath().getInt("bookingid");

		// Update Booking
		JSONObject body = new JSONObject();
		body.put("firstname", "Joseph");
		body.put("lastname", "Ambrose");
		body.put("totalprice", 150);
		body.put("depositpaid", true);

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2023-05-11");
		bookingdates.put("checkout", "2023-05-29");
		body.put("bookingdates", bookingdates);
		//   body.put("additionalneeds", "Baby crib");

		// Get response
		Response  responseUpdate= RestAssured.given(spec).auth().preemptive().basic("admin","password123").contentType(ContentType.JSON).body(body.toString())
				.put("/booking/" + bookingid);
		responseUpdate.print();

		// Credentials for Authorization

	     //	   "username" : ,
		//	   "password" :

		// verification
		
		Assert.assertEquals(responseUpdate.getStatusCode(), 200, "The expected status code 200 is not recieved");
		// verificaion using soft assertion

		SoftAssert softassert = new SoftAssert();

		String actualfirstname = responseUpdate.jsonPath().getString("firstname");
		softassert.assertEquals(actualfirstname, "Joseph", "Name is not matched");

		String actuallastname = responseUpdate.jsonPath().getString("lastname");
		softassert.assertEquals(actuallastname, "Ambrose", "Name is not matched");

		int pricematch = responseUpdate.jsonPath().getInt("totalprice");
		softassert.assertEquals(pricematch, 150, "Price is not matched");

		boolean depositpaid = responseUpdate.jsonPath().getBoolean("depositpaid");
		softassert.assertTrue(depositpaid, "deposit paid is not true");

		String actualCheckin = responseUpdate.jsonPath().getString("bookingdates.checkin");
		softassert.assertEquals(actualCheckin, "2023-05-11", "Date is not matched");

		String actualCheckout = responseUpdate.jsonPath().getString("bookingdates.checkout");
		softassert.assertEquals(actualCheckout, "2023-05-29", "Date is not matched");

		softassert.assertAll();

	
	}



}

/*
 * { "firstname": "Sally", "lastname": "Brown", "totalprice": 111,
 * "depositpaid": true, "bookingdates": { "checkin": "2013-02-23", "checkout":
 * "2014-10-23" }, "additionalneeds": "Breakfast" }
 */