Feature: Login page functionality
    In order to login to access collage generation functionality, credentials have to be entered

Scenario: I am navigating to the login page
    Given I am on the login page

    Then The input field for email should be required
    Then The input field for password should be required

    When I enter an empty email and submit
    Then I stay in the login page due to empty email input

    When I enter an empty password and submit
    Then I stay in the login page due to empty password input

    When I enter an email that isn't registered
    Then An error message about non-existent email should appear

    When I enter a registered email and the incorrect password
    Then An error message about incorrect password should appear

    When I enter a registered email and the correct password
    Then I get redirected to the main page
