package com.booking.builder;

import com.booking.dto.BookingDates;
import com.booking.dto.BookingRequest;

public class BookingPayloadBuilder {

    private BookingPayloadBuilder() {
    }

    public static BookingRequest build(
            int roomId,
            String firstname,
            String lastname,
            boolean depositPaid,
            String checkin,
            String checkout,
            String email,
            String phone
    ) {
       return new BookingRequest(
               roomId,
               firstname,
               lastname,
               depositPaid,
               new BookingDates(checkin, checkout),
               email,
               phone
       );
    }
}
