package steps;

import com.epam.learn.util.driver.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @After
    public void tearDown() {
        DriverSingleton.quitDriver();
    }
}