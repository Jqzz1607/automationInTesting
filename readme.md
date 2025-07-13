# Java BDD Selenium Framework for Shady B&B Application

## Overview

This project is a Behavior-Driven Development (BDD) test automation framework built using Java, Selenium WebDriver, Cucumber, and JUnit. It tests the Shady B&B web application with features for login, booking, contact forms, navigation validation, and performance checks.

---

## Table of Contents

- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Setup and Running Tests](#setup-and-running-tests)
- [Test Plan Document](#test-plan-document)
- [Bug Reporting](#bug-reporting)
- [Suggestions & Improvements](#suggestions--improvements)
- [Author](#author)

---

## Project Structure

automationInTestingOnline/
├── src/
│ ├── main/
│ │ └── java/online/automationInTesting/App.java
│ └── test/
│ ├── java/online/automationInTesting/
│ │ ├── helpers/ # Browser and Driver setup
│ │ ├── featureFiles/ # Gherkin feature files
│ │ ├── hooks/ # Cucumber hooks (Before/After)
│ │ ├── pages/ # Page Object Model classes
│ │ ├── runners/ # Cucumber test runners
│ │ ├── stepDefinitions/ # Step definitions for features
│ │ └── utils/ # Utility classes and config files
│ └── resources/ # Properties and config files
├── pom.xml # Maven dependencies
├── target/ # Test output and reports
└── README.md # This file


---

## Technologies Used

- Java 11+
- Selenium WebDriver 4.x
- Cucumber 7+
- JUnit 4.12
- Maven for build and dependency management
- WebDriverManager for browser drivers
- ExtentReports for test reporting
- Page Object Model for better code maintainability

---

## Setup and Running Tests

### Prerequisites
- Install Java (JDK 11+)
- Install Maven
- Ensure a supported browser (Chrome) installed

### Steps

1. Clone this repo:  
   git clone https://github.com/yourusername/automationInTestingOnline.git
2. Navigate to project directory:  
   cd automationInTestingOnline
3. Run tests via Maven:
   mvn clean test
4. Run functional tests:
   mvn clean test -Dcucumber.filter.tags="@functionalTest"
5. Run non-functional tests:
      mvn clean test -Dcucumber.filter.tags="@non-functionalTest"
6. Reports will be generated in `target/` folder, including ExtentReports HTML.

7. Feature files located under `src/test/java/online/automationInTesting/featureFiles` describe test scenarios.

---

## Test Plan Document

### 1. Purpose

To validate the functional and non-functional requirements of the Shady B&B application through automated BDD tests to ensure quality and reliability.

### 2. Scope

- Functional testing of key user workflows: admin login, booking rooms, and contact form submission.
- UI validation of navigation bar and homepage elements.
- Non-functional checks including performance API availability and page title visibility.

### 3. Test Objectives

- Confirm user can login with valid credentials and is prevented with invalid credentials.
- Verify booking can be successfully completed with valid details and prevent invalid data submissions.
- Ensure contact form submission works with valid inputs and validates invalid inputs.
- Check UI components such as navigation bar links and page titles are visible.
- Confirm browser performance API components exist for performance monitoring.

### 4. Test Types

| Type               | Description                                                   | Tags in Features     |
|--------------------|---------------------------------------------------------------|----------------------|
| Functional Testing | Verify core application workflows and business logic          | @functionalTest      |
| Non-Functional Testing | Validate UI element presence, accessibility, and performance | @non-functionalTest  |
| Negative Testing   | Verify application handles invalid inputs and error states     | Included in scenarios |

### 5. Test Environment

| Environment    | Details |
|----------------|-------|
| URL            | https://automationintesting.online/ |
| Browsers       | Chrome (latest) |
| OS             | macOS |
| Tools          | Selenium WebDriver, Maven, Java 11+ |

### 6. Entry Criteria

- Application deployed and accessible on test environment URL.
- Test environment configured with required browsers and drivers.
- Test data for valid and invalid scenarios prepared.

### 7. Exit Criteria

- All critical and major test cases pass without blocking issues.
- All identified bugs reported and documented.
- Test reports generated and reviewed.

### 8. Roles and Responsibilities

| Role               | Responsibility                           |
|--------------------|----------------------------------------|
| QA Engineer        | Create and maintain test automation scripts, run tests, report bugs |


### 9. Deliverables

- Automated test scripts in Git repository.
- Test execution reports (ExtentReports HTML).
- Bug reports with detailed steps and environment info.
- README and project documentation.

### 10. Risks and Mitigation

| Risk                             | Mitigation                              |
|---------------------------------|---------------------------------------|
| UI changes breaking tests       | Use Page Object Model for easier updates and stable locators |
| Network/environment instability | Run tests on stable environments and retry flaky tests |

---

## Bug Reporting

### Bug #1: Booking Past Dates

- **Environment:** prod, Desktop, Chrome v124
- **Steps:**
1. Open https://automationintesting.online/
2. Click "Book this Room"
3. Select past dates and proceed
4. Fill user details and book
- **Expected:** Past dates should be disabled to prevent booking.
- **Actual:** Booking is successful even with past dates.

---

### Bug #2: Invalid Characters Accepted in Booking Form

- **Environment:** prod, Desktop, Chrome v124
- **Steps:**
1. Open https://automationintesting.online/
2. Click "Book this Room"
3. Enter numbers in Firstname/Lastname, characters in phone number
4. Click “BOOK”
- **Expected:** Validation error preventing booking with invalid characters.
- **Actual:** Booking is successfully completed without error.

---

## Suggestions & Improvements

- **Improve date validation:** Disable past dates in the booking calendar UI and add server-side validation.
- **Input validation:** Enforce stricter form validations (e.g. names only alphabets, phone numbers only digits).
- **Add automated UI screenshots:** Capture screenshots on test failure for debugging.
- **Enhance test data management:** Externalize test data for more robust scenario coverage.
- **Parallel execution:** Enable parallel test execution to reduce total runtime.
- **Continuous Integration:** Integrate with CI tools like Jenkins or GitHub Actions for automated test runs.
- **Accessibility tests:** Add basic checks for web accessibility compliance.
- **Performance monitoring:** Extend performance tests to measure page load times and resource usage.

---

## Contact

If you want to contribute or have questions, reach out:

- GitHub: [yourusername](https://github.com/yourusername)
- Email: your.email@example.com

---

### Thank you for checking out this project!

