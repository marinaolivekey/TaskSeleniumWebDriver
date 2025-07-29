package com.epam.learn.util;

import com.epam.learn.util.driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestFailureListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(TestFailureListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test '{}' failed: {}", result.getName(), result.getThrowable().getMessage());
        takeScreenshot(result);
    }

    private void takeScreenshot(ITestResult result) {
        try {
            File screenshot = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String screenshotPath = String.format("screenshots/%s_%s.png", result.getName(), timestamp);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(screenshot, destination);
            logger.info("Screenshot saved: {}", destination.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to save screenshot: {}", e.getMessage());
        }
    }
}
