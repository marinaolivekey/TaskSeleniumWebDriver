package com.epam.learn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BookmarksPage extends AbstractPage {

    public BookmarksPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@class,'AppHeader_pageName')]//*[text()='Bookmarks']")
    private WebElement bookmarksTitle;

    @FindBy(xpath = "//h3[contains(@class, 'catalogCardHeaderTitle')]")
    private List<WebElement> courseTitles;

    private static final String COURSE_BLOCK_XPATH_TEMPLATE =
            "//h3[contains(@class, 'CatalogCardHeaderBlock_catalogCardHeaderTitle__N6fdc') and .//div[normalize-space(text())='%s']]/ancestor::div[contains(@class, 'CatalogCard_catalogCard__')]";

    public WebElement getCourseBlock(String courseTitle) {
        String xpath = String.format(COURSE_BLOCK_XPATH_TEMPLATE, courseTitle);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public BookmarksPage waitScreenLoaded() {
        waitForVisibility(bookmarksTitle);
        return this;
    }

    public boolean isCoursePresent(String courseName) {
        waitScreenLoaded();
        return courseTitles.stream()
                .anyMatch(title -> title.getText().trim().equals(courseName));
    }

    public BookmarksPage waitForCourseCardByTitle(String courseTitle) {
        String xpath = "//h3[contains(@class, 'CatalogCardHeaderBlock_catalogCardHeaderTitle') and .//div[text()='" + courseTitle + "']]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return this;
    }

    public BookmarksPage clickBookmarkIconByCourseTitle(String courseTitle) {
        WebElement courseBlock = getCourseBlock(courseTitle);
        WebElement bookmarkIcon = courseBlock.findElement(By.cssSelector("[data-testid='test-bookmarkIcon']"));
        bookmarkIcon.click();
        return this;
    }

    public boolean isBookmarkIconActiveForCourse(String courseTitle) {
        WebElement courseBlock = getCourseBlock(courseTitle);
        WebElement bookmarkIcon = courseBlock.findElement(By.cssSelector("[data-testid='test-bookmarkIcon']"));
        return bookmarkIcon.getAttribute("class").contains("BookmarkButton_activeBookmark__YvecO");
    }

    public BookmarksPage waitForBookmarkIconToBecomeInactive(String courseTitle) {
        wait.until(driver -> !isBookmarkIconActiveForCourse(courseTitle));
        return this;
    }
}
