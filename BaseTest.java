package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
      protected   RequestSpecification spec;
@BeforeMethod
        public void SetUp()
        {
              spec  = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
        }
    protected  static Response CreateBooking() {
            // Create JSON body
            JSONObject body = new JSONObject();
            body.put("firstname", "Chirag");
            body.put("lastname", "Joshi");
            body.put("totalprice", 150);
            body.put("depositpaid", true);

            JSONObject bookingdates = new JSONObject();
            bookingdates.put("checkin", "2023-05-11");
            bookingdates.put("checkout", "2023-05-29");
            body.put("bookingdates", bookingdates);
            //   body.put("additionalneeds", "Baby crib");
            // Get response
            Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString())
                    .post("https://restful-booker.herokuapp.com/booking");
            return response;

    }
}

