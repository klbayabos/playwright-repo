Feature: Employee Login and Homepage

  Scenario: As an employee, I should be able to login to the test tenant
    Given the user navigates to test tenant
    When the user logins as "Employee"
    Then the user should be redirected to the homepage

  Scenario: If not an employee, I should not be able to login to the test tenant
    Given the user navigates to test tenant
    When the user logins with username "InvalidUN" and password "RandomPass"
    Then the user should see unsuccessful error message
    
  Scenario: As an employee logged in to the system, I should be able to access the homepage
    Given the user navigates to test tenant
    When the user logins as "Employee"
    Then the user should be redirected to the homepage
    And the user should be see the homepage content    
    
  Scenario: As an employee logged in to the system, I should be able to see the homepage greeting
    Given the user navigates to test tenant
    When the user logins as "Employee"
    Then the user should be redirected to the homepage
    And the user should be see the homepage greeting his name "Wade"