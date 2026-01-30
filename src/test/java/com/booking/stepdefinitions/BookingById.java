package com.booking.stepdefinitions;

import com.booking.api.config.BaseApiConfig;
import com.booking.helper.ApiResponseHelper;
import com.booking.helper.ApiScenarioHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingById extends BaseApiConfig {

    private Response response;

    // ---------- GET BOOKING BY ID ----------

    @When("I retrieve the booking by id")
    public void iRetrieveTheBookingById() {
        String token = ApiScenarioHelper.getToken();
        Integer bookingId = ApiScenarioHelper.getBookingId();
        assertNotNull(token, "Token not set");
        assertNotNull(bookingId, "bookingId not set");

        response = baseRequest()
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .extract()
                .response();

        System.out.println("GET /booking/{id} response:");
        System.out.println(response.asString());

        ApiResponseHelper.setLastResponse(response);
    }

    @When("I retrieve the booking by id without authentication")
    public void iRetrieveTheBookingByIdWithoutAuthentication() {
        Integer bookingId = ApiScenarioHelper.getBookingId();
        assertNotNull(bookingId, "bookingId not set");

        response = baseRequest()
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .extract()
                .response();

        ApiResponseHelper.setLastResponse(response);
    }

    @Then("the booking details should match the created booking")
    public void theBookingDetailsShouldMatchTheCreatedBooking() {
        Integer bookingId = ApiScenarioHelper.getBookingId();

        assertEquals(bookingId, response.jsonPath().getInt("bookingid"));
        assertEquals(ApiScenarioHelper.getFirstname(), response.jsonPath().getString("firstname"));
        assertEquals(ApiScenarioHelper.getLastname(), response.jsonPath().getString("lastname"));
        assertEquals(ApiScenarioHelper.getRoomId(), response.jsonPath().getInt("roomid"));
        assertEquals(ApiScenarioHelper.getDepositPaid(), response.jsonPath().getBoolean("depositpaid"));
        assertEquals(ApiScenarioHelper.getCheckin(), response.jsonPath().getString("bookingdates.checkin"));
        assertEquals(ApiScenarioHelper.getCheckout(), response.jsonPath().getString("bookingdates.checkout"));
    }


}
