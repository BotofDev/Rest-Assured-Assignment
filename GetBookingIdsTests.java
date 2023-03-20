package com.herokuapp.restfulbooker;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIdsTests extends BaseTest{
	@Test
	
	public void getbookingwihtoutfilter()
	{
		// Get response with booking ids
		Response response = RestAssured.given(spec).get("/booking");
		response.print();
		
	    // verify response 200
		Assert.assertEquals(response.getStatusCode(), 200,"The expected status code 200 is not recieved");
		
		// verify atleast 1 booking id in repsonse
		List<Integer> bookingids = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingids.isEmpty(),"Boooking id shoould not be empty but it is here");
		
	}

	
}
