package com.epam.learn.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HighlightLoginPageDecorator implements LoginPageActions {
    private static final Logger logger = LoggerFactory.getLogger(HighlightLoginPageDecorator.class);
    private final LoginPageActions loginPage;
    private final WebDriver driver;

    public HighlightLoginPageDecorator(LoginPageActions loginPage, WebDriver driver) {
        this.loginPage = loginPage;
        this.driver = driver;
    }

    @Override
    public LoginPageActions typeLoginName(String username) {
        logger.info("Decorating typeLoginName with highlight");
        LoginPage loginPageImpl = (LoginPage) loginPage;
        WebElement usernameField = loginPageImpl.getUsernameField(); // Предполагается, что добавлен геттер
        highlightElement(usernameField);
        loginPage.typeLoginName(username);
        unhighlightElement(usernameField);
        return this;
    }

    @Override
    public HomePage clickSubmit() {
        logger.info("Decorating clickSubmit with highlight");
        LoginPage loginPageImpl = (LoginPage) loginPage;
        WebElement nextButton = loginPageImpl.getNextButton(); // Предполагается, что добавлен геттер
        highlightElement(nextButton);
        HomePage result = loginPage.clickSubmit();
        unhighlightElement(nextButton);
        return result;
    }

    @Override
    public LoginPageActions waitLoginFormVisibility() {
        logger.info("Decorating waitLoginFormVisibility with highlight");
        LoginPage loginPageImpl = (LoginPage) loginPage;
        WebElement loginForm = loginPageImpl.getLoginForm(); // Предполагается, что добавлен геттер
        highlightElement(loginForm);
        loginPage.waitLoginFormVisibility();
        unhighlightElement(loginForm);
        return this;
    }

    @Override
    public HomePage login(String username) {
        logger.info("Decorating login with highlight");
        return typeLoginName(username).clickSubmit();
    }

    private void highlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid red'; arguments[0].style.backgroundColor='yellow';", element);
            logger.debug("Element highlighted: {}", element);
            Thread.sleep(500); // Пауза для видимости подсветки
        } catch (InterruptedException e) {
            logger.error("Failed to highlight element: {}", e.getMessage());
        }
    }

    private void unhighlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border=''; arguments[0].style.backgroundColor='';", element);
            logger.debug("Element unhighlighted: {}", element);
        } catch (Exception e) {
            logger.error("Failed to unhighlight element: {}", e.getMessage());
        }
    }
}