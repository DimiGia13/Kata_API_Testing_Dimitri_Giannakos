package com.booking.helper;

public class ApiScenarioHelper {
    private ApiScenarioHelper(){}

    private static String token;
    private static Integer bookingId;

    public static String getToken(){
        return token;
    }

    public static void setToken(String token) {
        ApiScenarioHelper.token = token;
    }

    public static Integer getBookingId() {
        return bookingId;
    }

    public static void setBookingId(Integer bookingId) {
        ApiScenarioHelper.bookingId = bookingId;
    }

    public static void clear() {
        token = null;
        bookingId = null;
    }
}
