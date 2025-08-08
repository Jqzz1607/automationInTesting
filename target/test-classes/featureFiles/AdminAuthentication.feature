@functionalTest
Feature: Admin user login
  As an admin user
  I want to login


  Scenario: Valid login with correct admin credentials
    Given I am on the admin login page
    When  I login with username "admin" and password "password"
    Then  admin dashboard is displayed as "Restful Booker Platform Demo"

  Scenario Outline: Invalid login with incorrect password or incorrect user id
    Given I am on the admin login page
    When I login with username "<userId>" and password "<userPassword>"
    Then I should see login error "<msg>"
    Examples:
      | userId | userPassword |       msg         |
      | admin  |   com        |Invalid credentials|
      | com    | password     |Invalid credentials|
      | abc    | abc          |Invalid credentials|