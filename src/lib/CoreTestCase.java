package lib;

import junit.framework.TestCase;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class CoreTestCase extends TestCase {

    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp(); //метод setUp() из TestCase
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = this.getDriverByPlatformEnv(AppiumUrl, capabilities);
//        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
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

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "6.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/arman/appium/mobile_automation_java/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone X");
            capabilities.setCapability("platformVersion", "11.4");
            capabilities.setCapability("app", "/Users/arman/appium/mobile_automation_java/apks/Wikipedia.app");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv(String url, DesiredCapabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");
        AppiumDriver driver;

        if (platform.equals(PLATFORM_ANDROID)) {
            driver = new AndroidDriver(new URL(url), capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(url), capabilities);
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return driver;
    }
}
