package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageOpject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TMP = "xpath://XCUIElementTypeLink[contains(@name, '{ARTICLE_TITLE}')]";
        //todo ?? FOLDER_DESCRIPTION_TEXT_BLOCK = "id:org.wikipedia:id/item_reading_list_statistical_description";
    }

    public iOSMyListsPageOpject(AppiumDriver driver) {
        super(driver);
    }
}
