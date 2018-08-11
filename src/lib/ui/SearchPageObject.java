package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULTS_ELEMENT = "xpath://*[@text='No results found']",
            SEARCH_RESULT_WITH_TEXT_VALUE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text, '{SEARCHVALUE}')]",
            SEARCH_BEGIN_TEXT = "xpath://*[@text='Search and read the free encyclopedia in your language']";

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
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannod find and type into search input", 5);
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
