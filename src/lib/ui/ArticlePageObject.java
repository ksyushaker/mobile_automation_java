package lib.ui;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            TITLE_TPL,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            NAME_OF_FOLDER_TO_ADD_TPL;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderToAddName(String substring) {
        return NAME_OF_FOLDER_TO_ADD_TPL.replace("{FOLDER_NAME}", substring);
    }

    public static String getArticleText(String title_text) {
        return TITLE_TPL.replace("{search_text}", title_text);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public WebElement waitForTitleOnIOS(String title) {

        String title_locator = getArticleText(title);
        return this.waitForElementPresent(title_locator, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void assertArticleTitle() {
        this.assertElementPresent(TITLE, "The article title hasn't appeared yet");
    }

    public void swipeUpToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        }
    }

    public void addArticleToMyList(String name_of_folder, boolean first_article) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        if (first_article == true) {
            this.waitForElementAndClick(
                    ADD_TO_MY_LIST_OVERLAY,
                    "Cannot find 'Got it' tip overlay",
                    5
            );

            this.waitForElementAndClear(
                    MY_LIST_INPUT,
                    "Cannot find input to set name of articles folder",
                    5
            );

            this.waitForElementAndSendKeys(
                    MY_LIST_INPUT,
                    name_of_folder,
                    "Cannot put text into articles folder input",
                    5
            );

            this.waitForElementAndClick(
                    MY_LIST_OK_BUTTON,
                    "Cannot press OK button",
                    5
            );
        } else {

            String folder_name_xpath = getFolderToAddName(name_of_folder);
            this.waitForElementAndClick(
                    folder_name_xpath,
                    "Cannot find saved folder in overlay",
                    5
            );
        }
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }

    public void closeSyncAlert() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close sync alert",
                10
                );
    }
}

