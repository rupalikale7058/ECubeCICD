Feature: Test Member Receipt_Package feature

  @E2E @ECUBEUI @TestCase-RP001
  Scenario: Verify Member Receipt generated functionality after applying Package
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Package Subscription" from side menu
    And User select package type
    And User click on add option
    And User enter the Member Name
      | MemberName                |
      | 0005/1249 (PATEL VIKRAM ) |
    And User select MemberName checkBox
    And User click on save button
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                |
      | 0005/1249 (PATEL VIKRAM ) |
    And User select package option
    And User select package type from list
      | packageType |
      | Badmin Gym  |
    And User selects Payment Type in Package type
      | PaymentType            |
      | Credit Card (Yes Bank) |
    And User enter below credit card details
      | CreditCardNo |
      | 98764562345  |
    And User click on Save button
    Then Member Receipt generated successfully

  @E2E @ECUBEUI @TestCase-RP001
  Scenario: Verify Member Receipt generated functionality after applying Package and adding below details
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Package Subscription" from side menu
    And User select package type
    And User click on add option
    And User enter the Member Name
      | MemberName                |
      | 0005/1249 (PATEL VIKRAM ) |
    And User select MemberName checkBox
    And User click on save button
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                |
      | 0005/1249 (PATEL VIKRAM ) |
    And User select package option
    And User select package type from list
      | packageType |
      | Badmin Gym  |
    And User selects Payment Type in Package type
      | PaymentType            |
      | Credit Card (Yes Bank) |
    And User enter below credit card details
      | CreditCardNo |
      | 98764562345  |
    And User click on Save button
    Then Member Receipt generated successfully

  @E2E @ECUBEUI @TestCase-RP002
  Scenario: Verify Member Receipt generated functionality after applying Package and validate the receipt details
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Package Subscription" from side menu
    And User select package type
    And User click on add option
    And User enter the Member Name
      | MemberName                |
      | 0005/1249 (PATEL VIKRAM ) |
    And User select MemberName checkBox
    And User click on save button
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                |
      | 0005/1249 (PATEL VIKRAM ) |
    And User select package option
    And User select package type from list
      | packageType |
      | Badmin Gym  |
    And User selects Payment Type in Package type
      | PaymentType            |
      | Credit Card (Yes Bank) |
    And User enter below credit card details
      | CreditCardNo |
      | 98764562345  |
    And User click on Save button
    Then Member Receipt generated successfully
    And User validates the Receipt details

  @E2E @ECUBEUI @TestCase-RP003
  Scenario: Verify Member Receipt generated functionality after applying Package and validate the receipt details for Payment type Cash
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Package Subscription" from side menu
    And User select package type
    And User click on add option
    And User enter the Member Name
      | MemberName                |
      | 0005/1249 (PATEL VIKRAM ) |
    And User select MemberName checkBox
    And User click on save button
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                |
      | 0005/1249 (PATEL VIKRAM ) |
    And User select package option
    And User select package type from list
      | packageType |
      | Badmin Gym  |
    And User selects Payment Type in Package type
      | PaymentType |
      | Cash (Cash) |
    And User enter bank details in Package
      | Branch | BankInfo |
      | UK     | SBI Bank |
    And User click on Save button
    Then Member Receipt generated successfully
    And User validates the Receipt details