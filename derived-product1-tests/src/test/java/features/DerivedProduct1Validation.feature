Feature: Validate Derived Product1 functionality
  
  Scenario: Validate Slide Titles and Durations Under Tickets Menu on DP1 Home Page
    Given I open application
    Then I retrieved slide under tickets menu
    Then I attached details to the report
    And I close the application