Feature: Verify and store Jacket details from the Shop Menu in CP
  @smoke
  Scenario: Store Jacket details with price and Top Seller message
    Given I open application
    And I navigate to "Men's" category
    Then I attach Jacket's price, title, and Top Seller message in the report
    And I close the application

  @smoke
  Scenario: Count total video feeds on News & Features page
    Given I open application
    And I navigated to "News & Features"
    Then I count the total number of video feeds on the page older than 3 days
    And I close the application