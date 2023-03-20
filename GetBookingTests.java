package com.herokuapp.restfulbooker;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingTests extends BaseTest {
	@Test
	public void getbooking() {



		// Get response with booking ids
		Response response = RestAssured.given(spec).get("/booking/5");
		response.print();

		// verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "The expected status code 200 is not recieved");

		// verificaion using soft assertion

		SoftAssert softassert = new SoftAssert();

		String actualfirstname = response.jsonPath().getString("firstname");
		softassert.assertEquals(actualfirstname, "Susan", "Name is not matched");

		String actuallastname = response.jsonPath().getString("lastname");
		softassert.assertEquals(actuallastname, "Smith", "Name is not matched");

		int pricematch = response.jsonPath().getInt("pricematch");
		softassert.assertEquals(pricematch, 238, "Price is not matched");

		boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
		softassert.assertTrue(depositpaid, "deposit paid is not true");

		String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
		softassert.assertEquals(actualCheckin, "2019-01-03", "Date is not matched");

		String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
		softassert.assertEquals(actualCheckout, "2022-07-31", "Date is not matched");

		 softassert.assertAll();
		/*
		 * 
		 * {"firstname":"Mary" ,"lastname":"Smith", "totalprice":184,
		 * "depositpaid":false, "bookingdates": {"checkin":"2018-06-15",
		 * "checkout":"2020-10-10"}}
		 */
	}

}
