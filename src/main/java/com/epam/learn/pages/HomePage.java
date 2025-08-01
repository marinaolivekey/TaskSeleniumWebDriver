package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends AbstractPage {
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(xpath = "//*[contains(@class, 'AppHeader_action')]")
    private WebElement profileAvatar;

    @FindBy(xpath = "//*[contains(@class, 'AvatarBadge_description')]")
    private WebElement profileEmail;

    @FindBy(xpath = "//div[contains(@class, 'dropdown-body')]")
    private WebElement dropdownBody;

    public HomePage(WebDriver driver) {
        super(driver);
        logger.debug("Initialized HomePage");
    }

    public HomePage waitProfileMenuDisplayed() {
        logger.info("Waiting for profile menu to be displayed");
        try {
            waitForVisibility(profileAvatar);
            logger.debug("Profile menu is visible");
        } catch (Exception e) {
            logger.error("Failed to wait for profile menu visibility: {}", e.getMessage());
            throw e;
        }
        return this;
    }

    public HomePage clickProfileAvatar() {
        logger.info("Clicking profile avatar");
        profileAvatar.click();
        logger.debug("Profile avatar clicked");
        return this;
    }

    public String getProfileEmail() {
        logger.info("Getting profile email");
        waitForVisibility(profileEmail);
        String email = profileEmail.getText();
        logger.debug("Profile email retrieved: {}", email);
        return email;
    }

    public HomePage waitProfileDropDownIsDisplayed() {
        logger.info("Waiting for profile dropdown to be displayed");
        waitForVisibility(dropdownBody);
        logger.debug("Profile dropdown is visible");
        return this;
    }
}