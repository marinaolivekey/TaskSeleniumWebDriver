package tests;

import com.epam.learn.pages.*;
import com.epam.learn.util.ConfigReader;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.epam.learn.pages.AbstractPage.PAGE_LOAD_TIME_OUT_IN_SECONDS;

public class TASK_SW_2_VerifyUserCanAddCourseToBookmarks extends CommonConditions{
    private HomePage homePage;
    private CoursePage coursePage;
    private BookmarksPage bookmarksPage;

    private static final String COURSE_TITLE = "Getting Ready for Assessment";

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
    public void verifyUserCanSeeAddedCourseInBookmarks() {
        Assert.assertTrue(bookmarksPage.isCoursePresent(COURSE_TITLE),
                String.format("Курс '%s' не найден на странице Bookmarks", COURSE_TITLE));
    }

    @AfterMethod(inheritGroups = false, alwaysRun = true)
    public void cleanUp() {
        bookmarksPage.clickBookmarkIconByCourseTitle(COURSE_TITLE);
        bookmarksPage.waitForBookmarkIconToBecomeInactive(COURSE_TITLE);
    }
}
