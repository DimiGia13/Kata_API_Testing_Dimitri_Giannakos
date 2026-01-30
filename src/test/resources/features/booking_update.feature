Feature: Update booking (PUT)

  Scenario: Update booking with valid token
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    And I create a booking with valid data
    And I update the booking with new data
    Then the response status should be 200
    And the updated booking should reflect the new data

  Scenario: Update booking without token
    Given I create a booking with valid data
    And I update the booking with new data without authentication
    Then the response status should be 401

  Scenario: Update booking with invalid firstname
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    And I create a booking with valid data
    And I update the booking with an invalid firstname
    Then the response status should be 400
