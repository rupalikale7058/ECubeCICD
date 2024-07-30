Feature: Test Member Receipt_Service feature

  @E2E @ECUBEUI @TestCase-ROO009 @REG
  Scenario: Verify Member Receipt generated functionality for Add and Save the below details
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode            |
      | 0/9988 (KALE RUPALI ) |
    And User selects Payment Type
      | PaymentType       |
      | Cheque (Yes Bank) |
    And User enter cheque No
      | chequeNo     |
      | 987666664222 |
    And User enter bank details
      | Branch | BankInfo |
      | UK     | SBI Bank |
    And User click on Save button
    Then Member Receipt generated successfully
    And User validate the Receipt details

  @E2E @ECUBEUI @TestCase-ROO006 @REG
  Scenario: Verify Member Receipt generated functionality for Payment type and credit card
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                  |
      | 0003/1119 (MUKHERJEE ARUP ) |
    And User selects Payment Type
      | PaymentType            |
      | Credit Card (Yes Bank) |
    And User enter credit card details
      | CreditCardNo |
      | 98764562345  |
    And User enter Card Holder Name
      | CardHolderName |
      | JON            |
    And User click on Save button
    Then Member Receipt generated successfully
    And User validate the Receipt details when payment type is credit card

  @E2E @ECUBEUI @TestCase-ROO029 @REG
  Scenario: Verify Member Receipt generated functionality for Payment type Cash
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode               |
      | 0003/1120 (JAIN JAPESH ) |
    And User selects Payment Type
      | PaymentType |
      | Cash (Cash) |
    And User enter reference number
      | ReferenceNo |
      | 89999       |
    And User click on Save button

  @E2E @ECUBEUI @TestCase-ROO0091 @REG
  Scenario: Verify Member Receipt generated functionality for adding and Save the below details
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                  |
      | 0006/1251 (UNADKAT MUKUND ) |
    And Check total amount and available credit after adding advance amount
      | advanceAmount |
      | 1000          |
    And User enter description
    And User selects Payment Type
      | PaymentType       |
      | Cheque (Yes Bank) |
    And User enter cheque No
      | chequeNo     |
      | 458785784000 |
    And User enter bank details
      | Branch | BankInfo |
      | UK     | SBI Bank |
    And User click on Save button
    Then Member Receipt generated successfully
    And User validate the Receipt details

  @E2E @ECUBEUI @TestCase-ROO03
  Scenario: Verify Member Receipt generated functionality for Payment type Cheque
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                  |
      | 0003/1119 (MUKHERJEE ARUP ) |
    And User selects Payment Type
      | PaymentType       |
      | Cheque (Yes Bank) |
    And User click on Save button

  @E2E @ECUBEUI @TestCase-ROO04
  Scenario: Verify Member Receipt generated functionality for the Payment type Net Banking
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                |
      | 0005/1249 (PATEL VIKRAM ) |
    And User selects Payment Type
      | PaymentType            |
      | Net Banking (Yes Bank) |
    And User click on Save button

  @E2E @ECUBEUI @TestCase-ROO005
  Scenario: Verify Check total amount and available credit after adding advance amount in Member details
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                |
      | 0005/1249 (PATEL VIKRAM ) |
    And Check total amount and available credit after adding advance amount
      | advanceAmount |
      | 1000          |
    And User selects Payment Type
      | PaymentType       |
      | Cheque (Yes Bank) |
    And User enter cheque No
      | chequeNo     |
      | 458785784758 |
    And User enter bank details
      | Branch | BankInfo |
      | UK     | SBI Bank |
    And User click on Save button
    Then Member Receipt generated successfully
    And User validate the Receipt details

  @E2E @ECUBEUI @TestCase-ROO11
  Scenario: Verify Cancel button functionality on Member details page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode               |
      | 0003/1120 (JAIN JAPESH ) |
    And User selects Payment Type
      | PaymentType |
      | Cash (Cash) |
    And User click on Cancel Button

  @E2E @ECUBEUI @TestCase-ROO15
  Scenario: Verify search with Receipt No. functionality on Member receipt page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User click on search icon
    And User search with Receipt number
      | ReceiptNo |
      | 25915     |
    Then User Validate the search result in "Receipt No." column

  @E2E @ECUBEUI @TestCase-ROO096
  Scenario: Verify search with Member Code functionality on Member receipt page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User click on search icon
    And User search with Member Code
      | MemberCode                  |
      | 0003/1119 (MUKHERJEE ARUP ) |
    Then User Validate the search results in "Member Code" column

  @E2E @ECUBEUI @TestCase-ROO18
  Scenario: Verify search with Member Name functionality on Member receipt page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User click on search icon
    And User search with Member Name
      | MemberName                 |
      | ACHARYA HARSHAD  (KCS1318) |
    Then User Validate the search result of Member Name in "Member Name" column

  @E2E @ECUBEUI @TestCase-ROO0190
  Scenario: Verify search with Date functionality on Member receipt page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User click on search icon
    And User search with Date
      | PaymentDate |
      | 27/06/2024  |
    Then User Validate the search result of Date in "Payment Date" column

  @E2E @ECUBEUI @TestCase-ROO020
  Scenario: Verify Print functionality on Member receipt page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User select record to print
    And User click on Print icon

  @E2E @ECUBEUI @TestCase-ROO22
  Scenario: Verify Delete records functionality on Member receipt page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User click on search icon
    And User search with Date
      | PaymentDate |
      | 29/07/2024  |
    And User selects records to delete
    And User click on Delete icon
    Then User confirm delete records

  @E2E @ECUBEUI @TestCase-ROO23
  Scenario: Verify Member Receipt-Registration generated functionality for Add and Save the below details
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode             |
      | 0008/1374 (RAY TAPAN ) |
    And User select Registration option
    And User selects Payment Type
      | PaymentType       |
      | Cheque (Yes Bank) |
    And User enter cheque No
      | chequeNo     |
      | 987666664222 |
    And User enter bank details
      | Branch | BankInfo |
      | UK     | SBI Bank |
    And User click on Save button
    Then Member Receipt generated successfully

  @E2E @ECUBEUI @TestCase-RD00098
  Scenario:Verify description is successfully added and character limit in description box
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode                  |
      | 0003/1119 (MUKHERJEE ARUP ) |
    And User selects Payment Type
      | PaymentType            |
      | Credit Card (Yes Bank) |
    And User enter description
    And User enter credit card details
      | CreditCardNo |
      | 98764562345  |
    And User enter Card Holder Name
      | CardHolderName |
      | JON            |
    And User click on Save button
    Then Member Receipt generated successfully








