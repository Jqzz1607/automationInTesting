@non-functionalTest
Feature: Verify Navigation Bar Link Visibility
  As a QA Engineer,
  I want to ensure that all navigation bar links are visible
  So nav bar UI Links appear.

Scenario Outline: Navbar links are visible
Given I navigate to Shady B&B page
Then  navbar link "<text>" is visible
Examples:
| text      |
| Rooms     |
| Booking   |
| Amenities |
| Location  |
| Contact   |