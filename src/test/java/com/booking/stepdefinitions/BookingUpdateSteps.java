package com.booking.stepdefinitions;

import com.booking.api.config.BaseApiConfig;
import com.booking.builder.BookingPayloadBuilder;
import com.booking.dto.BookingRequest;
import com.booking.helper.ApiResponseHelper;
import com.booking.helper.ApiScenarioHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingUpdateSteps extends BaseApiConfig {

    private Response response;
    private int updatedRoomId;


    @And("I update the booking with new data")
    public void iUpdateTheBookingWithNewData() {
        String token = ApiScenarioHelper.getToken();
        Integer bookingId = ApiScenarioHelper.getBookingId();

        assertNotNull(token, "Token not set");
        assertNotNull(bookingId, "bookingId not set");

        // new data
        updatedRoomId = new Random().nextInt(100) + 1;

        BookingRequest payload = BookingPayloadBuilder.build(
                updatedRoomId,
                "Jane",
                "Smith",
                false,
                "2025-12-01",
                "2025-12-05",
                "john.doe@example.com",
                "11999999999"

        );

        response = baseRequest()
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .body(payload)
                .when()
                .put("/booking/{id}")
                .then()
                .extract()
                .response();

        ApiResponseHelper.setLastResponse(response);
        System.out.println(response.asString());
    }

    @And("I update the booking with new data without authentication")
    public void iUpdateTheBookingWithNewDataWithoutAuthentication() {
        Integer bookingId = ApiScenarioHelper.getBookingId();
        assertNotNull(bookingId, "bookingId not set (did you create a booking?)");

        // reuse same updated values (or just set them here again)
        int anyRoomId = new Random().nextInt(100) + 1;

        BookingRequest payload = BookingPayloadBuilder.build(
                anyRoomId,
                "Jane",
                "Smith",
                false,
                "2025-12-01",
                "2025-12-05",
                "jane.smith@example.com",
                "11888888888"
        );

        response = baseRequest()
                .pathParam("id", bookingId)
                .body(payload)
                .when()
                .put("/booking/{id}")
                .then()
                .extract()
                .response();

        ApiResponseHelper.setLastResponse(response);
    }

    @Then("the updated booking should reflect the new data")
    public void theUpdatedBookingShouldReflectTheNewData() {
        assertTrue(response.jsonPath().getBoolean("success"), "Update should return success=true");
    }

    @And("I update the booking with an invalid firstname")
    public void iUpdateTheBookingWithInvalidFistName() {
        String token = ApiScenarioHelper.getToken();
        Integer bookingId = ApiScenarioHelper.getBookingId();

        assertNotNull(token, "Token not set");
        assertNotNull(bookingId, "bookingId not set");

        // new data
        updatedRoomId = new Random().nextInt(100) + 1;

        BookingRequest payload = BookingPayloadBuilder.build(
                updatedRoomId,
                "Ja",
                "Smith",
                false,
                "2025-12-01",
                "2025-12-05",
                "john.doe@example.com",
                "11999999999"
        );

        response = baseRequest()
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .body(payload)
                .when()
                .put("/booking/{id}")
                .then()
                .extract()
                .response();

        ApiResponseHelper.setLastResponse(response);
        System.out.println(response.asString());
    }

}
