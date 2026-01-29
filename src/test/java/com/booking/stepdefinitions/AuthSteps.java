package com.booking.stepdefinitions;

import com.booking.ApiResponseHelper;
import com.booking.helper.ApiScenarioHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class AuthSteps {

    private static final String BASE_URL = "https://automationintesting.online/api";
    private Response response;
    private String token;

    private String username;
    private String password;

    @Given("I have valid admin credentials")
    public void iHaveValidAdminCredentials() {
        username = "admin";
        password = "password";
    }

    @Given("I have invalid credentials")
    public void iHaveInvalidCredentials() {
        username = "admin";
        password = "wrong";
    }

    @When("I request an auth token")
    public void iRequestAnAuthToken() {
        response = RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .body("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
                .when()
                .post("/auth/login");

        ApiResponseHelper.setLastResponse(response);



        if (response.statusCode() == 200) {
            token = response.jsonPath().getString("token");
            ApiScenarioHelper.setToken(token);
        }
    }


    @And("the response should contain a token")
    public void theResponseShouldContainAToken() {
        Assertions.assertNotNull(token, "Token should not be null");
        Assertions.assertFalse(token.isBlank(), "Token should not be blank");
    }

    @Then("the error message should be {string}")
    public void theErrorMessageShouldBe(String expected) {
        String error = response.jsonPath().getString("error");
        Assertions.assertEquals(expected, error);
    }
}
