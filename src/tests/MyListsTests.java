package tests;

import org.junit.Test;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning Programming";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder, true);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
        }

        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    //ex5
    @Test
    public void testTwoArticlesSaving() {
        String first_article = "Android (operating system)";
        String second_article = "Appium";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Android");
        searchPageObject.clickByArticleWithSubstring(first_article);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
            articlePageObject.addArticleToMyList(name_of_folder, true);
        } else {
            articlePageObject.waitForTitleOnIOS(first_article);
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeSyncAlert();
        }
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();

        searchPageObject.typeSearchLine(second_article);
        searchPageObject.clickByArticleWithSubstring(second_article);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
            articlePageObject.addArticleToMyList(name_of_folder, false);
        } else {
            articlePageObject.waitForTitleOnIOS(second_article);
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
        }
        myListsPageObject.swipeByArticleToDelete(second_article);
        myListsPageObject.waitForArticleToDisappearByTitle(second_article);
        myListsPageObject.waitForArticleToAppearByTitle(first_article);
        myListsPageObject.clickByArticleInFolder(first_article);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
            String title_not_deleted_article = articlePageObject.getArticleTitle();

            assertEquals("Names of articles don't equal",
                    first_article,
                    title_not_deleted_article
            );
        } else {
            articlePageObject.waitForTitleOnIOS(first_article);
        }
    }
}
