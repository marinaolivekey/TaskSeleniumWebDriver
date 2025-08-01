package tests;

import com.epam.learn.pages.HomePage;
import com.epam.learn.pages.HomePageValidator;
import com.epam.learn.pages.LoginPage;
import com.epam.learn.pages.HighlightLoginPageDecorator;
import com.epam.learn.pages.LoginPageActions;
import com.epam.learn.pages.StartPage;
import com.epam.learn.util.ConfigReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = {"smoke"})
public class TASK_SW_2_VerifyUserCanLoginAndSeesCorrectEmailInAvatar extends CommonConditions {
    private static final Logger logger = LoggerFactory
            .getLogger(TASK_SW_2_VerifyUserCanLoginAndSeesCorrectEmailInAvatar.class);
    private HomePage homePage;
    private HomePageValidator homePageValidator;
    private String profileEmail;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        logger.info("Setting up test environment");
        LoginPageActions loginPage = new StartPage(driver)
                .openPage()
                .waitForLoginButtonDisplayed()
                .clickSignIn();
        loginPage = new HighlightLoginPageDecorator(loginPage, driver);
        homePage = loginPage
                .login(ConfigReader.getLoginEmail())
                .waitProfileMenuDisplayed()
                .clickProfileAvatar();
        homePageValidator = new HomePageValidator(driver);
        profileEmail = homePage.waitProfileDropDownIsDisplayed().getProfileEmail();
        logger.debug("Test setup completed, profile email: {}", profileEmail);
    }

    @Test(priority = 1)
    public void verifyDropDownIsDisplayed() {
        logger.info("Verifying dropdown is displayed");
        assertTrue(homePageValidator.isDropdownBodyDisplayed(),
                "DropDown not Displayed");
        logger.debug("Dropdown is displayed");
    }

    @Test(priority = 2)
    public void verifyUserCanSeeCorrectEmailInAvatar() {
        logger.info("Verifying profile email matches expected: {}", ConfigReader.getLoginEmail());
        assertEquals(profileEmail, ConfigReader.getLoginEmail(),
                String.format("User email in profile DDL does not match: expected /%s/",
                        ConfigReader.getLoginEmail()));
        logger.debug("Profile email matches expected");
    }
}