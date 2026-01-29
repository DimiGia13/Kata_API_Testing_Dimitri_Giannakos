Feature: Get all Bookings


  Scenario: View all the bookings
    Given the Booking API is available
    When I send a GET request to /booking
    Then the response status code should be 200
    And the response should contain at least one booking


