@functionalTest
Feature: Contact Us form submission

  Scenario Outline: Submit contact form with valid details
    Given I am on the contact page
    When I fill in the contact form with "<name>", "<email>", "<phone>", "<subject>", "<message>"
    And I submit the form
    Then I should see a confirmation message "<confirmation>"

    Examples:
      | name  | email             | phone        | subject         | message                              | confirmation     |
      | Alice | alice@example.com | 123456789101 | Booking Inquiry | I'd like to book a single room test. | Booking Inquiry  |

  Scenario Outline: Submit contact form with invalid phone
    Given I am on the contact page
    When I fill in the contact form with "<name>", "<email>", "<phone>", "<subject>", "<message>"
    And I submit the form
    Then I should see a error message "<confirmation>"

    Examples:
      | name  | email             | phone        | subject      | message                              | confirmation                               |
      | Alice | alice@example.com | 123456101 | Booking Inquiry | I'd like to book a single room test. | Phone must be between 11 and 21 characters.|
