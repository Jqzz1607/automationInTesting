@non-functionalTest
Feature: Verify homePG title name  Visibility
  As a QA Engineer,
  I want to ensure that homePG title name is visible
  So homePG title UI Link appear.



  Scenario: Validate homepage title
    Given I navigate to Shady B&B page
    Then  "Shady Meadows B&B" title is displayed