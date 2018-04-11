Feature: Login page appearance
    The login page has to have an appearance identical to what's specified by the stakeholders.

Scenario: I am navigating to the login page
    Given I am looking at the login page
    Then An input field for email is shown
    Then An input field for password is shown
    Then A button for logging in is shown
