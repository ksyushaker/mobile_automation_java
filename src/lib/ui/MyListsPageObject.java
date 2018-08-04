package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TMP = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TMP = "//*[@text='{ARTICLE_TITLE}']",
            FOLDER_DESCRIPTION_TEXT_BLOCK = "org.wikipedia:id/item_reading_list_statistical_description";

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TMP.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TMP.replace("{ARTICLE_TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementRefreshed(
                By.id(FOLDER_DESCRIPTION_TEXT_BLOCK),
                "Cannot find description by folder " + name_of_folder,
                15
        );

        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_title__xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                By.xpath(article_title__xpath),
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_title__xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_title__xpath),
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        waitForArticleToAppearByTitle(article_title);
        String article_title__xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_title__xpath),
                "Cannot find saved article"
        );

        waitForArticleToDisappearByTitle(article_title);
    }
}
