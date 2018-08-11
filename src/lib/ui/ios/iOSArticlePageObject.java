package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        //todo OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        //todo NAME_OF_FOLDER_TO_ADD_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER_NAME}']";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
