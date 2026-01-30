package com.booking.stepdefinitions;

import com.booking.api.config.BaseApiConfig;
import com.booking.helper.ApiResponseHelper;
import com.booking.helper.ApiScenarioHelper;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Map;

public class BookingPatchSteps extends BaseApiConfig {

    private Response response;

    @When("I partially update the booking with valid patch data")
    public void iPartiallyUpdateBooking() {
        Integer bookingId = ApiScenarioHelper.getBookingId();

        Map<String, Object> payload = Map.of(
                "firstname", "Jane"
        );

        response = baseRequest()
                .header("Cookie", "token=" + ApiScenarioHelper.getToken())
                .pathParam("id", bookingId)
                .body(payload)
                .when()
                .patch("/booking/{id}")
                .then()
                .extract().response();

        ApiResponseHelper.setLastResponse(response);
    }

    @When("I partially update the booking without authentication")
    public void iPartiallyUpdateBookingWithoutAuthentication() {
        Map<String, Object> payload = Map.of(
                "firstname", "Jane"
        );

        response = baseRequest()
                .pathParam("id", ApiScenarioHelper.getBookingId())
                .body(payload)
                .when()
                .patch("/booking/{id}")
                .then()
                .extract().response();

        ApiResponseHelper.setLastResponse(response);
    }
}
