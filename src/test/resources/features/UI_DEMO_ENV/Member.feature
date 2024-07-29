Feature: Create New Member and search feature

  @E2E @ECUBEUI @TestCase-M001
  Scenario: Verify Member Creation functionality
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member" from side menu
    And User clicks on plus icon
    And User generate and fill the data into the General Tab In Member Module
    And User generate and fill the data into the Personal Tab In Member Module
    And User generate and fill the data into the Contact Tab In Member Module
    And User generate and fill the data into the Sub Member Tab In Member Module
    And User generate and fill the data into the Membership Tab In Member Module
    And User generate and enter Member code
    And User Clicks on Save button

  @E2E @ECUBEUI @TestCase-M0022
  Scenario: Verify Member Creation functionality
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member" from side menu
    And User clicks on plus icon
    And User generate and fill the data into the General Tab In Member Module
    And User generate and fill the data into the Personal Tab In Member Module
    And User generate and fill the data into the Contact Tab In Member Module
    And User generate and fill the data into the Sub Member Tab In Member Module
    And User generate and fill the data into the Membership Tab In Member Module
    And User generate and enter Member code
    And User Clicks on Save button

  @E2E @ECUBEUI @TestCase-M002
  Scenario: Verify search functionality with Membership Category
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member" from side menu
    And User click on search icon
    Then User search with Membership Category and Validate the search result in "Category" column in member table

  @E2E @ECUBEUI @TestCase-M003
  Scenario: Verify search functionality with Membership ID
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member" from side menu
    And User click on search icon
    Then User search with Membership ID and Validate the search result in "Membership" column in member table

  @E2E @ECUBEUI @TestCase-M004
  Scenario: Verify search functionality with Member Code/Name
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member" from side menu
    And User click on search icon
    And User enter Member Name as below
      | memberName  |
      | KALE RUPALI |
    Then User Validate the search result of Member Name in "Member Name" column in member table

  @E2E @ECUBEUI @TestCase-M005
  Scenario: Verify search functionality with Corporate Name
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member" from side menu
    And User click on search icon
    And User enter Corporate code
      | corporateCode                |
      | INDIA INTERNATIONAL EXCHANGE |
    Then User Validate the search result of Corporate code in "Corporate Name" column in member table

  @E2E @ECUBEUI @TestCase-M006
  Scenario: Verify search functionality with Corporate Name
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member" from side menu
    And User click on search icon
    And User enter Reference code
      | referenceCode |
      | MIRANI GUNJAN |
    Then User Validate the search result of Reference code in "Reference" column in member table

  @E2E @ECUBEUI @TestCase-M007
  Scenario: Verify Refresh button functionality within Member Search page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Member" from side menu
    And User click on search icon
    And User enter Member Name as below
      | memberName  |
      | KALE RUPALI |
    And User enter Reference code
      | referenceCode |
      | MIRANI GUNJAN |
    When User clicks on the Refresh button
    Then The search fields should be cleared and the default state should be restored