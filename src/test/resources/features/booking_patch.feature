Feature: Partially update booking

  @happy @auth @specDeviation
  Scenario: Partially update booking with valid patch data
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    And I create a booking with valid data
    And I partially update the booking with valid patch data
    Then the response status should be 405

  @negative @noAuth @specDeviation
  Scenario: Partially update booking without token returns 401
    Given I create a booking with valid data
    When I partially update the booking without authentication
    Then the response status should be 405