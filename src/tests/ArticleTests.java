package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_text = articlePageObject.getArticleTitle();

        //применение ассертов из класса TestCase, а не из библиотеки jUnit
        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_text);
    }

    @Test
    public void testSwipeToElement() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeUpToFooter();
    }

    //ex6
    @Test
    public void testAssertTitle() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Android");
        searchPageObject.clickByArticleWithSubstring("Android (operating system)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertArticleTitle();
    }
}
