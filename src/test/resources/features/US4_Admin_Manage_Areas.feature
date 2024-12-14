Feature: Employee Login and Homepage

  Scenario: As an admin, I should be able to create a new area
    Given the user is logged in to test tenant as "Admin"
    When the user navigates to Area page
    And the user creates a new area with name "Name_ABCD" shortname "A" exportcode "Code 1"
    Then the user should see the name "Name_ABCD" shortname "A" exportcode "Code 1" in the list of Areas

  Scenario: As an admin, I should be able to create multiple areas
    Given the user is logged in to test tenant as "Admin"
    When the user navigates to Area page
    And the user creates a new area with name "Name_ABCDE" shortname "B" exportcode "Code 2"
    And the user creates a new area with name "Name_ABCDEF" shortname "C" exportcode "Code 3"
    Then the user should see the name "Name_ABCDE" shortname "B" exportcode "Code 2" in the list of Areas
    And the user should also see the name "Name_ABCDEF" shortname "C" exportcode "Code 3" in the list of Areas
    
  Scenario: As an admin, I should be able to delete an area
    Given the user is logged in to test tenant as "Admin"
    When the user navigates to Area page
    And the user creates a new area with name "Name_ABCDEFG" shortname "D" exportcode "Code 4"
    When the user deletes area with name "Name_ABCDEFG" exportcode "Code 4"
    Then the area with name "Name_ABCDEFG" exportcode "Code 4" should not display in the list of Areas
    
  Scenario: As an admin, I should be able to delete multitple areas
    Given the user is logged in to test tenant as "Admin"
    When the user navigates to Area page
    When the user deletes area with name "Name_ABCD" exportcode "Code 1"
    When the user deletes area with name "Name_ABCDE" exportcode "Code 2"
    When the user deletes area with name "Name_ABCDEF" exportcode "Code 3"
    Then the area with name "Name_ABCD" exportcode "Code 1" should not display in the list of Areas
    And the area with name "Name_ABCDE" exportcode "Code 2" should also not display in the list of Areas
    And the area with name "Name_ABCDEF" exportcode "Code 3" should also not display in the list of Areas