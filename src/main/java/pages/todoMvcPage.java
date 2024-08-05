package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class todoMvcPage {
    WebDriverWait wait;
    WebDriver driver;

    By newTodoInput = By.className("new-todo");
    By todoList = By.className("todo-list");
    By todoItems = By.cssSelector(".todo-list li");
    By todoCheckbox = By.cssSelector(".todo-list li .toggle");
    By deleteButton = By.cssSelector(".todo-list li .destroy");
    By filterActive = By.linkText("Active");
    By filterCompleted = By.linkText("Completed");

    public todoMvcPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void open(String url) {
        driver.get(url);
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(newTodoInput)).isDisplayed();
    }

    public void addTask(String task) {
        driver.findElement(newTodoInput).sendKeys(task);
    }

    public boolean isTaskPresent(String task) {
        return driver.findElements(todoList)
                .stream()
                .anyMatch(element -> element.getText().contains(task));
    }

    public void completeTask(String task) {
        driver.findElements(todoItems)
                .stream()
                .filter(element -> element.getText().contains(task))
                .findFirst()
                .ifPresent(element -> element.findElement(todoCheckbox).click());
    }

    public boolean isTaskCompleted(String task) {
        return driver.findElements(todoList)
                .stream()
                .filter(element -> element.getText().contains(task))
                .findFirst()
                .map(element -> element.getAttribute("class").contains("completed"))
                .orElse(false);
    }

    public void deleteTask(String task) {
        driver.findElements(todoItems)
                .stream()
                .filter(element -> element.getText().contains(task))
                .findFirst()
                .ifPresent(element -> element.findElement(deleteButton).click());
    }

    public boolean isTaskDeleted(String task) {
        return driver.findElements(todoItems)
                .stream()
                .noneMatch(element -> element.getText().contains(task));
    }

    public void filterActiveTasks() {
        WebElement activeLink = wait.until(ExpectedConditions.visibilityOfElementLocated(filterActive));
        activeLink.click();
    }

    public void filterCompletedTasks() {
        driver.findElement(filterCompleted).click();
    }

    public boolean isOnlyActiveTasksPresent() {
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(todoItems, 0))
                .stream()
                .allMatch(element -> !element.getAttribute("class").contains("completed"));
    }

    public boolean isOnlyCompletedTasksPresent() {
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(todoItems, 0))
                .stream()
                .allMatch(element -> element.getAttribute("class").contains("completed"));
    }
}
