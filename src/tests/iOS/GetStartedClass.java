package tests.iOS;

import org.junit.Test;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;

public class GetStartedClass extends iOSTestCase {

    @Test
    public void testPassThroughWelcome() {
        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForNewWaysToExploreText();
        welcomePage.clickNextButton();

        welcomePage.waitForAddOrEditPrefferedLangText();
        welcomePage.clickNextButton();

        welcomePage.waitForLearnMoreAboutDataCollectedText();
        welcomePage.clickGetStartedButton();
    }
}
