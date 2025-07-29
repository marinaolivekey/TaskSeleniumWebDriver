package com.epam.learn.pages;

import com.epam.learn.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartPage extends AbstractPage {
    private static final Logger logger = LoggerFactory.getLogger(StartPage.class);
    private final String PAGE_URL = ConfigReader.getBaseUrl();

    @FindBy(xpath = "//a[contains(@class, 'uui-button-box') and .//div[text()='Log In']]")
    private WebElement loginButton;

    @FindBy(id = "content")
    private WebElement loginForm;

    public StartPage(WebDriver driver) {
        super(driver);
        logger.debug("Initialized StartPage");
    }

    public StartPage openPage() {
        logger.info("Navigating to page: {}", PAGE_URL);
        driver.navigate().to(PAGE_URL);
        waitForPageLoad();
        waitForLoginButtonDisplayed();
        logger.debug("Page loaded successfully");
        return this;
    }

    public LoginPage clickSignIn() {
        logger.info("Clicking Sign In button");
        waitForVisibility(loginButton);
        loginButton.click();
        logger.debug("Sign In button clicked");
        return new LoginPage(driver).waitLoginFormVisibility();
    }

    public StartPage waitForLoginButtonDisplayed() {
        logger.debug("Waiting for login button to be visible");
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(loginButton)));
        logger.debug("Login button is visible");
        return this;
    }

    private StartPage waitForPageLoad() {
        logger.debug("Waiting for page to load completely");
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        logger.debug("Page load completed");
        return this;
    }
}