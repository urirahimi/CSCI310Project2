Feature: Main page appearance
    The main page has to have an appearance identical to what's specified by the stakeholders.

Scenario: I am navigating to the main page
    Given I am looking at the main page
    Then An input box for topic is shown
        But The input box for topic has placeholder text
    Then A button for building collages is shown
    Then A button for saving collages is shown
    Then A dropdown menu for collage shape is shown collapsed
    Then A dropdown menu for collage options is shown collapsed
    
