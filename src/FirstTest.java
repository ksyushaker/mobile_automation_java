import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

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
        MainPageObject.swipeUp(2000);
        MainPageObject.swipeUp(2000);

    }




    @Test
    public void testSearchingCancel() {
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
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text, 'Russia')]"),
                "Cannot find article title",
                15
        );

        Assert.assertTrue(
                "We see unexpected search results",
                search_items_count != 0
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text, 'Russia')]"),
                "Search not canceled",
                5
        );
    }

    @Test
    public void testTwoArticlesSaving() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_first_article = "Android";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_first_article,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Android (operating system)']"),
                "Cannot find 'Android (operating system)' topic searching by '" + search_first_article + "'",
                15
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find first article title",
                15
        );

        String title_first_article = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot get first article title",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open first article options",
                5

        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add first article to reading list",
                5

        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5

        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Mobile Development";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put test into articles folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5

        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close first article, cannot find X link",
                5

        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_second_article = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_second_article,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by '" + search_second_article + "'",
                15
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find second article title",
                15
        );

        String title_second_article = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot get second article title",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open second article options",
                5

        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add second article to reading list",
                5

        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + name_of_folder + "']"),
                "Cannot find saved folder in overlay",
                5

        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close second article, cannot find X link",
                5

        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my lists",
                5

        );

        MainPageObject.waitForElementRefreshed(
                By.id("org.wikipedia:id/item_reading_list_statistical_description"),
                "Cannot find folder description",
                20
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='" + title_first_article + "']"),
                "Cannot find first article in created folder",
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='" + title_first_article + "']"),
                "Cannot find saved first article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='" + title_first_article + "']"),
                "Cannot delete saved first article",
                5
        );

        Assert.assertTrue("Cannot find not deleted article",
                driver.getPageSource().contains(title_second_article));

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + title_second_article + "']"),
                "Cannot find title of not deleted article",
                15
        );

        String title_not_deleted_article = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot get not deleted article title",
                5
        );

        Assert.assertEquals("Names of articles don't equal",
                title_second_article,
                title_not_deleted_article
        );
    }

    @Test
    public void testAssertTitle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_article = "Android";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_article,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Android (operating system)']"),
                "Cannot find 'Android (operating system)' topic searching by '" + search_article + "'",
                15
        );

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "The article title hasn't appeared yet"
        );
    }

}
