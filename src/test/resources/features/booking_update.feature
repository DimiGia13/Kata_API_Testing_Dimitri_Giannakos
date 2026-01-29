Feature: Update booking (PUT)

  Scenario: Update booking with valid token
    When I authenticate as admin
    And I create a booking
    And I update the booking with new data
    Then the response status should be 200
    And the updated booking should reflect the new data

  Scenario: Update booking without token returns 401
    When I create a booking
    And I update the booking with new data without authentication
    Then the response status should be 401
