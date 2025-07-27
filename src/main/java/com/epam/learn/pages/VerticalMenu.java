package com.epam.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VerticalMenu extends AbstractPage{

    public static final String CATALOG_LINK_TEXT = "catalog";
    public static final String BOOKMARKS_LINK_TEXT = "bookmarks";

    @FindBy(xpath = "//*[contains(@class, 'AppMenuItem') and contains(@href, '" + CATALOG_LINK_TEXT + "')]")
    private WebElement catalogMenuLink;

    @FindBy(xpath = "//*[contains(@class, 'AppMenuItem') and contains(@href, '" + BOOKMARKS_LINK_TEXT + "')]")
    private WebElement bookmarksMenuLink;

    public VerticalMenu(WebDriver driver) {
        super(driver);
    }

    public VerticalMenu waitVerticalMenuCatalogVisibility() {
        waitForVisibility(catalogMenuLink);
        return this;
    }

    public CatalogPage clickCatalogLink() {
        waitForVisibility(catalogMenuLink);
        catalogMenuLink.click();
        return new CatalogPage(driver).waitScreenLoaded();
    }

    public BookmarksPage clickBookmarksLink() {
        waitForVisibility(bookmarksMenuLink);
        bookmarksMenuLink.click();
        return new BookmarksPage(driver).waitScreenLoaded();
    }
}
