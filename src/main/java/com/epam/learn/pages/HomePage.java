package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage{

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
    }

    public HomePage waitProfileMenuDisplayed() {
        waitForVisibility(profileAvatar);
        return this;
    }

    public boolean isProfileMenuDisplayed() {
        return mainMenuLogo.isDisplayed();
    }

    public HomePage clickProfileAvatar() {
        profileAvatar.click();
        return this;
    }

    public String getProfileEmail() {
        waitForVisibility(profileEmail);
        return profileEmail.getText();
    }

    public boolean isDropdownBodyDisplayed() {
        return dropdownBody.isDisplayed();
    }

    public HomePage waitProfileDropDownIsDisplayed() {
        waitForVisibility(dropdownBody);
        return this;
    }
}
