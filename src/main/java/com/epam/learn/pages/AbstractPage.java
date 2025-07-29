package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage {
    private static final Logger logger = LoggerFactory.getLogger(AbstractPage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public static final int DEFAULT_TIME_OUT_IN_SECONDS = 40;
    public static final int PAGE_LOAD_TIME_OUT_IN_SECONDS = 10;


    public AbstractPage(WebDriver driver) {
        if (driver == null) {
            logger.error("WebDriver instance cannot be null");
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS));
        PageFactory.initElements(driver, this);
        logger.debug("Initialized AbstractPage for {}", this.getClass().getSimpleName());
    }

    public AbstractPage waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public AbstractPage waitForStalenessOf(WebElement element) {
        wait.withTimeout(Duration.ofSeconds(PAGE_LOAD_TIME_OUT_IN_SECONDS))
                .until(ExpectedConditions.stalenessOf(element));
        return this;
    }

    public AbstractPage waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }
}
