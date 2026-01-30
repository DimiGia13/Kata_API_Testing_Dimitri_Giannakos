package com.booking.api.config;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public abstract class BaseApiConfig {
    private static final String BASE_URL = "https://automationintesting.online/api";

    protected RequestSpecification baseRequest() {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .accept(JSON);
    }
}
