package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeNavigationBar[@name='Wikipedia, scroll to top of Explore']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULTS_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        //todo  SEARCH_RESULT_WITH_TEXT_VALUE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text, '{SEARCHVALUE}')]";
        //todo  SEARCH_BEGIN_TEXT = "xpath://*[@text='Search and read the free encyclopedia in your language']";
    }

    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
