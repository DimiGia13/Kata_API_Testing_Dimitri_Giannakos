package com.booking;

import io.restassured.response.Response;

public class ApiResponseHelper {
    private static Response lastResponse;

    public static Response getLastResponse() {
        return lastResponse;
    }

    public static void setLastResponse(Response response) {
        lastResponse = response;
    }

}
