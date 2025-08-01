package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePageValidator implements PageValidator {
    private static final Logger logger = LoggerFactory.getLogger(HomePageValidator.class);

    @FindBy(xpath = "//*[contains(@class,'styles_logo')][@href]")
    private WebElement mainMenuLogo;

    @FindBy(xpath = "//div[contains(@class,'dropdown-body')]")
    private WebElement dropdownBody;

    public HomePageValidator(WebDriver driver) {
        PageFactory.initElements(driver, this);
        logger.debug("Initialized HomePageValidator");
    }

    @Override
    public boolean isProfileMenuDisplayed() {
        logger.debug("Checking if profile menu is displayed");
        boolean isDisplayed = mainMenuLogo.isDisplayed();
        logger.debug("Profile menu displayed: {}", isDisplayed);
        return isDisplayed;
    }

    @Override
    public boolean isDropdownBodyDisplayed() {
        logger.debug("Checking if dropdown body is displayed");
        boolean isDisplayed = dropdownBody.isDisplayed();
        logger.debug("Dropdown body displayed: {}", isDisplayed);
        return isDisplayed;
    }
}