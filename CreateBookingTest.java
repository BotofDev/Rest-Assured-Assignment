package com.herokuapp.restfulbooker;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;

public class CreateBookingTest  extends BaseTest {
	@Test
	public void createbookingtest() throws JSONException {

		// Create JSON body
		Response response = CreateBooking();

		response.print();

		// verification
		
		Assert.assertEquals(response.getStatusCode(), 200, "The expected status code 200 is not recieved");

		// verificaion using soft assertion

		SoftAssert softassert = new SoftAssert();

		String actualfirstname = response.jsonPath().getString("booking.firstname");
		softassert.assertEquals(actualfirstname, "Chirag", "Name is not matched");

		String actuallastname = response.jsonPath().getString("booking.lastname");
		softassert.assertEquals(actuallastname, "Joshi", "Name is not matched");

		int pricematch = response.jsonPath().getInt("booking.totalprice");
		softassert.assertEquals(pricematch, 150, "Price is not matched");

		boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
		softassert.assertTrue(depositpaid, "deposit paid is not true");

		String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
		softassert.assertEquals(actualCheckin, "2023-05-11", "Date is not matched");

		String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
		softassert.assertEquals(actualCheckout, "2023-05-29", "Date is not matched");

		softassert.assertAll();

	
	}



}

/*
 * { "firstname": "Sally", "lastname": "Brown", "totalprice": 111,
 * "depositpaid": true, "bookingdates": { "checkin": "2013-02-23", "checkout":
 * "2014-10-23" }, "additionalneeds": "Breakfast" }
 */