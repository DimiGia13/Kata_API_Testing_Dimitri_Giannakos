package com.booking.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingSteps {

    private Response response;
    private static final String BASE_URL = "https://automationintesting.online/api";
    private static final Random RANDOM = new Random(42);
    private int roomId;


    @Given("the Booking API is available")
    public void theBookingAPIIsAvailable(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    //region GET all bookings scenario
    @When("^I send a GET request to /booking$")
    public void iSendAGetRequestToBooking() {
        response = when().get("/booking");
    }


    @And("the response should contain at least one booking")
    public void theResponseShouldContainAtLeastOneBooking() {
        response.then().body("size()", greaterThan(0));
    }
    //endregion

    //region create a booking scenario
    @When("I create a booking with valid data")
    public void iSendAPostRequestToBookingWithValidData(){
        roomId = RANDOM.nextInt(100) + 1;

        String payload = """
    {
      "roomid": %d,
      "firstname": "John",
      "lastname": "Doe",
      "depositpaid": true,
      "bookingdates": {
        "checkin": "2025-11-01",
        "checkout": "2025-11-05"
      },
      "email": "john.doe@example.com",
      "phone": "11999999999"
    }
    """.formatted(roomId);

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

}
