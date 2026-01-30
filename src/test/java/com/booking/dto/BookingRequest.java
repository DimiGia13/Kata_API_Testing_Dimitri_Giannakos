package com.booking.dto;

public record BookingRequest (
    int roomid,
    String firstname,
    String lastname,
    Boolean depositpaid,
    BookingDates bookingdates,
    String email,
    String phone
) {}
