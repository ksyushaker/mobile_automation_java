package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULTS_ELEMENT,
            SEARCH_RESULT_WITH_TEXT_VALUE_TPL,
            SEARCH_BEGIN_TEXT;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchWithTextValueElement(String substring) {
        return SEARCH_RESULT_WITH_TEXT_VALUE_TPL.replace("{SEARCHVALUE}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndSendKeys(SEARCH_INPUT,
                    search_line,
                    "Cannot find and type into search input", 5);
        } else {
            this.waitForElementAndClear(SEARCH_INPUT,
                    "Cannot find and type into search input",
                    5);
            this.waitForElementAndSendKeys(SEARCH_INPUT,
                    search_line,
                    "Cannot find and type into search input", 5);
        }
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request",
                15
        );

        return this.getAnountOfElements(
                SEARCH_RESULT_ELEMENT);
    }

    public int getAmountOfFoundArticlesWithTextRequest(String search_value) {

        String search_result_xpath = getResultSearchWithTextValueElement(search_value);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find anything by text value request " + search_value,
                15
        );

        return this.findElementsAndCount(
                search_result_xpath,
                "Cannot find article with search value " + search_value,
                15);
    }

    public void waitForEmptyResultsLabel() {

        this.waitForElementPresent(
                SEARCH_EMPTY_RESULTS_ELEMENT,
                "Cannot find empty result label by the request",
                15
        );
    }

    public void waitForNoSearchValueText() {

        this.waitForElementPresent(
                SEARCH_BEGIN_TEXT,
                "Cannot find no results before searching text",
                15
        );
    }

    public void assertThereIsNoResultsOfSearch() {
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results of search",
                10);
    }

    public void assertNoResultsAfterSearchCancel(String search_value) {

        String search_result_xpath = getResultSearchWithTextValueElement(search_value);
        this.waitForElementNotPresent(search_result_xpath,
                "We supposed not to find any results of search",
                10);
    }
}
