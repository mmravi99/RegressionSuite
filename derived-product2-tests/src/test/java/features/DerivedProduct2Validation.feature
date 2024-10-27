Feature: Verify Footer Links on DP2 Home Page

  Scenario: Find and Store Footer Links and Check for Duplicates
    Given I open application
    Then I store all hyperlinks of the footer links into a CSV file
    And I report if any duplicate hyperlinks are present in the footer
    Then I close the application