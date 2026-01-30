Feature: Get booking by id

  Scenario: Retrieve booking details with valid token
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    And I create a booking with valid data
    And I retrieve the booking by id
    Then the response status should be 200
    And the booking details should match the created booking


  Scenario: Retrieve booking details without token returns 401
    Given I create a booking with valid data
    When I retrieve the booking by id without authentication
    Then the response status should be 401
