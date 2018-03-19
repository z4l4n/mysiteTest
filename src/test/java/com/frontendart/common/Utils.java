package com.frontendart.common;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.general.GeneralLocatorTypes;
import com.frontendart.locators.general.GeneralPageLocators;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.rightpanel.crud.RecordEditorMessageTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;

/**
 * Common functions
 *
 * @author gyizol
 *
 */
public class Utils extends JunitTestClass {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("Utils");

    /**
     * String for assert
     */
    private static final String EXPECTED_VALUE = "Elvárt eredmény: ";

    /**
     * Creates random int
     *
     * @param min
     * @param max
     * @return randomNum
     * @throws IllegalArgumentException
     */
    public static int randInt(final int min, final int max) throws IllegalArgumentException {
        final Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Creates random string.
     *
     * @param characterSet
     * @param length
     * @return result
     * @throws IllegalArgumentException
     */
    public static String randomString(final char[] characterSet, final int length) throws IllegalArgumentException {
        return RandomStringUtils.random(length, characterSet);
    }

    /**
     * Creates random string.
     *
     * @param characterSet
     * @param length
     * @return result
     * @throws IllegalArgumentException
     */
    public static String randomString() throws IllegalArgumentException {
        return RandomStringUtils.random(Constants.RANDOM_STRING_LENGTH, Constants.CHARSET);
    }

    /**
     * overriding assertFalse
     *
     * @param message
     * @param condition
     */
    public static boolean myAssertFalse(final String message, final boolean condition) {
        LOGGER.info(EXPECTED_VALUE + message);
        Assert.assertFalse(message, condition);
        return true;
    }

    /**
     * overriding assertTrue
     *
     * @param message
     * @param condition
     */
    public static boolean myAssertTrue(final String message, final boolean condition) {
        LOGGER.info(EXPECTED_VALUE + message);
        Assert.assertTrue(message, condition);
        return true;
    }

    /**
     * overriding assertEquals
     *
     * @param message
     * @param condition
     */
    public static boolean myAssertEquals(final String message, final Object arg1, final Object arg2) {
        LOGGER.info(EXPECTED_VALUE + message);
        Assert.assertEquals(message, arg1, arg2);
        return true;
    }

    /**
     * overriding assertNotEquals
     *
     * @param message
     * @param condition
     */
    public static boolean myAssertNotEquals(final String message, final Object arg1, final Object arg2) {
        LOGGER.info(EXPECTED_VALUE + message);
        Assert.assertNotEquals(message, arg1, arg2);
        return true;
    }

    /**
     * get the labels of general attribute
     */
    public static String getAllMyLabelsAsString(final GeneralTableAttributes locator) {
        String returnString = locator.getNames().get(0);
        for (int index = 1; index < locator.getNames().size(); index++) {
            returnString = returnString.concat("/").concat(locator.getNames().get(index));
        }

        return returnString;
    }

    /**
     * get the labels of general locator
     */
    public static String getAllMyLabelsAsString(final GeneralLocatorTypes locator) {
        String returnString = locator.getNames().get(0);
        for (int index = 1; index < locator.getNames().size(); index++) {
            returnString = returnString.concat("/").concat(locator.getNames().get(index));
        }

        return returnString;
    }

    /**
     * A metódus egy enum objektumból csinál ByElementet
     *
     * @author gyizol
     * @param enumObject
     * @return
     */
    public static By createGeneralByElementFromEnum(final GeneralLocatorTypes locator) {
        return By.xpath(locator.toString());
    }

    /**
     * A metódus egy enum objektumból csinál WebElementet
     *
     * @author gyizol
     * @param enumObject
     * @return
     */
    public static WebElement createGeneralWebElementFromEnum(final GeneralLocatorTypes locator) {
        return driver.findElement(createGeneralByElementFromEnum(locator));
    }

    /**
     * A metódus egy stringből csinál WebElementet
     *
     * @author gyizol
     * @param enumObject
     * @return
     */
    public static WebElement createGeneralWebElementFromString(final String locatorString) {
        return driver.findElement(By.xpath(locatorString));
    }

    /**
     * Enum-ból készít WebElement listát
     *
     * @author gyizol
     * @param driver
     * @param locator
     * @return elements
     */
    public static List<WebElement> createGeneralWebElementsFromEnum(final GeneralLocatorTypes locator) {
        return driver.findElements(createGeneralByElementFromEnum(locator));
    }

    /**
     * @author gyizol
     * @param locator
     * @throws InterruptedException
     *
     */
    public static boolean waitForAndClickOnGeneralWebElement(final GeneralLocatorTypes locator) {
        waitForElementVisible(locator);
        //waitForElementClickable(locator); // Én (Zalán) raktam vissza

        final WebElement myWebElement = createGeneralWebElementFromEnum(locator);

        LOGGER.info("Kattintsunk a következő elemre: " + getAllMyLabelsAsString(locator));

        //myWebElement.click();

        Actions actions = new Actions(driver);
        actions.moveToElement(myWebElement).click().perform();

        return true;
    }

    public static void waitUntilUsernameTooltipHide() {
        final WebElement usernameTooltip = driver.findElement(By.xpath(MainPageLocators.LOGGED_IN_TOOLTIP.toString()));
        boolean hidden = false;

        long startTime = System.currentTimeMillis(); //fetch starting time
        while (hidden == false || (System.currentTimeMillis() - startTime) < 10000) {
            if ("true".equals(usernameTooltip.getAttribute("hidden"))) {
                hidden = false;
            } else {
                hidden = true;
            }
        }

    }

    public static boolean isThisElementClickable(final GeneralLocatorTypes locator) {
        final By myByLocator = createGeneralByElementFromEnum(locator);
        ExpectedConditions.elementToBeClickable(myByLocator);
        return true;
    }

    public static void waitForElementClickable(final GeneralLocatorTypes locator) {
        final WebDriverWait wait = new WebDriverWait(driver, TestConfiguration.maxWaitTimeInSec);
        final By myByLocator = createGeneralByElementFromEnum(locator);
        wait.until(ExpectedConditions.elementToBeClickable(myByLocator));
    }

    /**
     * Waits for a general locator element to be present
     */
    public static void waitForElementVisible(final GeneralLocatorTypes locator) {
        final WebDriverWait wait = new WebDriverWait(driver, TestConfiguration.maxWaitTimeInSec);
        final By myByLocator = createGeneralByElementFromEnum(locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(myByLocator));
    }

    /**
     * Checks if a messagebox is active on the website and accepts it
     *
     * @return
     */
    public static boolean acceptMessageBoxIfVisible() {
        boolean returnValue = false;
        // Accept the message box if present
        if (isMessageBoxVisible()) {
            if (isThisElementPresent(GeneralPageLocators.MESSAGE_BOX_OK_BUTTON)) {
                waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_BOX_OK_BUTTON);
            } else if (isThisElementPresent(GeneralPageLocators.MESSAGE_BOX_YES_BUTTON)) {
                waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_BOX_YES_BUTTON);
            } else if (isThisElementPresent(GeneralPageLocators.MESSAGE_BOX_CANCEL_BUTTON)) {
                waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_BOX_CANCEL_BUTTON);
            }
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * Checks if a messagebox is active on the website and cancels it
     *
     * @return
     */
    public static boolean cancelMessageBoxIfVisible() {
        boolean returnValue = false;
        // Accept the message box if present
        if (isMessageBoxVisible()) {
            waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_BOX_CANCEL_BUTTON);
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * Dismiss the long run query popup message if exists
     */
    public static void dismissRunAlertIfExist() {
        if (Utils.isMessageBoxVisible()) {
            try {
                waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_BOX_SHOW_RESULTS_BUTTON);
            } catch (final NoSuchElementException e) {
                waitForAndClickOnGeneralWebElement(GeneralPageLocators.MESSAGE_BOX_OK_BUTTON);
            }
        }
    }

