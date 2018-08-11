package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TMP = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TMP = "xpath://*[@text='{ARTICLE_TITLE}']";
        FOLDER_DESCRIPTION_TEXT_BLOCK = "id:org.wikipedia:id/item_reading_list_statistical_description";
    }

    public AndroidMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
