Feature: Test Smart search feature

  @E2E @ECUBEUI @TestCase-SS01
  Scenario:Verify search with Member Code on the Smart Search page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Smart Search" from side menu
    And User enter Member code to search the details
      | memberCode |
      | 0006/1251  |
    Then User validate the search result in "Member Code" column

  @E2E @ECUBEUI @TestCase-SS02
  Scenario:Verify search with Birth Date on the Smart Search page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Smart Search" from side menu
    And User select Birth Date From and To
      | BirthDateFromMonth | BirthDateFromDate | BirthDateToMonth | BirthDateToDate |
      | December           | 1                 | December         | 5               |
    And User clicks on the search button
    Then User validates the search results as per the selected Birth Date in the "Birth Date" column

  @E2E @ECUBEUI @TestCase-SS03
  Scenario:Verify Export Report to PDF on the Smart Search page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Smart Search" from side menu
    And User select Birth Date From and To
      | BirthDateFromMonth | BirthDateFromDate | BirthDateToMonth | BirthDateToDate |
      | December           | 1                 | December         | 2               |
    And User clicks on the search button
    And User clicks on PDF icon to export report in PDF
    Then PDF report generated successfully

  @E2E @ECUBEUI @TestCase-SS04
  Scenario:Verify Export Report to PDF on the Smart Search page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Smart Search" from side menu
    And User select City to search the details
      | city  |
      | ANAND |
    And User clicks on the search button
    Then User validates the search result as per the selected "City" column

  @E2E @ECUBEUI @TestCase-SS05
  Scenario Outline:Verify search with Birth Date on the Smart Search page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Smart Search" from side menu
    And User select "<AdvanceFilterOption>" from Advance filter
    And User clicks on the search button
    Then User validate the search result
    Examples:
      | AdvanceFilterOption |
      | Without Email Id    |
      | Without Mobile No   |
      | Without PAN No      |
      | Hide SubMembers     |

  @TestCase-SS07
  Scenario Outline:Verify search with Birth Date on the Smart Search page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Smart Search" from side menu
    And User select "<AdvanceFilterOption>" from Advance filter
    And User clicks on the search button
    Then User validate the search result
    Examples:
      | AdvanceFilterOption |
      | Hide Members        |
      | Age Search          |
      | Payment             |

  @E2E @ECUBEUI @TestCase-SS06
  Scenario Outline:Verify search with Birth Date on the Smart Search page
    Given User open Ecube login page
    When User Login with valid credentials
    Then User should be redirected to the home page
    And User selects "Smart Search" from side menu
    And User select "<AdvanceFilterOption>" from Advance filter
    And User enter age limit
      | age |
      | 18  |
    And User clicks on the search button
    Then User validate the search result
    Examples:
      | AdvanceFilterOption |
      | Age Search          |
