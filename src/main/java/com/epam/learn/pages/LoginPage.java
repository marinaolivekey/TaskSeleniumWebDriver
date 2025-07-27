package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

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
    }

    public LoginPage waitLoginFormVisibility() {
        waitForVisibility(loginForm);
        return this;
    }

    public HomePage login(String username) {
                typeLoginName(username)
                .clickSubmit()
                .waitProfileMenuDisplayed();
        return new HomePage(driver);
    }


    public LoginPage typeLoginName(String username) {
        waitForVisibility(usernameField);
        usernameField.click();
        usernameField.sendKeys(username);
        return this;
    }

    public HomePage clickSubmit() {
        waitForVisibility(nextButton);
        nextButton.click();
        return new HomePage(driver);
    }
}