    /**
     * Checks if a messagebox is visible on the website
     *
     * @param driver
     * @return
     */
    public static boolean isMessageBoxVisible() {
        boolean returnValue = true;
        try {
            returnValue = isThisElementVisible(GeneralPageLocators.MESSAGE_BOX);
        } catch (final NoSuchElementException e) {
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Checks if an error window is visible on the website
     *
     * @param driver
     * @return
     */
    public static boolean isErrorWindowVisible() {
        boolean returnValue = true;
        try {
            returnValue = isThisElementVisible(GeneralPageLocators.ERROR_WINDOW);
        } catch (final NoSuchElementException e) {
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Checks if an warning window is visible on the website
     *
     * @param driver
     * @return
     */
    public static boolean isWarningWindowVisible() {
        boolean returnValue = true;
        try {
            returnValue = isThisElementVisible(GeneralPageLocators.WARNING_WINDOW);
        } catch (final NoSuchElementException e) {
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Checks if an invalid window is visible on the website
     *
     * @param driver
     * @return
     */
    public static boolean isInvalidWindowVisible() {
        boolean returnValue = true;
        try {
            returnValue = isThisElementVisible(GeneralPageLocators.INVALID_WINDOW);
        } catch (final NoSuchElementException e) {
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Waits for message box to be present and accepts it.
     *
     * @return
     */
    public static void waitForMessageBoxPresentAndAccept() {
        waitForMessageBoxToBePresent();
        Utils.defaultWait();
        acceptMessageBoxIfVisible();
    }

    /**
     * Waits for message box to be present and accepts it.
     *
     * @return
     */
    public static boolean waitForMessageBoxPresentAndAcceptBoolean() {
        boolean isAppear = false;
        waitForMessageBoxToBePresent();
        Utils.defaultWait();
        isAppear = acceptMessageBoxIfVisible();
        return isAppear;
    }

    /**
     * Waits for message box to be present and accepts it.
     *
     * @return
     */
    public static void waitForMessageBoxToBePresent() {
        waitForElementPresent(GeneralPageLocators.MESSAGE_BOX);
    }

    /**
     * Waits for message box to be dismissed.
     *
     * @return
     */
    public static void waitForMessageBoxToBeDismissed() {
        waitForInvisibilityOfThisElement(GeneralPageLocators.MESSAGE_BOX);
    }

    /**
     * Returns messagebox text
     *
     * @return
     */
    public static String getMessageBoxText() {
        return createGeneralWebElementFromEnum(GeneralPageLocators.MESSAGE_BOX_MESSAGE).getText();
    }

    /**
     * Checks if a messagebox is active on the website
     *
     * @return
     */
    public static boolean isMessageBoxPresentWithText(final GeneralLocatorTypes locator) {
        waitForMessageBoxToBePresent();
        final String messageBoxText = getMessageBoxText();
        LOGGER.info("Egy figyelmeztető üzenet jelenik meg az alábbi szöveggel: \"" + messageBoxText + "\"");

        if (locator != RecordEditorMessageTypes.EXISTING_DOI) {
            acceptMessageBoxIfVisible();
        }

        return getAllMyLabelsAsString(locator).contains(messageBoxText);
    }

    /**
     * Checks if a messagebox is not active with this text
     *
     * @return
     */
    public static boolean isMessageBoxNotPresentWithText(final GeneralLocatorTypes locator) {
        boolean returnValue = true;
        waitMillisec(2000);
        if (isMessageBoxVisible()) {
            final String messageBoxText = getMessageBoxText();
            if ((!messageBoxText.isEmpty()) && (getAllMyLabelsAsString(locator).contains(messageBoxText))) {
                returnValue = false;
                acceptMessageBoxIfVisible();
            }
        }

        return returnValue;
    }

    /**
     * Checks if alert is present
     *
     * @return
     */
    public static boolean isAlertPresent() {
        boolean returnValue = false;
        try {
            driver.switchTo().alert();
            returnValue = true;
        } catch (final NoAlertPresentException Ex) {
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Logs redmine issue(s)
     *
     * @param arrayList
     */
    public static void writeMyRedmineIssues(final String issues) {
        LOGGER.info("Ez a teszteset az alábbi redmine issue(k) alapján készült: ");
        final String[] splitted = issues.split("#");
        for (int index = 1; index < splitted.length; index++) {
            LOGGER.info(" - https://redmine.mt2.dsd.sztaki.hu:18018/issues/" + splitted[index]);
        }

    }

    /**
     * Waits
     *
     * @param millisec
     */
    public static void waitMillisec(final int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (final InterruptedException e) {
            fail("The test failed!" + e.getMessage());
        }
    }

    /**
     * Waits default time
     */
    public static void defaultWait() {
        waitMillisec(TestConfiguration.defaultWaitTimeInSec * 1000);
    }

    /**
     * Returns actual role
     *
     * @param millisec
     */
    public static Roles getActualRole() {
        return TestConfiguration.role;
    }

    /**
     * Returns true if actual role is central admin
     *
     * @return
     */
    public static boolean actualRoleIsCentralAdmin() {
        return Roles.CENTRAL_ADMIN.equals(getActualRole());
    }

    /**
     * Writes text to a general field
     *
     * @param text
     * @param locator
     */
    public static void writeTextToThisField(final String text, final GeneralLocatorTypes locator) {
        LOGGER.info("Írjuk be a következő szöveget a \"" + getAllMyLabelsAsString(locator) + "\" mezőbe: " + text);
        waitForElementVisible(locator);
        final By myLocator = createGeneralByElementFromEnum(locator);
        driver.findElement(myLocator).clear();
        driver.findElement(myLocator).sendKeys(text);
    }

    /**
     * Returns true if string contains locator (hungarian or english name)
     *
     * @param text
     * @param column
     * @return
     */
    public static boolean isThisStringListContainsThisAttribute(final List<String> text, final GeneralTableAttributes column) {
        boolean returnValue = false;

        // Get names to find
        String hungarianName = column.getNames().get(0);
        String hungarianNameWithAsterisk = column.getNames().get(0);
        String englishName = column.getNames().get(1);
        String englishNameWithAsterisk = column.getNames().get(1);

        // If we need to find it with ":" characters, we add that
        if (text.get(0).contains(":")) {
            hungarianName = hungarianName.concat(":");
            hungarianNameWithAsterisk = hungarianNameWithAsterisk.concat(":*");
            englishName = englishName.concat(":");
            englishNameWithAsterisk = englishNameWithAsterisk.concat(":*");
        }

        // Find
        if ((text.contains(hungarianName)) || text.contains(englishName) || (text.contains(hungarianNameWithAsterisk))
                || (text.contains(englishNameWithAsterisk))) {
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * Converts a webelement array to string array
     *
     * @param webelementValues
     * @return doubleValues
     */
    public static List<String> convertThisWebElementArrayToStringArray(final List<WebElement> webelementValues) {
        final List<String> stringValues = new ArrayList<>();
        for (final WebElement myElement : webelementValues) {
            stringValues.add(myElement.getText());
        }
        return stringValues;
    }

    /**
     * Converts string array to int array
     *
     * @param stringValues
     * @return
     */
    public static List<Integer> convertThisStringArrayToIntArray(final List<String> stringValues) {
        final List<Integer> intValues = new ArrayList<>();
        for (final String myElement : stringValues) {
            intValues.add(Integer.parseInt(myElement));
        }
        return intValues;
    }

    /**
     * Returns true if element is visible
     *
     * @param dateLabel
     * @return
     */
    public static boolean isThisElementVisible(final GeneralLocatorTypes locator) {
        return createGeneralWebElementFromEnum(locator).isDisplayed();
    }

    /**
     * Returns true if element is present
     *
     * @param driver
     * @param newButton
     * @return
     */
    public static boolean isThisElementPresent(final GeneralLocatorTypes locator) {
        return createGeneralWebElementsFromEnum(locator).size() > 0;
    }

    /**
     * Waits for element(s) to be present - looks every 0.5 seconds, until max wait time
     *
     * @param driver
     * @param newButton
     * @return
     */
    public static void waitForElementPresent(final GeneralLocatorTypes locator) {
        int tryCount = 0;
        while (!isThisElementPresent(locator)) {
            tryCount++;
            waitMillisec(500);
            if (tryCount > (TestConfiguration.maxWaitTimeInSec * 2)) {
                fail("This element is not available in " + TestConfiguration.maxWaitTimeInSec + " seconds: "
                        + Utils.getAllMyLabelsAsString(locator));
            }
        }
    }

    /**
     * Returns the name of this button
     *
     * @param driver
     * @param newButton
     * @return
     */
    public static String getNameOfThisButton(final WebElement element) {
        return element.findElement(By.xpath("./span/span/span[2]")).getText();
    }

    /**
     * Clicks on the given row
     *
     * @param rowToClick
     */
    public static void clickOnThisRow(final WebElement rowToClick) {
        Utils.scrollIntoView(rowToClick);
        Utils.defaultWait();
        rowToClick.findElement(By.xpath("./tbody/tr/td/div")).click();
    }

    /**
     * Waits for staleness of element
     *
     * @param locator
     */
    public static void waitForStalenessOfThisElement(final GeneralLocatorTypes locator) {
        final WebDriverWait wait = new WebDriverWait(driver, TestConfiguration.maxWaitTimeInSec);
        final WebElement myWebElement = createGeneralWebElementFromEnum(locator);
        wait.until(ExpectedConditions.stalenessOf(myWebElement));
    }

    /**
     * Waits for staleness of element
     *
     * @param locator
     */
    public static void waitForStalenessOfThisElement(final WebElement element) {
        final WebDriverWait wait = new WebDriverWait(driver, TestConfiguration.maxWaitTimeInSec);
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * Waits for invisibility of element
     *
     * @param driver
     * @param locator
     */
    public static void waitForInvisibilityOfThisElement(final GeneralLocatorTypes locator) {
        final WebDriverWait wait = new WebDriverWait(driver, TestConfiguration.maxWaitTimeInSec);
        final By myByElement = createGeneralByElementFromEnum(locator);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(myByElement));
    }

    /**
     * Waits for right loader disappear
     */
    public static void waitForRightPanelLoadingFinished() {
        defaultWait();
        waitForInvisibilityOfThisElement(GeneralPageLocators.RIGHT_PANEL_LOAD_MASK);
    }

    /**
     * Closes driver
     *
     * @param driver
     */
    public static void closeDriver() {
        // Quit driver
        int attempts = 1;
        boolean driverIsClosed = false;
        while ((!driverIsClosed) && (attempts < 6)) {
            LOGGER.info("Closing webdriver try count: " + attempts);
            try {
                driverIsClosed = true;
                driver.close();
                driver.quit();
            } catch (final WebDriverException e) {
                driverIsClosed = false;
            }
            attempts++;
        }
    }

    /**
     * Switches to active element
     *
     * @return
     */
    public static WebElement switchToActiveElement() {
        return driver.switchTo().activeElement();
    }

    /**
     * Refresh
     */
    public static void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Navigate to URL
     *
     * @param newPasswordLink
     */
    public static void navigateToThisURL(final String url) {
        driver.get(url);
    }

    /**
     * Creates action
     *
     * @return
     */
    public static Actions createActionForDriver() {
        return new Actions(driver);
    }

    /**
     * Checks for error window. If it is present, fails test, and refreshes the page
     */
    public static void checkForErrorsWindow() {
        if (isErrorWindowVisible()) {
            LOGGER.info("There is an error window present on the page!");
            Utils.waitForAndClickOnGeneralWebElement(GeneralPageLocators.ERROR_WINDOW_REFRESH_BUTTON);
            RecordSelectionManager.waitUntilTableIsReady();
            fail("The test failed, because there was an error window present on the page!");

        }
    }

    public static void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void scrollIntoViewWithFalseParameter(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void scrollTotalDown() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(
                "var body = document.body,html document.documentElement;var height = Math.max( body.scrollHeight,body.offsetHeight,html.clientHeight, html.scrollHeight, html.offsetHeight );window.scrollBy(0,height)");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void sendKeysToWebelementWithAction(WebElement element, Keys sendKeysParameter) {

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(sendKeysParameter);
        actions.build().perform();

    }

    public static void clickToWebelementWithAction(WebElement element) {

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.build().perform();

    }

    public static void clickToWebelementWithJavascriptExecutor(WebElement element) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    }

    public static void clickToWebelementWithJavascriptExecutorWithXCoord(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + element.getLocation().x + ")");
        element.click();

    }

    public static void clickToWebelementWithJavascriptExecutorWithYCoord(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + element.getLocation().y + ")");
        element.click();

    }

    public static void clickToWebelementWithJavascriptExecutorWithYCoordAndOffset(WebElement element, int offset) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + element.getLocation().y + offset + ")");
        Utils.defaultWait();
        element.click();

    }

    public static void switchTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(0));
        }
    }

}
