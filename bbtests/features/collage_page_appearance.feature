Feature: Collage page appearance
    The collage page has to have an appearance identical to what's specified by the stakeholders.

Scenario: I am navigating to the collage page
    Given I am looking at the collage page
    Then An export collage button is shown
    Then A save collage button is shown
    Then An input box for re-entering a topic is shown
        But The input box for topic has placeholder text
    Then A rectangle that displays miniaturized images of previous collages generated.
    Then A collage is shown if there are sufficient images.
    Then An error message is shown if there are insufficient images.
