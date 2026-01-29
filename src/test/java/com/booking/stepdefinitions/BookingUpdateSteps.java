package com.booking.stepdefinitions;

import com.booking.ApiResponseHelper;
import com.booking.builder.BookingPayloadBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingUpdateSteps {
    private static final String BASE_URL = "https://automationintesting.online/api";

    private Response response;

    private String token;
    private Integer bookingId;
    private int roomId;


    // updated expected data
    private String updatedFirstname;
    private String updatedLastname;
    private String updatedCheckin;
    private String updatedCheckout;
    private int updatedRoomId;

    // ---------- AUTH ----------

    @When("I authenticate as admin")
    public void iAuthenticateAsAdmin() {
        response = RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .accept(JSON)
                .body("{\"username\":\"admin\",\"password\":\"password\"}")
                .when()
                .post("/auth/login");

        assertEquals(200, response.statusCode(), "Login should return 200");

        token = response.jsonPath().getString("token");
        assertNotNull(token, "Token should not be null");
        assertFalse(token.isBlank(), "Token should not be blank");
    }

    // ---------- CREATE BOOKING ----------
    @And("I create a booking")
    public void iSendAPostRequestToBookingWithValidData(){
        roomId = new Random().nextInt(100) + 1;

        String payload = BookingPayloadBuilder.build(
                roomId,
                "John",
               "Doe",
                 true,
                "2025-11-01",
                 "2025-11-05",
                "john.doe@example.com",
                "11999999999"
        );

        response =
                given()
                        .baseUri("https://automationintesting.online/api")
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload)
                        .when()
                        .post("/booking")
                        .then()
                        .extract().response();

        int status = response.statusCode();
        assertTrue(status == 200 || status == 201,
                "Expected 200 or 201 but got " + status + " body: " + response.asString());

        bookingId = response.jsonPath().getInt("bookingid");
        assertNotNull(bookingId, "bookingid should not be null");
        assertTrue(bookingId > 0, "bookingid should be > 0");

    }

    @And("I update the booking with new data")
    public void iUpdateTheBookingWithNewData() {
        assertNotNull(token, "Token not set");
        assertNotNull(bookingId, "bookingId not set");

        // new data
        updatedRoomId = new Random().nextInt(100) + 1;
        updatedFirstname = "Jane";
        updatedLastname = "Smith";
        updatedCheckin = "2025-12-01";
        updatedCheckout = "2025-12-05";

        String payload = BookingPayloadBuilder.build(
                updatedRoomId,
                updatedFirstname,
                updatedLastname,
                false,
                updatedCheckin,
                updatedCheckout,
                "john.doe@example.com",
                "11999999999"

        );

        response = given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .accept(JSON)
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .body(payload)
                .when()
                .put("/booking/{id}")
                .then()
                .extract()
                .response();

        ApiResponseHelper.setLastResponse(response);
    }

    @And("I update the booking with new data without authentication")
    public void iUpdateTheBookingWithNewDataWithoutAuthentication() {
        assertNotNull(bookingId, "bookingId not set");

        // reuse same updated values (or just set them here again)
        int anyRoomId = new Random().nextInt(100) + 1;

        String payload = BookingPayloadBuilder.build(
                anyRoomId,
                "Jane",
                "Smith",
                false,
                "2025-12-01",
                "2025-12-05",
                "jane.smith@example.com",
                "11888888888"
        );

        response = given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .accept(JSON)
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
        assertEquals(updatedFirstname, response.jsonPath().getString("firstname"));
        assertEquals(updatedLastname, response.jsonPath().getString("lastname"));
        assertEquals(updatedRoomId, response.jsonPath().getInt("roomid"));
        assertEquals(updatedCheckin, response.jsonPath().getString("bookingdates.checkin"));
        assertEquals(updatedCheckout, response.jsonPath().getString("bookingdates.checkout"));
    }


}
