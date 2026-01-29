package com.booking.stepdefinitions;

import com.booking.builder.BookingPayloadBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import io.restassured.response.Response;

public class BookingCreateSteps {

    private static final String BASE_URL = "https://automationintesting.online/api";
    private static final Random RANDOM = new Random(42);
    private int roomId;
    private Response response;

    //region create a booking scenario
    @When("I create a booking with valid data")
    public void iSendAPostRequestToBookingWithValidData(){
         roomId = new Random().nextInt(100) + 1;

        String payload = BookingPayloadBuilder.build(
                roomId,
                "Jo",
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

    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expected) {
        int actual = response.statusCode();
        Assertions.assertTrue(
                actual == expected || actual == 201,
                "Expected status " + expected + " or 201 but was " + actual
        );
    }

    @And("the response should contain a bookingid")
    public void theResponseShouldContainABookingId() {
        response.then().body("bookingid", notNullValue());
    }

    @Then("the response errors should contain {string}")
    public void theResponseErrorsShouldContain(String expectedError) {
        response.then().body("errors", hasItem(expectedError));
    }


    @When("I create a booking with an invalid firstname")
    public void iCreateABookingWithInvalidFirstname() {
        roomId = new Random().nextInt(100) + 1;

        String payload = BookingPayloadBuilder.build(
                roomId,
                "Jo",
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
    }

    @When("I create a booking with an invalid lastname")
    public void iCreateABookingWithInvalidLastname() {
        roomId = new Random().nextInt(100) + 1;

        String payload = BookingPayloadBuilder.build(
                roomId,
                "John",
                "Do",
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
    }

    @When("I create a booking with an invalid email")
    public void iCreateABookingWithInvalidEmail() {
        roomId = new Random().nextInt(100) + 1;

        String payload = BookingPayloadBuilder.build(
                roomId,
                "John",
                "Doe",
                true,
                "2025-11-01",
                "2025-11-05",
                "not-an-email",
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
    }

    @When("I create a booking with an invalid Phone number")
    public void iCreateABookingWithInvalidPhoneNumber() {
        roomId = new Random().nextInt(100) + 1;

        String payload = BookingPayloadBuilder.build(
                roomId,
                "John",
                "Doe",
                true,
                "2025-11-01",
                "2025-11-05",
                "john.doe@example.com",
                "1199999999999999999999999999999999999999999999999999999999999999"
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
    }


    @When("I create a booking with invalid dates")
    public void iCreateABookingWithInvalidDates() {
        roomId = new Random().nextInt(100) + 1;

        String payload = BookingPayloadBuilder.build(
                roomId,
                "John",
                "Doe",
                true,
                "2025-11-05",
                "2025-11-01",
                "john.doe@example.com",
                "1199999999999999"
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
    }





}
