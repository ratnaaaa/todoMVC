Feature: TodoMVC
  Scenario: Visit URL
    Given I open TodoMVC URL

  Scenario: Add Task
    Given I open TodoMVC URL
    When I add a new task "Go to H Club SCBD"
    And I add a new task "Buy a bottle of Singleton"
    And I add a new task "Dance the night out!"
    Then I should see the task "Go to H Club SCBD" in the list
    And I should see the task "Buy a bottle of Singleton" in the list
    And I should see the task "Dance the night out!" in the list

  Scenario: Complete Task
    Given I open TodoMVC URL
    When I add a new task "Go to H Club SCBD"
    And I mark the task "Go to H Club SCBD" as completed
    Then I should see the task "Go to H Club SCBD" marked as completed

  Scenario: Delete Task
    Given I open TodoMVC URL
    When I add a new task "Buy a bottle of Singleton"
    And I delete the task "Buy a bottle of Singleton"
    Then I should not see the task "Buy a bottle of Singleton" in the list

  Scenario: Filter Tasks
    Given I open TodoMVC URL
    When I add a new task "Go to H Club SCBD"
    And I add a new task "Buy a bottle of Singleton"
    And I add a new task "Dance the night out!"
    And I mark the task "Go to H Club SCBD" as completed
    And I mark the task "Dance the night out!" as completed
    When I filter tasks by "Active"
    Then I should see only "active" tasks
    When I filter tasks by "Completed"
    Then I should see only "completed" tasks