Feature: Main page appearance
    The main page has to have an appearance identical to what's specified by the stakeholders.

Scenario: I am navigating to the main page
    Given I am looking at the main page
    Then An input box for topic is shown
        But The input box for topic has placeholder text
    Then An input box for shape is shown
        But The input box for shape has placeholder text
    Then A button for building collages is shown
    Then A button for saving collages is shown
    Then A dropdown menu for collage options is shown collapsed
        But The dropdown menu contains several options for collage generation 
        But The dropdown menu contains an option for photo rotation
        But The dropdown menu contains an option for photo borders
        But The dropdown menu contains an option for sepia
        But The dropdown menu contains an option for grayscale
