package com.booking.stepdefinitions;

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

public class BookingById {
    private static final String BASE_URL = "https://automationintesting.online/api";

    private Response response;

    private String token;
    private Integer bookingId;

    // expected booking data
    private int roomId;
    private String firstname;
    private String lastname;
    private boolean depositPaid;
    private String checkin;
    private String checkout;
    private String email;
    private String phone;

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
                firstname = "John",
                lastname ="Doe",
                depositPaid = true,
                checkin = "2025-11-01",
                checkout = "2025-11-05",
                email = "john.doe@example.com",
                phone = "11999999999"
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

    // ---------- GET BOOKING BY ID ----------

    @And("I retrieve the booking by id")
    public void iRetrieveTheBookingById() {
        assertNotNull(token, "Token not set");
        assertNotNull(bookingId, "bookingId not set");

        response = given()
                .baseUri(BASE_URL)
                .accept(JSON)
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .extract()
                .response();
    }

    @And("I retrieve the booking by id without authentication")
    public void iRetrieveTheBookingByIdWithoutAuthentication() {
        assertNotNull(bookingId, "bookingId not set");

        response = given()
                .baseUri(BASE_URL)
                .accept(JSON)
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .extract()
                .response();
    }

    @Then("the booking details should match the created booking")
    public void theBookingDetailsShouldMatchTheCreatedBooking() {
        assertEquals(firstname, response.jsonPath().getString("firstname"));
        assertEquals(lastname, response.jsonPath().getString("lastname"));
        assertEquals(roomId, response.jsonPath().getInt("roomid"));
        assertEquals(depositPaid, response.jsonPath().getBoolean("depositpaid"));
        assertEquals(checkin, response.jsonPath().getString("bookingdates.checkin"));
        assertEquals(checkout, response.jsonPath().getString("bookingdates.checkout"));
        assertEquals(email, response.jsonPath().getString("email"));
        assertEquals(phone, response.jsonPath().getString("phone"));
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int code) {
        assertEquals(code, response.statusCode(), response.asString());
    }

}
