package tests;

import com.epam.learn.util.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class CommonConditions {
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        driver = DriverSingleton.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        DriverSingleton.quitDriver();
    }
}