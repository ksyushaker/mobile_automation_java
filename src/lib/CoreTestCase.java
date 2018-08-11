package lib;

import junit.framework.TestCase;

import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.AppiumDriver;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp(); //метод setUp() из TestCase
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown(); //метод tearDown() из TestCase
    }

    public void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void backgroundApp(int seconds) {
        driver.runAppInBackground(seconds);
    }

    private void skipWelcomePageForIOSApp() {
        if (Platform.getInstance().isIOS()) {
            WelcomePageObject welcomePage = new WelcomePageObject(driver);
            welcomePage.clickSkip();
        }
    }
}
