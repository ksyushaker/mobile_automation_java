package lib;

import junit.framework.TestCase;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class iOSTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp(); //метод setUp() из TestCase
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone X");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("app", "/Users/arman/appium/mobile_automation_java/apks/Wikipedia.app");

        driver = new IOSDriver(new URL(AppiumUrl), capabilities);
        rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown(); //метод tearDown() из TestCase
    }

    public void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void backgroundApp(int seconds){
        driver.runAppInBackground(seconds);
    }
}
