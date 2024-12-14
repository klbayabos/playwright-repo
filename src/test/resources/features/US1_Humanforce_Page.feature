Feature: Humanforce Page

  Scenario: As a viewer, I should be able to visit Humanforce Time & Attendance page
    Given viewer is on the homepage of the Humanforce page
    When viewer clicks the Time and Attendance link
    Then viewer should see a list of helpful resources
    
  Scenario: As a viewer, I should be able to select and read an article
    Given viewer is on the homepage of the Humanforce page
    When viewer clicks the Time and Attendance link
    And viewer selects the "7 benefits of workforce analytics for business" article
    Then viewer reads the content of the article "7 benefits of workforce analytics for business"