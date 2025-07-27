package tests;

import com.epam.learn.pages.*;
import com.epam.learn.util.ConfigReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TASK_SW_2_VerifyUserCanDeleteCourseFromBookmarks extends CommonConditions{
    private HomePage homePage;
    private CoursePage coursePage;
    private BookmarksPage bookmarksPage;

    private static final String COURSE_TITLE = "What Does It Take to Be an Expert?";

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PAGE_LOAD_TIME_OUT_IN_SECONDS));
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
    }

    @Test
    public void verifyUserCanRemoveCourseFromBookmarks() {
        bookmarksPage.clickBookmarkIconByCourseTitle(COURSE_TITLE);
        bookmarksPage.waitForBookmarkIconToBecomeInactive(COURSE_TITLE);
        Assert.assertFalse(bookmarksPage.isBookmarkIconActiveForCourse(COURSE_TITLE));
    }
}
