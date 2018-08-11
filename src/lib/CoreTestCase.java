package lib;

import junit.framework.TestCase;

import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.AppiumDriver;

public class CoreTestCase extends TestCase {

    protected Platform Platform;
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp(); //метод setUp() из TestCase
        this.Platform = new Platform();
        driver = this.Platform.getDriver();
        rotateScreenPortrait();
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

}
