package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PartialUpdateBookingTests extends BaseTest{
    @Test
    public void partialUpdateBookingTests()
    {
        Response responseCreate = CreateBooking();
        responseCreate.print();
        // Get Booking id of new booking
        int bookingid =responseCreate.jsonPath().getInt("bookingid");


        JSONObject body = new JSONObject();
        body.put("firstname", "Joseph");
        body.put("lastname", "Ambrose");


        Response  responseUpdate= RestAssured.given(spec).auth().preemptive().basic("admin","password123").contentType(ContentType.JSON).body(body.toString())
                .patch("/booking/" + bookingid);
        responseUpdate.print();


        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "The expected status code 200 is not recieved");
        // verificaion using soft assertion

        SoftAssert softassert = new SoftAssert();

        String actualfirstname = responseUpdate.jsonPath().getString("firstname");
        softassert.assertEquals(actualfirstname, "Joseph", "Name is not matched");

        String actuallastname = responseUpdate.jsonPath().getString("lastname");
        softassert.assertEquals(actuallastname, "Ambrose", "Name is not matched");

        softassert.assertAll();
    }
}
