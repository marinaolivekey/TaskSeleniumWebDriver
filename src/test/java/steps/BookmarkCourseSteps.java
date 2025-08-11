package steps;

import com.epam.learn.pages.BookmarksPage;
import com.epam.learn.pages.NavigationFacade;
import com.epam.learn.pages.VerticalMenu;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class BookmarkCourseSteps {

    private BookmarksPage bookmarksPage;

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        // Логика авторизации
    }

    @Given("the user is on the main page")
    public void the_user_is_on_the_main_page() {
        // Переход на главную страницу
    }

    @When("^the user navigates to course \"([^\"]+)\"$")
    public void the_user_navigates_to_course(String courseTitle) {
        NavigationFacade navigationFacade = new NavigationFacade(Hooks.driver);
        navigationFacade.navigateToCoursePage(courseTitle).clickBookmarkIcon();
    }

    @When("^the user bookmarks the course \"([^\"]+)\"$")
    public void the_user_bookmarks_the_course(String title) {
        VerticalMenu verticalMenu = new VerticalMenu(Hooks.driver);
        bookmarksPage = verticalMenu.clickBookmarksLink()
                .waitScreenLoaded()
                .waitForCourseCardByTitle(title);
    }

    @Then("^the course \"([^\"]+)\" should be present in the bookmarks$")
    public void the_course_should_be_present_in_the_bookmarks(String title) {
        Assert.assertTrue(bookmarksPage.isCoursePresent(title),
                String.format("Course '%s' not found on Bookmarks page", title));
    }
}