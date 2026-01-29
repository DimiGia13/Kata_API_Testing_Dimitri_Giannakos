package com.booking.builder;

public class BookingPayloadBuilder {

    private BookingPayloadBuilder() {
    }

    public static String build(
            int roomId,
            String firstname,
            String lastname,
            boolean depositPaid,
            String checkin,
            String checkout,
            String email,
            String phone
    ) {
        return """
    {
      "roomid": %d,
      "firstname": "%s",
      "lastname": "%s",
      "depositpaid": %s,
      "bookingdates": {
        "checkin": "%s",
        "checkout": "%s"
      },
      "email": "%s",
      "phone": "%s"
    }
    """.formatted(roomId, firstname, lastname, depositPaid, checkin, checkout, email, phone);
    }
}
