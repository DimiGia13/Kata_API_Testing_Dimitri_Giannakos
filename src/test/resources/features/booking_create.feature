Feature: Booking creation


  Scenario: Successfully create a booking
    When I create a booking with valid data
    Then the response status should be 200
    And the response should contain a bookingid

  Scenario: Create booking fails when firstname is too short
    When I create a booking with an invalid firstname
    Then the response status should be 400
    And the response errors should contain "size must be between 3 and 18"

  Scenario: Create booking fails when lastname is too short
    When I create a booking with an invalid lastname
    Then the response status should be 400
    And the response errors should contain "size must be between 3 and 18"

  Scenario: Create booking fails when email is invalid
    When I create a booking with an invalid email
    Then the response status should be 400
    And the response errors should contain "must be a well-formed email address"

  Scenario: Create booking fails when Phone number is invalid
    When I create a booking with an invalid Phone number
    Then the response status should be 400
    And the response errors should contain "size must be between 11 and 21"

  Scenario: Create booking fails when dates are invalid
    When I create a booking with invalid dates
    Then the response status should be 400
    And the response errors should contain "Failed to create booking"