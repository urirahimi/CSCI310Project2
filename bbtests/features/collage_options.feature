Feature: Collage page options
    The collages generated have color themes based on the options shown.

Scenario: I am generating a collage with the photos rotated.
    Given I am at the landing page 
    When I select the Photo Rotation option
        And I submit a valid topic for a collage to be rotated
    Then The collage generated should be rotated
    When I select the Photo Borders option
        And I submit a valid topic for a collage with borders
    Then The collage generated should have borders around it
    When I select the Sepia option
        And I submit a valid topic for a collage in sepia tone
    Then The collage generated should be in sepia tone
    When I select the Grayscale option
        And I submit a valid topic for a collage in grayscale
    Then The collage generated should be in grayscale
    
