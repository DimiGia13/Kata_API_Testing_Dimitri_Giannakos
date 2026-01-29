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

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingSteps {

    private Response response;


    @Given("the Booking API is available")
    public void theBookingAPIIsAvailable(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    //region GET all bookings scenario
    @When("^I send a GET request to /booking$")
    public void iSendAGetRequestToBooking() {
        response = when().get("/booking");
    }

    @Then("the response status code should be 200")
    public void theResponseStatusCodeShouldBe200(){
        response.then().statusCode(200);
    }

    @And("the response should contain at least one booking")
    public void theResponseShouldContainAtLeastOneBooking() {
        response.then().body("size()", greaterThan(0));
    }
    //endregion

    //region GET a booking by id scenario

}
