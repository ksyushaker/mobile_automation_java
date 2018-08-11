package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TMP,
            ARTICLE_BY_TITLE_TMP,
            FOLDER_DESCRIPTION_TEXT_BLOCK;

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TMP.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TMP.replace("{ARTICLE_TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementRefreshed(
                FOLDER_DESCRIPTION_TEXT_BLOCK,
                "Cannot find description by folder " + name_of_folder,
                15
        );

        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_title__xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_title__xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_title_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_title__xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_title__xpath,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_title__xpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void clickByArticleInFolder(String article_name) {
        String article_in_folder_xpath = getSavedArticleXpathByTitle(article_name);
        this.waitForElementAndClick(article_in_folder_xpath, "Cannot find title of not deleted article", 5);
    }
}
