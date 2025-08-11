package com.epam.learn.util.driver;

import com.epam.reportportal.service.ReportPortal;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DriverSingleton {
    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<List<File>> screenshotList = ThreadLocal.withInitial(ArrayList::new);
    private static final File TESTNG_DIRECTORY = new File("screenshots");

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser", "chrome");
            logger.info("Initializing WebDriver for browser: {} in thread: {}", browser, Thread.currentThread().getName());
            driver.set(WebDriverFactory.createWebDriver(browser));
            driver.get().manage().window().maximize();
            logger.debug("WebDriver initialized for thread: {}", Thread.currentThread().getName());
        }
        return driver.get();
    }

    public static void scrollPageForScreenshot() {
        try {
            WebDriver webDriver = getDriver();
            if (webDriver != null) {
                ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                logger.info("Page scrolled for screenshot in thread: {}", Thread.currentThread().getName());
            }
        } catch (Exception e) {
            logger.error("Failed to scroll page for screenshot: {}", e.getMessage());
            ReportPortal.emitLog("Failed to scroll page for screenshot: " + e.getMessage(), "ERROR", new java.util.Date());
        }
    }

    public static void takeScreenshot() {
        try {
            WebDriver webDriver = getDriver();
            if (webDriver != null) {
                if (!TESTNG_DIRECTORY.exists()) {
                    TESTNG_DIRECTORY.mkdirs();
                }
                File screenshot = File.createTempFile("screenshot", ".png", TESTNG_DIRECTORY);
                try (FileOutputStream stream = new FileOutputStream(screenshot)) {
                    stream.write(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
                }
                screenshotList.get().add(screenshot);
                logger.info("Screenshot saved: {} in thread: {}", screenshot.getAbsolutePath(), Thread.currentThread().getName());
            }
        } catch (IOException e) {
            logger.error("Unable to save screenshot", e);
            ReportPortal.emitLog("Unable to save screenshot: " + e.getMessage(), "ERROR", new java.util.Date());
        }
    }

    public static void attachScreenshots() {
        List<File> screenshots = screenshotList.get();
        if (screenshots != null) {
            for (File file : screenshots) {
                try {
                    ReportPortal.emitLog(
                            "Saved screenshot: " + file.getName(),
                            "INFO",
                            new java.util.Date(),
                            file
                    );
                } catch (Exception e) {
                    logger.error("Failed to attach screenshot: {}", file.getName());
                    ReportPortal.emitLog("Failed to attach screenshot: " + file.getName(), "ERROR", new java.util.Date());
                }
            }
            screenshots.clear();
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Closing WebDriver for thread: {}", Thread.currentThread().getName());
            driver.get().quit();
            driver.remove();
            screenshotList.remove();
            logger.debug("WebDriver closed for thread: {}", Thread.currentThread().getName());
        }
    }
}