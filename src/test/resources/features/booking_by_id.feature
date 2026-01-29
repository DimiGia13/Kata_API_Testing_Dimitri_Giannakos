Feature: Get booking by id

  Scenario: Retrieve booking details with valid token
    When I authenticate as admin
    And I create a booking
    And I retrieve the booking by id
    Then the response status should be 200
    And the booking details should match the created booking

  @specDeviation
  Scenario: Retrieve booking details without token returns 401 (spec deviation)
    When I create a booking
    And I retrieve the booking by id without authentication
    Then the response status should be 200
