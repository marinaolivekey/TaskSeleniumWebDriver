package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoursePage extends AbstractPage{
    private static final Logger logger = LoggerFactory.getLogger(CoursePage.class);

    @FindBy(xpath = "//h1[contains(@class, 'Typography_h1')]")
    private WebElement courseTitle;

    @FindBy(xpath = "//div[contains(@class, 'BookmarkButton_bookmarkIcon') and @data-testid='test-bookmarkIcon']")
    private WebElement bookmarkIcon;

    public CoursePage(WebDriver driver) {
        super(driver);
        logger.debug("Initialized CoursePage");
    }

    public CoursePage waitScreenLoaded() {
        logger.info("Waiting for Course page to load");
        waitForVisibility(courseTitle);
        logger.debug("Course page loaded");
        return this;
    }

    public String getCourseTitle() {
        logger.info("Getting course title");
        waitForVisibility(courseTitle);
        String title = courseTitle.getText();
        logger.debug("Course title retrieved: {}", title);
        return title;
    }

    public CoursePage clickBookmarkIcon() {
        logger.info("Clicking bookmark icon");
        waitForElementToBeClickable(bookmarkIcon);
        bookmarkIcon.click();
        wait.until(ExpectedConditions.attributeContains(bookmarkIcon, "class", "BookmarkButton_activeBookmark__"));
        logger.debug("Bookmark icon clicked");
        return this;
    }
}