package tests;

import org.junit.Test;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {
        if (this.Platform.isAndroid()){
            return; // если платформа Android, тест завершается (т/к/ этого функционала в приложении нет в версии под Android)
        }

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
