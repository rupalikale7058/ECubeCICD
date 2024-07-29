Feature: Test Report feature

  @E2E @ECUBEUI @TestCase-ROO201
  Scenario: Verify Total Outstanding Amount on Member Details Page is matching in reports
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User capture the Member code details from member receipt
    And User navigate to member details page
    And User capture the Total OutStanding amount from member details page
    And User Click on Back Button
    And User selects "Report" from side menu
    And User Select Finances and Invoice option from the Report
    And User click on Member statement option
    And User enter Member code
    And User click on Predicted button
    And User navigated to receipt details page
    Then User check the total balance amount is equal with Total Outstanding amount

  @E2E @ECUBEUI @TestCase-RO02020
  Scenario: Verify Balance amount is displayed properly in receipt details page in Report module
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User capture the Member code details from member receipt
    And User selects "Report" from side menu
    And User Select Finances and Invoice option from the Report
    And User click on Member statement option
    And User enter Member code
    And User click on Go button
    And User navigated to receipt details page
    Then User validate Balance amount is displayed properly

