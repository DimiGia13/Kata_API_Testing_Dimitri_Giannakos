Feature: Get booking by id

  Scenario: Retrieve booking details with valid token
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    And I create a booking with valid data
    And I retrieve the booking by id
    Then the response status should be 200
    And the booking details should match the created booking


  Scenario: Retrieve booking details without token
    Given I create a booking with valid data
    When I retrieve the booking by id without authentication
    Then the response status should be 401

  @SpecDeviation
  Scenario Outline: Retrieve booking details with invalid id format
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    And I retrieve the booking with id "<id>"
    Then the response status should be 400

    Examples:
      | id   |
      | abc  |
      | -1   |
      | 1.5  |
