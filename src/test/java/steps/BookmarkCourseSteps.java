package steps;

import com.epam.learn.pages.BookmarksPage;
import com.epam.learn.pages.NavigationFacade;
import com.epam.learn.pages.VerticalMenu;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class BookmarkCourseSteps {

    private BookmarksPage bookmarksPage;

    @Given("the user is on the course page for {string}")
    public void the_user_is_on_the_course_page_for(String title) {
        System.out.println("DEBUG: title = " + title);
        NavigationFacade navigationFacade = new NavigationFacade(Hooks.driver);
        navigationFacade.navigateToCoursePage(title).clickBookmarkIcon();
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        // If login required, implement here
    }

    @When("the user bookmarks the course {string}")
    public void the_user_bookmarks_the_course(String title) {
        VerticalMenu verticalMenu = new VerticalMenu(Hooks.driver);
        bookmarksPage = verticalMenu.clickBookmarksLink()
                .waitScreenLoaded()
                .waitForCourseCardByTitle(title);
    }

    @Then("the course {string} should be present in the bookmarks")
    public void the_course_should_be_present_in_the_bookmarks(String title) {
        Assert.assertTrue(bookmarksPage.isCoursePresent(title),
                String.format("Course '%s' not found on Bookmarks page", title));
    }
}