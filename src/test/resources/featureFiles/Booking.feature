@functionalTest
Feature: Booking
As a customer
I want to book room of any type


  Scenario Outline: Successful single room booking
    Given I navigate to Shady B&B page
    When I book a room with valid details "<firstName>", "<LastName>","<email>","<phone>"
    Then I should see a booking confirmation "<message>"
    Examples:
      | firstName | LastName | email          | phone      |message          |
      | mike      | mike     | mike@gmail.com |07514543921 |Booking Confirmed|



