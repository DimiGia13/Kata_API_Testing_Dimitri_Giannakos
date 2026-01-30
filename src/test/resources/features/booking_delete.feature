Feature: Delete booking

  Scenario: Delete booking with valid token
    Given I have valid admin credentials
    When I request an auth token
    And I create a booking with valid data
    And I delete the booking
    Then the response status should be 200

  @specDeviation
  Scenario: Delete booking without token returns 401
    And I create a booking with valid data
    When I delete the booking without authentication
    Then the response status should be 401

  @delete @negative
  Scenario: Delete non-existing booking
    Given I have valid admin credentials
    When I request an auth token
    And I have a non-existing booking id
    And I delete the booking
    Then the response status should be 500
