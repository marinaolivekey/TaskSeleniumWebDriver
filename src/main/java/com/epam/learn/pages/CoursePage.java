package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CoursePage extends AbstractPage{
    public CoursePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(@class, 'Typography_h1')]")
    private WebElement courseTitle;

    @FindBy(xpath = "//div[contains(@class, 'BookmarkButton_bookmarkIcon') and @data-testid='test-bookmarkIcon']")
    private WebElement bookmarkIcon;

    public CoursePage waitScreenLoaded() {
        waitForVisibility(courseTitle);
        return this;
    }

    public String getCourseTitle() {
        waitForVisibility(courseTitle);
        return courseTitle.getText();
    }

    public CoursePage clickBookmarkIcon() {
        WebElement icon = bookmarkIcon;
        icon.click();
        wait.until(ExpectedConditions.attributeContains(icon, "class", "BookmarkButton_activeBookmark__YvecO"));
        return this;
    }
}
