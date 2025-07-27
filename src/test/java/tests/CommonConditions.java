package tests;

import com.epam.learn.util.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class CommonConditions {
    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        driver = DriverSingleton.getDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (driver != null) {
            DriverSingleton.quitDriver();
        }
    }
}