Feature: Delete booking

  @happy @auth
  Scenario: Delete booking with valid token
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    And I create a booking with valid data
    And I delete the booking
    Then the response status should be 200

  @negative @noAuth
  Scenario: Delete booking without token
    Given I create a booking with valid data
    When I delete the booking without authentication
    Then the response status should be 401

  @negative @specDeviation
  Scenario: Delete non-existing booking
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    And I have a non-existing booking id
    And I delete the booking
    Then the response status should be 500

  @negative @auth @validation
  Scenario: Delete a booking with a negative id
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    And I have a negative booking id
    And I delete the booking
    Then the response status should be 500
