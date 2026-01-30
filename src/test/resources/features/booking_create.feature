Feature: Booking creation


  @happy
  Scenario: Successfully create a booking
    When I create a booking with valid data
    Then the response status should be 201
    And the response should contain a booking id

  @negative @validation
  Scenario: Create booking fails when firstname is too short
    When I create a booking with an invalid firstname
    Then the response status should be 400
    And the response errors should contain "size must be between 3 and 18"

  @negative @validation
  Scenario: Create booking fails when lastname is too short
    When I create a booking with an invalid lastname
    Then the response status should be 400
    And the response errors should contain "size must be between 3 and 30"

  @negative @validation
  Scenario: Create booking fails when email is invalid
    When I create a booking with an invalid email
    Then the response status should be 400
    And the response errors should contain "must be a well-formed email address"

  @negative @validation
  Scenario: Create booking fails when Phone number is invalid
    When I create a booking with an invalid Phone number
    Then the response status should be 400
    And the response errors should contain "size must be between 11 and 21"

  @negative @validation @specDeviation
  Scenario: Create booking fails when checkout before checkin
    When I create a booking with checkout before checkin
    Then the response status should be 409
    And the response errors should contain "Failed to create booking"

  @negative @validation
  Scenario: Create booking fails with a bad date format
    When I create a booking with a bad date format
    Then the response status should be 400
    And the response errors should contain "Failed to create booking"