package com.epam.learn.pages;

import com.epam.learn.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavigationFacade {
    private static final Logger logger = LoggerFactory.getLogger(NavigationFacade.class);
    private final WebDriver driver;

    public NavigationFacade(WebDriver driver) {
        this.driver = driver;
        logger.debug("Initialized NavigationFacade");
    }

    public CoursePage navigateToCoursePage(String courseTitle) {
        logger.info("Navigating to course page: {}", courseTitle);
        LoginPageActions loginPage = new StartPage(driver)
                .openPage()
                .waitForLoginButtonDisplayed()
                .clickSignIn();
        loginPage = new HighlightLoginPageDecorator(loginPage, driver);
        loginPage
                .login(ConfigReader.getLoginEmail())
                .waitProfileMenuDisplayed();
        CoursePage coursePage = new VerticalMenu(driver)
                .waitVerticalMenuCatalogVisibility()
                .clickCatalogLink()
                .waitScreenLoaded()
                .clickLeadershipBlock()
                .clickGeneral()
                .clickCourseByName(courseTitle)
                .waitScreenLoaded();
        logger.debug("Navigation to course page '{}' completed", courseTitle);
        return coursePage;
    }
}