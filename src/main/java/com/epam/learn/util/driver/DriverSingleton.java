package com.epam.learn.util.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverSingleton {
    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser", "chrome");
            logger.info("Initializing WebDriver for browser: {} in thread: {}", browser, Thread.currentThread().getName());
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--incognito");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("-private");
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.get().manage().window().maximize();
            logger.debug("WebDriver initialized for thread: {}", Thread.currentThread().getName());
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Closing WebDriver for thread: {}", Thread.currentThread().getName());
            driver.get().quit();
            driver.remove();
            logger.debug("WebDriver closed for thread: {}", Thread.currentThread().getName());
        }
    }
}