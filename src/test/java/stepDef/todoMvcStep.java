package stepDef;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.todoMvcPage;
import utils.driverManagers;
import static org.junit.Assert.assertTrue;

public class todoMvcStep {
    WebDriver driver;
    todoMvcPage todoMVCPage;

    @Given("I open TodoMVC URL")
    public void iOpenTodoMVCURL() {
        driver = driverManagers.getDriver();
        todoMVCPage = new todoMvcPage(driver);
        todoMVCPage.open("https://todomvc.com/examples/react/dist/");
        assertTrue(todoMVCPage.isPageLoaded());
    }

    @When("I add a new task {string}")
    public void iAddANewTask(String task) {
        todoMVCPage.addTask(task);
    }

    @Then("I should see the task {string} in the list")
    public void iShouldSeeTheTaskInTheList(String task) {
        assertTrue(todoMVCPage.isTaskPresent(task));
    }

    @When("I mark the task {string} as completed")
    public void iMarkTheTaskAsCompleted(String task) {
        todoMVCPage.completeTask(task);
    }

    @Then("I should see the task {string} marked as completed")
    public void iShouldSeeTheTaskMarkedAsCompleted(String task) {
        assertTrue(todoMVCPage.isTaskCompleted(task));
    }

    @When("I delete the task {string}")
    public void iDeleteTheTask(String task) {
        todoMVCPage.deleteTask(task);
    }

    @Then("I should not see the task {string} in the list")
    public void iShouldNotSeeTheTaskInTheList(String task) {
        assertTrue(todoMVCPage.isTaskDeleted(task));
    }

    @When("I filter tasks by {string}")
    public void iFilterTasksBy(String filter) {
        switch (filter.toLowerCase()) {
            case "active":
                todoMVCPage.filterActiveTasks();
                break;
            case "completed":
                todoMVCPage.filterCompletedTasks();
                break;
        }
    }

    @Then("I should see only {string} tasks")
    public void iShouldSeeOnlyTasks(String filter) {
        switch (filter.toLowerCase()) {
            case "active":
                assertTrue(todoMVCPage.isOnlyActiveTasksPresent());
                break;
            case "completed":
                assertTrue(todoMVCPage.isOnlyCompletedTasksPresent());
                break;
        }
    }
}
