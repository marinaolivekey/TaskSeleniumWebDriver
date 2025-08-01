package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticalMenu extends AbstractPage{
    private static final Logger logger = LoggerFactory.getLogger(VerticalMenu.class);

    public static final String CATALOG_LINK_TEXT = "catalog";
    public static final String BOOKMARKS_LINK_TEXT = "bookmarks";

    @FindBy(xpath = "//*[contains(@class, 'AppMenuItem') and contains(@href, '" + CATALOG_LINK_TEXT + "')]")
    private WebElement catalogMenuLink;

    @FindBy(xpath = "//*[contains(@class, 'AppMenuItem') and contains(@href, '" + BOOKMARKS_LINK_TEXT + "')]")
    private WebElement bookmarksMenuLink;

    public VerticalMenu(WebDriver driver) {
        super(driver);
        logger.debug("Initialized VerticalMenu");
    }

    public VerticalMenu waitVerticalMenuCatalogVisibility() {
        logger.info("Waiting for catalog menu to be visible");
        waitForVisibility(catalogMenuLink);
        logger.debug("Catalog menu is visible");
        return this;
    }

    public CatalogPage clickCatalogLink() {
        logger.info("Clicking catalog link");
        waitForVisibility(catalogMenuLink);
        catalogMenuLink.click();
        logger.debug("Catalog link clicked");
        return new CatalogPage(driver).waitScreenLoaded();
    }

    public BookmarksPage clickBookmarksLink() {
        logger.info("Clicking bookmarks link");
        waitForVisibility(bookmarksMenuLink);
        bookmarksMenuLink.click();
        logger.debug("Bookmarks link clicked");
        return new BookmarksPage(driver).waitScreenLoaded();
    }
}