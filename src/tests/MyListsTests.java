package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning Programming";
        articlePageObject.addArticleToMyList(name_of_folder, true);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    //ex5
    @Test
    public void testTwoArticlesSaving() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String search_first_article = "Android";
        searchPageObject.typeSearchLine(search_first_article);
        searchPageObject.clickByArticleWithSubstring("Android (operating system)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
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

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
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
