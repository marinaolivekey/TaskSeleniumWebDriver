package tests;

import com.epam.learn.pages.BookmarksPage;
import com.epam.learn.pages.CoursePage;
import com.epam.learn.pages.NavigationFacade;
import com.epam.learn.pages.VerticalMenu;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Test(groups = {"regression"})
public class TASK_SW_2_VerifyUserCanDeleteCourseFromBookmarks extends CommonConditions {
    private static final Logger logger = LoggerFactory
            .getLogger(TASK_SW_2_VerifyUserCanDeleteCourseFromBookmarks.class);
    private CoursePage coursePage;
    private BookmarksPage bookmarksPage;
    private static final String COURSE_TITLE = "What Does It Take to Be an Expert?";

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        logger.info("Setting up test environment");
        NavigationFacade navigationFacade = new NavigationFacade(driver);
        coursePage = navigationFacade
                .navigateToCoursePage(COURSE_TITLE)
                .clickBookmarkIcon();
        bookmarksPage = new VerticalMenu(driver)
                .clickBookmarksLink()
                .waitScreenLoaded()
                .waitForCourseCardByTitle(COURSE_TITLE);
        logger.debug("Test setup completed");
    }

    @Test
    public void verifyUserCanRemoveCourseFromBookmarks() {
        logger.info("Removing course '{}' from Bookmarks", COURSE_TITLE);
        bookmarksPage.clickBookmarkIconByCourseTitle(COURSE_TITLE);
        bookmarksPage.waitForBookmarkIconToBecomeInactive(COURSE_TITLE);
        Assert.assertFalse(bookmarksPage.isBookmarkIconActiveForCourse(COURSE_TITLE));
        logger.info("Course '{}' successfully removed from Bookmarks", COURSE_TITLE);
    }
}