package tests;

import com.epam.learn.pages.*;
import com.epam.learn.util.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Test(groups = {"regression"})
public class TASK_SW_2_VerifyUserCanAddCourseToBookmarks extends CommonConditions{
    private static final Logger logger = LoggerFactory
            .getLogger(TASK_SW_2_VerifyUserCanAddCourseToBookmarks.class);
    private HomePage homePage;
    private CoursePage coursePage;
    private BookmarksPage bookmarksPage;

    private static final String COURSE_TITLE = "Getting Ready for Assessment";

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        logger.info("Setting up test environment");
        homePage = new StartPage(driver)
                .openPage()
                .waitForLoginButtonDisplayed()
                .clickSignIn()
                .login(ConfigReader.getLoginEmail())
                .waitProfileMenuDisplayed();

        coursePage = new VerticalMenu(driver)
                .waitVerticalMenuCatalogVisibility()
                .clickCatalogLink()
                .waitScreenLoaded()
                .clickLeadershipBlock()
                .clickGeneral()
                .clickCourseByName(COURSE_TITLE)
                .waitScreenLoaded()
                .clickBookmarkIcon();

        bookmarksPage = new VerticalMenu(driver)
                .clickBookmarksLink()
                .waitScreenLoaded()
                .waitForCourseCardByTitle(COURSE_TITLE);
        logger.debug("Test setup completed");
    }

    @Test
    public void verifyUserCanSeeAddedCourseInBookmarks() {
        logger.info("Verifying course '{}' is present in Bookmarks", COURSE_TITLE);
        Assert.assertTrue(bookmarksPage.isCoursePresent(COURSE_TITLE),
                String.format("Курс '%s' не найден на странице Bookmarks", COURSE_TITLE));
        logger.info("Course '{}' found in Bookmarks", COURSE_TITLE);
    }

    @AfterMethod(inheritGroups = false, alwaysRun = true)
    public void cleanUp() {
        logger.info("Cleaning up: removing course '{}' from Bookmarks", COURSE_TITLE);
        bookmarksPage.clickBookmarkIconByCourseTitle(COURSE_TITLE);
        bookmarksPage.waitForBookmarkIconToBecomeInactive(COURSE_TITLE);
        logger.debug("Cleanup completed");
    }
}
