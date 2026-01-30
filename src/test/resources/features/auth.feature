Feature: Authentication

  Scenario: Login returns token for valid credentials
    Given I have valid admin credentials
    When I request an auth token
    Then the response status should be 200
    And the response should contain a token

  Scenario: Login fails with invalid credentials
    Given I have invalid credentials
    When I request an auth token
    Then the response status should be 401
    And the error message should be "Invalid credentials"

  Scenario: Login fails without username
    Given I have login data without a username
    When I request an auth token
    Then the response status should be 401
    And the error message should be "Invalid credentials"