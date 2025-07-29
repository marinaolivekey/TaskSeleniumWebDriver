package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends AbstractPage{
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(xpath = "//*[contains(@class,'styles_logo')][@href]")
    private WebElement mainMenuLogo;

    @FindBy(xpath = "//*[contains(@class, 'AppHeader_action')]")
    private WebElement profileAvatar;

    @FindBy(xpath= "//*[contains(@class,'AvatarBadge_description')]")
    private WebElement profileEmail;

    @FindBy(xpath= "//div[contains(@class,'dropdown-body')]")
    private WebElement dropdownBody;

    public HomePage(WebDriver driver) {
        super(driver);
        logger.debug("Initialized HomePage");
    }

    public HomePage waitProfileMenuDisplayed() {
        logger.info("Waiting for profile menu to be displayed");
        waitForVisibility(profileAvatar);
        logger.debug("Profile menu is visible");
        return this;
    }

    public boolean isProfileMenuDisplayed() {
        logger.debug("Checking if profile menu is displayed");
        boolean isDisplayed = mainMenuLogo.isDisplayed();
        logger.debug("Profile menu displayed: {}", isDisplayed);
        return isDisplayed;
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

    public boolean isDropdownBodyDisplayed() {
        logger.debug("Checking if dropdown body is displayed");
        boolean isDisplayed = dropdownBody.isDisplayed();
        logger.debug("Dropdown body displayed: {}", isDisplayed);
        return isDisplayed;
    }

    public HomePage waitProfileDropDownIsDisplayed() {
        logger.info("Waiting for profile dropdown to be displayed");
        waitForVisibility(dropdownBody);
        logger.debug("Profile dropdown is visible");
        return this;
    }
}
