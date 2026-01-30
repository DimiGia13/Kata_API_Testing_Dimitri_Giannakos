Feature: Authentication

  @auth @happy
  Scenario: Login returns token for valid credentials
    Given I have login data with username "admin" and password "password"
    When I request an auth token
    Then the response status should be 200
    And the response should contain a token

  @auth @negative
  Scenario Outline: Login fails for invalid input
    Given I have login data with username "<username>" and password "<password>"
    When I request an auth token
    Then the response status should be <status>
    And the error message should be "<message>"

    Examples:
      | username | password  | status | message             |
      | wrong    | password  | 401    | Invalid credentials |
      | admin    | wrong     | 401    | Invalid credentials |
      |          | password  | 401    | Invalid credentials |
      | admin    |           | 401    | Invalid credentials |
      |          |           | 401    | Invalid credentials |