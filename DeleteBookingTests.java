package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteBookingTests extends BaseTest{
    @Test
    public void deleteBookingTests()
    {
        Response responseCreate = CreateBooking();
        responseCreate.print();
        // Get Booking id of new booking
        int bookingid =responseCreate.jsonPath().getInt("bookingid");



  // Deleting the response using .delete()

        Response  responseDelete= RestAssured.given(spec).auth().preemptive().basic("admin","password123")
                .delete("/booking/" + bookingid);
        responseDelete.print();


        Assert.assertEquals(responseDelete.getStatusCode(), 201, "The expected status code 201 is not recieved");


        Response responseGet = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingid);
        responseGet.print();
        Assert.assertEquals(responseGet.getStatusCode(),404,"");

        Assert.assertEquals(responseGet.getBody().asString(),"Not Found", "Body Should Not be founf but it is");



    }
}
