import lib.CoreTestCase;
import lib.ui.MainPageObject;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }
/*
    @Test
    public void testSearchingResults() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Russia",
                "Cannot find search input",
                5
        );

        int search_items_count = MainPageObject.findElementsAndCount(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find article title",
                15
        );

        int search_results_without_value = MainPageObject.findElementsAndCountNotContainsText(
                By.id("org.wikipedia:id/page_list_item_title"),
                "There is no search value in line ",
                search_items_count,
                "Russia"
        );

        assertTrue(
                "We see no search value in search results",
                search_results_without_value == 0
        );

    }

    @Test
    public void testSwipeArticle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        MainPageObject.swipeUp(2000); //чем больше поставить времени, тем дольше будет идти свайп
        MainPageObject.swipeUp(2000);
        MainPageObject.swipeUp(2000);
    }*/
}
