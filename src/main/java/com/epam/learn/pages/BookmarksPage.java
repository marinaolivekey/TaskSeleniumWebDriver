package com.epam.learn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BookmarksPage extends AbstractPage {
    private static final Logger logger = LoggerFactory.getLogger(BookmarksPage.class);

    @FindBy(xpath = "//*[contains(@class,'AppHeader_pageName')]//*[text()='Bookmarks']")
    private WebElement bookmarksTitle;

    @FindBy(xpath = "//h3[contains(@class, 'catalogCardHeaderTitle')]")
    private List<WebElement> courseTitles;

    private static final String COURSE_BLOCK_XPATH_TEMPLATE =
            "//h3[contains(@class, 'CatalogCardHeaderBlock_catalogCardHeaderTitle__N6fdc') and .//div[normalize-space(text())='%s']]/ancestor::div[contains(@class, 'CatalogCard_catalogCard__')]";

    public BookmarksPage(WebDriver driver) {
        super(driver);
        logger.debug("Initialized BookmarksPage");
    }

    public WebElement getCourseBlock(String courseTitle) {
        logger.debug("Locating course block for: {}", courseTitle);
        String xpath = String.format(COURSE_BLOCK_XPATH_TEMPLATE, courseTitle);
        WebElement courseBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        logger.debug("Course block found for: {}", courseTitle);
        return courseBlock;
    }

    public BookmarksPage waitScreenLoaded() {
        logger.info("Waiting for Bookmarks page to load");
        waitForVisibility(bookmarksTitle);
        logger.debug("Bookmarks page loaded");
        return this;
    }

    public boolean isCoursePresent(String courseName) {
        waitScreenLoaded();
        logger.info("Checking if course '{}' is present", courseName);
        boolean isPresent = courseTitles.stream()
                .anyMatch(title -> title.getText().trim().equals(courseName));
        logger.debug("Course '{}' presence: {}", courseName, isPresent);
        return isPresent;
    }

    public BookmarksPage waitForCourseCardByTitle(String courseTitle) {
        logger.info("Waiting for course card: {}", courseTitle);
        String xpath = String.format(COURSE_BLOCK_XPATH_TEMPLATE, courseTitle);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        logger.debug("Course card '{}' is visible", courseTitle);
        return this;
    }

    public BookmarksPage clickBookmarkIconByCourseTitle(String courseTitle) {
        logger.info("Clicking bookmark icon for course: {}", courseTitle);
        WebElement courseBlock = getCourseBlock(courseTitle);
        WebElement bookmarkIcon = courseBlock.findElement(By.cssSelector("[data-testid='test-bookmarkIcon']"));
        waitForElementToBeClickable(bookmarkIcon);
        bookmarkIcon.click();
        logger.debug("Bookmark icon clicked for: {}", courseTitle);
        return this;
    }

    public boolean isBookmarkIconActiveForCourse(String courseTitle) {
        logger.debug("Checking if bookmark is active for: {}", courseTitle);
        WebElement courseBlock = getCourseBlock(courseTitle);
        WebElement bookmarkIcon = courseBlock.findElement(By.cssSelector("[data-testid='test-bookmarkIcon']"));
        boolean isActive = bookmarkIcon.getAttribute("class").contains("BookmarkButton_activeBookmark__");
        logger.debug("Bookmark active status for '{}': {}", courseTitle, isActive);
        return isActive;
    }

    public BookmarksPage waitForBookmarkIconToBecomeInactive(String courseTitle) {
        logger.info("Waiting for bookmark icon to become inactive for: {}", courseTitle);
        wait.until(driver -> !isBookmarkIconActiveForCourse(courseTitle));
        logger.debug("Bookmark icon is inactive for: {}", courseTitle);
        return this;
    }
}
