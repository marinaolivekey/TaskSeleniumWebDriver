//package tests;
//
//import com.codeborne.selenide.Condition;
//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.SelenideElement;
//import com.codeborne.selenide.WebDriverRunner;
//import com.epam.learn.util.ConfigReader;
//import com.epam.learn.util.driver.DriverSingleton;
//import org.openqa.selenium.JavascriptExecutor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import static com.codeborne.selenide.Selenide.*;
//
//@Test(groups = {"regression"})
//public class TASK_SW_2_VerifyUserCanDeleteCourseFromBookmarksSelenide extends CommonConditions {
//    private static final Logger logger = LoggerFactory.getLogger(TASK_SW_2_VerifyUserCanDeleteCourseFromBookmarksSelenide.class);
//    private static final String COURSE_NAME = "What Does It Take to Be an Expert?";
//
//    @BeforeClass(inheritGroups = false, alwaysRun = true)
//    public void setUp() {
//        logger.info("Setting up test environment for Selenide test");
//
//        // Initialize Selenide with WebDriver from DriverSingleton
//        WebDriverRunner.setWebDriver(DriverSingleton.getDriver());
//
//        // Open start page
//        open(ConfigReader.getBaseUrl());
//        logger.debug("Opened start page: {}", ConfigReader.getBaseUrl());
//
//        // Clear browser state
//        Selenide.clearBrowserCookies();
//        Selenide.clearBrowserLocalStorage();
//
//        // Navigate to login page
//        SelenideElement loginButton = $x("//a[contains(@class, 'uui-button-box') and .//div[text()='Log In']]").shouldBe(Condition.visible);
//        highlightElement(loginButton);
//        loginButton.click();
//
//        // Perform login (manual password input)
//        SelenideElement usernameField = $("input#userName").shouldBe(Condition.visible);
//        highlightElement(usernameField);
//        usernameField.setValue(ConfigReader.getLoginEmail());
//        logger.info("Waiting for manual password input and submit (30 seconds)");
//        Selenide.sleep(30000); // Wait for manual password input and submit
//        $x("//*[contains(@class,'AppHeader_action')]").shouldBe(Condition.visible);
//        logger.debug("Login completed");
//
//        // Navigate to catalog
//        SelenideElement catalogMenuLink = $x("//*[contains(@class, 'AppMenuItem') and contains(@href, 'catalog')]").shouldBe(Condition.visible);
//        highlightElement(catalogMenuLink);
//        catalogMenuLink.click();
//
//        // Navigate to Leadership > General
//        SelenideElement leadershipBlock = $x("//h4[.//div[normalize-space(text())='Leadership']]").shouldBe(Condition.visible);
//        highlightElement(leadershipBlock);
//        leadershipBlock.click();
//        SelenideElement subItemGeneral = $x("//div[contains(@class, 'ExploreCategoriesItem_subItemText') and text()='General']").shouldBe(Condition.visible);
//        highlightElement(subItemGeneral);
//        subItemGeneral.click();
//
//        // Add course to bookmarks
//        SelenideElement courseCard = $x(String.format("//h3[.//div[normalize-space(text())='%s']]", COURSE_NAME)).shouldBe(Condition.visible);
//        highlightElement(courseCard);
//        courseCard.click();
//        SelenideElement bookmarkIcon = $("[data-testid='test-bookmarkIcon']").shouldBe(Condition.visible);
//        highlightElement(bookmarkIcon);
//        bookmarkIcon.click();
//        bookmarkIcon.shouldHave(Condition.match("active bookmark", e -> e.getAttribute("class").contains("BookmarkButton_activeBookmark")));
//        logger.debug("Course '{}' added to bookmarks", COURSE_NAME);
//
//        // Navigate to bookmarks page
//        SelenideElement bookmarksMenuLink = $x("//*[contains(@class, 'AppMenuItem') and contains(@href, 'bookmarks')]").shouldBe(Condition.visible);
//        highlightElement(bookmarksMenuLink);
//        bookmarksMenuLink.click();
//        $x("//*[contains(@class,'AppHeader_pageName')]//*[text()='Bookmarks']").shouldBe(Condition.visible);
//        $x(String.format("//h3[contains(@class, 'CatalogCardHeaderBlock_catalogCardHeaderTitle__N6fdc') and .//div[normalize-space(text())='%s']]", COURSE_NAME)).shouldBe(Condition.visible);
//        logger.debug("Course '{}' found in bookmarks", COURSE_NAME);
//    }
//
//    @Test
//    public void verifyUserCanRemoveCourseFromBookmarks() {
//        logger.info("Removing course '{}' from Bookmarks", COURSE_NAME);
//
//        // Remove course from bookmarks
//        SelenideElement courseBlock = $x(String.format("//h3[contains(@class, 'CatalogCardHeaderBlock_catalogCardHeaderTitle__N6fdc') and .//div[normalize-space(text())='%s']]/ancestor::div[contains(@class, 'CatalogCard_catalogCard__')]", COURSE_NAME)).shouldBe(Condition.visible);
//        SelenideElement bookmarkIcon = courseBlock.find("[data-testid='test-bookmarkIcon']").shouldBe(Condition.visible);
//        highlightElement(bookmarkIcon);
//        bookmarkIcon.click();
//
//        // Wait for bookmark icon to become inactive
//        bookmarkIcon.shouldNotHave(Condition.match("active bookmark", e -> e.getAttribute("class").contains("BookmarkButton_activeBookmark")));
//        logger.debug("Bookmark icon for course '{}' is inactive", COURSE_NAME);
//
//        // Verify bookmark icon is inactive
//        Assert.assertFalse(bookmarkIcon.has(Condition.match("active bookmark", e -> e.getAttribute("class").contains("BookmarkButton_activeBookmark"))),
//                String.format("Bookmark icon for course '%s' is still active", COURSE_NAME));
//        logger.info("Course '{}' successfully removed from Bookmarks", COURSE_NAME);
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void tearDownClass() {
//        logger.info("Tearing down test environment");
//        DriverSingleton.quitDriver();
//        logger.debug("WebDriver closed");
//    }
//
//    private void highlightElement(SelenideElement element) {
//        logger.debug("Highlighting element: {}", element);
//        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
//        js.executeScript("arguments[0].style.border='3px solid red'; arguments[0].style.backgroundColor='yellow';", element);
//        js.executeScript("arguments[0].style.border=''; arguments[0].style.backgroundColor='';", element);
//    }
//}