package com.epam.learn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogPage extends AbstractPage{
    private static final Logger logger = LoggerFactory.getLogger(CatalogPage.class);

    public static final String CATEGORY_BLOCK_TITLE_TEXT = "Leadership";

    @FindBy(xpath = "//div[contains(@class, 'ExploreCategoriesItem_subItemText') and text()='General']")
    private WebElement subItemGeneral;
    @FindBy(xpath = "//h4[.//div[normalize-space(text())='" + CATEGORY_BLOCK_TITLE_TEXT + "']]")
    private WebElement leadershipBlock;

    public CatalogPage(WebDriver driver) {
        super(driver);
        logger.debug("Initialized CatalogPage");
    }

    public CatalogPage waitScreenLoaded() {
        logger.info("Waiting for Catalog page to load");
        waitForVisibility(leadershipBlock);
        logger.debug("Catalog page loaded");
        return this;
    }

    public CatalogPage clickLeadershipBlock() {
        logger.info("Clicking Leadership block");
        waitForElementToBeClickable(leadershipBlock);
        leadershipBlock.click();
        logger.debug("Leadership block clicked");
        return this;
    }

    public CoursePage clickCourseByName(String courseName) {
        logger.info("Clicking course: {}", courseName);
        String xpath = String.format("//h3[.//div[normalize-space(text())='%s']]", courseName);
        WebElement courseCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        courseCard.click();
        logger.debug("Course '{}' clicked", courseName);
        return new CoursePage(driver);
    }

    public CatalogPage clickGeneral() {
        logger.info("Clicking General sub-category");
        waitForElementToBeClickable(subItemGeneral);
        subItemGeneral.click();
        logger.debug("General sub-category clicked");
        return this;
    }
}