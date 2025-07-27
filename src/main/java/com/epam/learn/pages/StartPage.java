package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends AbstractPage {
    private final String PAGE_URL = "https://learn.epam.com";

    @FindBy(xpath = "//a[contains(@class, 'uui-button-box') and .//div[text()='Log In']]")
    private WebElement loginButton;

    @FindBy(id = "content")
    private WebElement loginForm;

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public StartPage openPage() {
        driver.navigate().to(PAGE_URL);
        waitForPageLoad();
        waitForLoginButtonDisplayed();
        return this;
    }

    public LoginPage clickSignIn() {
        waitForVisibility(loginButton);
        loginButton.click();
        return new LoginPage(driver).waitLoginFormVisibility();
    }

    public StartPage waitForLoginButtonDisplayed() {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(loginButton)));
        return this;
    }

    private StartPage waitForPageLoad() {
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        return this;
    }
}