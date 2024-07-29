Feature: Test Member Receipt_Registration feature

  @E2E @ECUBEUI @TestCase-ROO025
  Scenario Outline: Verify Member Receipt generated functionality for adding details in Registration page and Save the below details
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode               |
      | 0003/1123 (JAIN MAYANK ) |
    And User Navigate to Registration option
    And User select "<Tax>" from list
    And User enter receipt amount
      | receiptAmount |
      | 1000          |
    And User selects Payment Type in Registration option
      | PaymentType       |
      | Cheque (Yes Bank) |
    And User enter cheque No in Registration option
      | chequeNo     |
      | 458785785000 |
    And User enter bank details in Registration option
      | Branch | BankInfo |
      | UK     | SBI Bank |
    And User click on Save button
    Then Member Receipt generated successfully
    And User validate the Receipt details
    Examples:
      | Tax     |
      | GST 1   |
      | GST 12% |
      | GST 15% |
      | GST 18% |
      | GST 28% |

  @E2E @ECUBEUI @TestCase-ROO0087
  Scenario: Verify Cancel button functionality on Member details page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member Receipt" from side menu
    And User clicks on plus icon
    And User enters Member Code
      | MemberCode               |
      | 0003/1123 (JAIN MAYANK ) |
    And User Navigate to Registration option
    And User selects Payment Type in Registration option
      | PaymentType       |
      | Cheque (Yes Bank) |
    And User enter cheque No in Registration option
      | chequeNo     |
      | 458785785000 |
    And User enter bank details in Registration option
      | Branch | BankInfo |
      | UK     | SBI Bank |
    And User click on Cancel Button