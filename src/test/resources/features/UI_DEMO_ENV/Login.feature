Feature: Test Login feature

  @E2E @ECUBEUI @TestCase-ROO01
  Scenario: Verify login functionality
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User successfully logged in


