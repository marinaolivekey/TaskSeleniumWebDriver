package tests;

import com.epam.learn.pages.HomePage;
import com.epam.learn.pages.StartPage;
import com.epam.learn.util.ConfigReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TASK_SW_2_VerifyUserCanLoginAndSeesCorrectEmailInAvatar extends CommonConditions {
    private HomePage homePage;
    private String profileEmail;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        homePage = new StartPage(driver)
                .openPage()
                .waitForLoginButtonDisplayed()
                .clickSignIn()
                .login(ConfigReader.getLoginEmail())
                .waitProfileMenuDisplayed()
                .clickProfileAvatar();
        profileEmail = homePage.waitProfileDropDownIsDisplayed().getProfileEmail();
    }

    @Test(priority = 1)
    public void verifyDropDownIsDisplayed() {
        assertTrue(homePage.isDropdownBodyDisplayed(),
                "DropDown not Displayed");
    }

    @Test(priority = 2)
    public void verifyUserCanSeeCorrectEmailInAvatar() {
        assertEquals(profileEmail, ConfigReader.getLoginEmail(),
                String.format("User email in profile DDL does not match: expected /%s/",
                        ConfigReader.getLoginEmail()));
    }
}

