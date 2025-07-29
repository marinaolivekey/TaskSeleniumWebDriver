package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractPage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(id = "content")
    private WebElement loginForm;

    @FindBy(id = "userName")
    private WebElement usernameField;

    @FindBy(css = "input[name='password'][type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@class,'submit')]")
    private WebElement nextButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        logger.debug("Initialized LoginPage");
    }

    public LoginPage waitLoginFormVisibility() {
        logger.info("Waiting for login form to be visible");
        waitForVisibility(loginForm);
        logger.debug("Login form is visible");
        return this;
    }

    public HomePage login(String username) {
        logger.info("Logging in with username: {}", username);
                typeLoginName(username)
                .clickSubmit()
                .waitProfileMenuDisplayed();
        logger.debug("Login completed");
        return new HomePage(driver);
    }


    public LoginPage typeLoginName(String username) {
        logger.info("Typing username: {}", username);
        waitForVisibility(usernameField);
        usernameField.click();
        usernameField.sendKeys(username);
        logger.debug("Username entered");
        return this;
    }

    public HomePage clickSubmit() {
        logger.info("Clicking submit button");
        waitForVisibility(nextButton);
        nextButton.click();
        logger.debug("Submit button clicked");
        return new HomePage(driver);
    }
}