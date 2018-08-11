package lib.ui;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) //конструктор класса
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {

        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeofSwipe) {

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();  //передаем параметры экрана
        int x = size.width / 2; //ширина устройства, поделенная на 2 - середина по оси X
        int start_y = (int) (size.height * 0.8); //точка, находящаяся на 80% внизу экрана
        // + в скобках (int) одначает перевод значения в int (т.к. при умножении на 0.8 мложет получиться значение типа double)
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeofSwipe)
                .moveTo(x, end_y)
                .release()
                .perform(); //метод perform() передает всю команду на исполнение
    }

    public void swipeUpQuick() {

        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {

        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return; //выход из if
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(String locator, String error_message) {

        WebElement element = waitForElementPresent(locator,
                error_message,
                10);
        int left_x = element.getLocation().getX(); //записывает самую левую точку по оси Х
        int right_x = left_x + element.getSize().getWidth(); //прибавляем всю ширину
        int upper_y = element.getLocation().getY(); // получает самую высокую точку по оси Y
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    public int getAnountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementPresent(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 0);
        if (!element.isDisplayed()) {
            String default_message = "An element '" + locator + "' supposed to be already present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, int tinmeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, tinmeoutInSeconds);
        return element.getAttribute(attribute);
    }


    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAnountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public int findElementsAndCountNotContainsText(String locator, String error_message, int count, String value) {

        By by = this.getLocatorByString(locator);
        int countElements = 0;
        for (int i = 0; i < count; i++) {
            WebElement elementIndex = (WebElement) driver.findElements(by).get(i);
            String elementText = elementIndex.getText();
            if (!elementText.contains(value)) {
                System.out.println(error_message + i);
                countElements++;
            }
        }
        return countElements;
    }

    public int findElementsAndCount(String locator, String error_message, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);
        waitForElementPresent(locator, error_message, timeoutInSeconds);
        return driver.findElements(by).size();
    }

    public boolean assertTextInFieldPresent(String locator, String value, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        String element_text_value = element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected field text",
                value,
                element_text_value
        );
        return true;
    }

    public WebElement waitForElementRefreshed(String locator, String error_message, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(by))
        );
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2); //делит то, что передается в переменной
        //locator_with_type на то, что до ":" и после и передается в переменную exploded_locator
        String by_type = exploded_locator[0]; //в переменную by_type передается первая часть разделенного значения переменной locator_with_type
        String locator = exploded_locator[1]; //в переменную by_type передается вторая часть разделенного значения переменной locator_with_type

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else
            throw new IllegalArgumentException("Cannot get type of locator " + locator_with_type);
    }
}
