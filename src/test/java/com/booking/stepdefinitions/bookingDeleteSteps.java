package com.booking.stepdefinitions;

import com.booking.ApiResponseHelper;
import com.booking.helper.ApiScenarioHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class bookingDeleteSteps {
    private static final String BASE_URL = "https://automationintesting.online/api";
    private Response response;


    @And("I have a non-existing booking id")
    public void iHaveANonExistingBookingId() {
        ApiScenarioHelper.setBookingId(999999999);
    }

    @And("I have a negative booking id")
    public void iHaveANegativeBookingId() {
        ApiScenarioHelper.setBookingId(-5);
    }

    @And("I delete the booking")
    public void iDeleteTheBooking() {
        String token = ApiScenarioHelper.getToken();
        Integer bookingId = ApiScenarioHelper.getBookingId();

        assertNotNull(token, "Token not set (did you request an auth token?)");
        assertNotNull(bookingId, "bookingId not set (did you create a booking?)");

        response = given()
                .baseUri(BASE_URL)
                .accept(JSON)
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .when()
                .delete("/booking/{id}")
                .then()
                .extract()
                .response();

        ApiResponseHelper.setLastResponse(response);
    }

    @When("I delete the booking without authentication")
    public void iDeleteTheBookingWithoutAuthentication() {
        Integer bookingId = ApiScenarioHelper.getBookingId();
        assertNotNull(bookingId, "bookingId not set (did you create a booking?)");

        response = given()
                .baseUri(BASE_URL)
                .accept(JSON)
                .pathParam("id", bookingId)
                .when()
                .delete("/booking/{id}")
                .then()
                .extract()
                .response();

        ApiResponseHelper.setLastResponse(response);
    }
}
