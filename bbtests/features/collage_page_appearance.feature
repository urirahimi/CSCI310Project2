Feature: Collage page appearance
    The collage page has to have an appearance identical to what's specified by the stakeholders.

Scenario: I am navigating to the collage page
    Given I submitted a valid topic on the main page
    Then A loading symbol should appear until I see the collage page
    Then An export collage button is shown
    Then A save collage button is shown
    Then An input box for re-entering a topic is shown
        But The input box for topic has placeholder text
    Then A rectangle that displays miniaturized images of previous collages generated is shown
    Then A collage is shown

    Given I submitted an invalid topic on the main page
    Then A loading symbol should still appear until I see the collage page
    Then An error message is shown
    Then All UI elements besides the collage is the same
