@non-functionalTest
Feature:  presence of performance API and Timing Components
  As a QA
  I want to verify that the Performance API and its key timing components are present in the browser
  So that I can it used them to measure the performance of https://automationintesting.online/

  Scenario: Verify presence of Performance API and its components
    Given I navigate to Shady B&B page
    Then  the browser should have "window.performance"
    And   the browser should have "performance.timing"
    And   the browser should have "performance.getEntriesByType"
    And   the browser should have at least one navigation entry in "performance.getEntriesByType('navigation')"