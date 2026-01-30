package com.booking.dto;

public record BookingRequest (
    int roomid,
    String firstname,
    String lastname,
    boolean depositpaid,
    BookingDates bookingdates,
    String email,
    String phone
) {}
