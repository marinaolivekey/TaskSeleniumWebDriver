package com.epam.learn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CatalogPage extends AbstractPage{
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public static final String CATEGORY_BLOCK_TITLE_TEXT = "Leadership";

    @FindBy(xpath = "//div[contains(@class, 'ExploreCategoriesItem_subItemText') and text()='General']")
    private WebElement subItemGeneral;
    @FindBy(xpath = "//h4[.//div[normalize-space(text())='" + CATEGORY_BLOCK_TITLE_TEXT + "']]")
    private WebElement leadershipBlock;

    public CatalogPage waitScreenLoaded() {
        waitForVisibility(leadershipBlock);
        return this;
    }

    public CatalogPage clickLeadershipBlock() {
        leadershipBlock.click();
        return this;
    }

    public CoursePage clickCourseByName(String courseName) {
        String xpath = String.format("//h3[.//div[normalize-space(text())='%s']]", courseName);
        WebElement courseCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        courseCard.click();
        return new CoursePage(driver);
    }

    public CatalogPage clickGeneral() {
        subItemGeneral.click();
        return this;
    }
}
