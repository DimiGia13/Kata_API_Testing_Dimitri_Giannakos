package com.booking.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.greaterThan;

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


    @And("the response should contain at least one booking")
    public void theResponseShouldContainAtLeastOneBooking() {
        response.then().body("size()", greaterThan(0));
    }
    //endregion



}
