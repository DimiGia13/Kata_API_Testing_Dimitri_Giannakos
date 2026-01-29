package com.booking.helper;

public class ApiScenarioHelper {
    private ApiScenarioHelper(){}

    private static String token;
    private static Integer bookingId;
    private static String firstname;
    private static String lastname;
    private static Integer roomId;
    private static Boolean depositPaid;
    private static String checkin;
    private static String checkout;

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

    public static void setBookingData(Integer roomId, String firstname, String lastname,
                                      Boolean depositPaid, String checkin, String checkout) {
        ApiScenarioHelper.roomId = roomId;
        ApiScenarioHelper.firstname = firstname;
        ApiScenarioHelper.lastname = lastname;
        ApiScenarioHelper.depositPaid = depositPaid;
        ApiScenarioHelper.checkin = checkin;
        ApiScenarioHelper.checkout = checkout;
    }

    public static Integer getRoomId() { return roomId; }
    public static String getFirstname() { return firstname; }
    public static String getLastname() { return lastname; }
    public static Boolean getDepositPaid() { return depositPaid; }
    public static String getCheckin() { return checkin; }
    public static String getCheckout() { return checkout; }

}
