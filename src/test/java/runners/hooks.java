package runners;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.driverManagers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class hooks {
    private WebDriver driver = driverManagers.getDriver();

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshot.toPath(), new File("C:/Users/ESB/playground/src/screenshots" + scenario.getName() + ".png").toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driverManagers.closeDriver();
    }
}
