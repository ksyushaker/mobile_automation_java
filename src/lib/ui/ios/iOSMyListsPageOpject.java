package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageOpject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TMP = "xpath://XCUIElementTypeLink[contains(@name, '{ARTICLE_TITLE}')]";
    }

    public iOSMyListsPageOpject(AppiumDriver driver) {
        super(driver);
    }
}
