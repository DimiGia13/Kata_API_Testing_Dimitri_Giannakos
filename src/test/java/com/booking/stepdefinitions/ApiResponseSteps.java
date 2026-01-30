package com.booking.stepdefinitions;

import com.booking.helper.ApiResponseHelper;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiResponseSteps {
    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        var response = ApiResponseHelper.getLastResponse();
        assertEquals(statusCode, response.statusCode(), response.asString());
    }
}
