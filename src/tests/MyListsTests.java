package tests;

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
import org.junit.Test;

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

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_first_article = "Android";
        searchPageObject.typeSearchLine(search_first_article);
        searchPageObject.clickByArticleWithSubstring("Android (operating system)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();

        String title_first_article = articlePageObject.getArticleTitle();
        String name_of_folder = "Mobile Development";

        articlePageObject.addArticleToMyList(name_of_folder, true);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();

        String search_second_article = "Java";
        searchPageObject.typeSearchLine(search_second_article);
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();

        String title_second_article = articlePageObject.getArticleTitle();

        articlePageObject.addArticleToMyList(name_of_folder, false);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.openFolderByName(name_of_folder);

        myListsPageObject.swipeByArticleToDelete(title_first_article);

        myListsPageObject.waitForArticleToDisappearByTitle(title_first_article);
        myListsPageObject.waitForArticleToAppearByTitle(title_second_article);
        myListsPageObject.clickByArticleInFolder(title_second_article);

        articlePageObject.waitForTitleElement();
        String title_not_deleted_article = articlePageObject.getArticleTitle();

        assertEquals("Names of articles don't equal",
                title_second_article,
                title_not_deleted_article
        );
    }
}
