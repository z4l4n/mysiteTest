package com.frontendart.managers.gui2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frontendart.common.BrowserTypes;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.locators.gui2.Gui2MainPageLocators;
import com.frontendart.locators.gui2.PublicationDetailsLocators;

/**
 * Manager class for select tests.
 *
 * @author Zoli
 *
 */
public class Gui2PublicationManager {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("Gui2PublicationManager");

    /**
     * Select random publication
     *
     * @return
     */
    public static WebElement selectRandomPublication() {
        final List<WebElement> displayedElements = getPublicationsAsWebelements();

        final int randInt = Utils.randInt(1, displayedElements.size());
        return displayedElements.get(randInt - 1);
    }

    /**
     * Check count of publication
     *
     * @return
     */
    public static void checkCountOfPublication() {

        org.junit.Assume.assumeFalse("There is no element in the publication list",
                "".equals(Utils.createGeneralWebElementFromString(Gui2MainPageLocators.EMPTY_PUBLICATION_LIST.toString()).getText()));

    }

    /**
     * Returns displayed publications as web elements
     *
     * @return
     */
    public static List<WebElement> getPublicationsAsWebelements() {
        Utils.waitForElementPresent(Gui2MainPageLocators.PUBLICATION_LIST);
        return Utils.createGeneralWebElementsFromEnum(Gui2MainPageLocators.PUBLICATION_LIST);
    }

    /**
     * Check publication details
     *
     */
    public static void checkPublicationDetails(final WebDriver driver) {
        // Select random publication and click on it
        final WebElement selectedPublication = selectRandomPublication();
        clickOnThisPublication(selectedPublication);

        // Get title and click
        final WebElement title = selectedPublication.findElement(By.xpath("./div/div[2]/h4/a"));
        final String titleAsText = title.getText();
        LOGGER.info("Kattintsunk a kiválasztott publikáció címére.");
        title.click();

        // Switch to new window
        final String winHandleBefore = driver.getWindowHandle();
        for (final String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        // Validation
        Utils.waitForElementPresent(PublicationDetailsLocators.TITLE_ROW);
        final String titleRowAsText = Utils.createGeneralWebElementFromEnum(PublicationDetailsLocators.TITLE_ROW).getText();
        Utils.myAssertTrue("A megjelenő oldal felső címsorának tartalmaznia kell a publikáció címét.",
                titleRowAsText.contains(titleAsText));

        final String titleAsBookDetail = Utils.createGeneralWebElementFromEnum(PublicationDetailsLocators.TITLE).getText();
        Utils.myAssertTrue("A megjelenő oldalon lévő publikáció adatainak tartalmaznia kell a publikáció címét.",
                titleAsBookDetail.contains(titleAsText));

        // Close and switch back to original window
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    /**
     * clicks on this publication
     *
     * @param selectedPublication
     */
    public static void clickOnThisPublication(final WebElement selectedPublication) {
        LOGGER.info("Kattintsunk egy publikációnál lévő checkboxba.");

        if (TestConfiguration.getDesiredBrowser().equals(BrowserTypes.FIREFOX)) {
            //  Utils.scrollIntoViewWithFalseParameter(selectedPublication.findElement(By.xpath("./div/div[1]/label")));

            Utils.clickToWebelementWithJavascriptExecutorWithYCoordAndOffset(
                    selectedPublication.findElement(By.xpath("./div/div[1]/label")), -200);

        } else {
            selectedPublication.findElement(By.xpath("./div/div/label")).click();
        }

        Utils.myAssertTrue("A kiválasztott publikációnak kijelölésre kell kerülnie.",
                selectedPublication.getAttribute("class").contains("selected"));
    }

    /**
     * navigates to author page and check
     *
     *
     */
    public static void navigateToAuthorAndCheckPage(final WebDriver driver) {
        // Select publication and click title
        final WebElement selectedPublication = selectRandomPublication();
        clickOnThisPublication(selectedPublication);
        LOGGER.info("Kattintsunk a kiválasztott publikáció címére.");
        selectedPublication.findElement(By.xpath("./div/div[2]/h4/a")).click();

        // Switch to new window
        final String winHandleBefore = driver.getWindowHandle();
        for (final String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        // Get first author and click
        Utils.waitForElementPresent(PublicationDetailsLocators.AUTHORS);
        final WebElement firstAuthor = Utils.createGeneralWebElementsFromEnum(PublicationDetailsLocators.AUTHORS).get(0);
        final String[] splitted = firstAuthor.getText().split(" ");
        final String firstAuthorAsString = splitted[0];
        LOGGER.info("Kattintsunk a publikáció első olyan szerzőjére, amihez tartozik hivatkozás.");
        firstAuthor.click();

        // Validate
        Utils.waitForElementPresent(Gui2MainPageLocators.PUBLICATION_LIST);
        Utils.defaultWait();
        final String nameFromTitleRow = driver.findElement(By.xpath("//*[@class='fixed-title']/h3")).getText();
        Utils.myAssertTrue("A jobb fenti címsávnak tartalmaznia kell a szerző nevét.", nameFromTitleRow.contains(firstAuthorAsString));

        // Close and switch back to original window
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    /**
     * Get publication years as list
     */
    public static List<Integer> getPublicationYearsAsList() {
        final List<WebElement> publications = getPublicationsAsWebelements();
        final List<Integer> list = new ArrayList<>();

        for (final WebElement publication : publications) {
            final String yearAsString = publication.findElement(By.xpath("./div/div/span")).getText();
            if (!yearAsString.isEmpty()) {
                final int year = Integer.parseInt(yearAsString);
                list.add(year);
            }
        }

        return list;
    }

    /**
     * checks publication sortings
     *
     */
    public static void checkPublicationSortings(final WebDriver driver) {
        // Create two lists (same)
        List<Integer> actualList = getPublicationYearsAsList();
        List<Integer> orderedList = actualList;

        // Sort the first one, and validate
        Collections.sort(orderedList);
        Collections.reverse(orderedList);
        Utils.myAssertEquals("A publikációknak évszám alapján csökkenő sorrendben kell megjelenniük.", orderedList, actualList);

        // Sort by year
        LOGGER.info("Kattintsunk az 'Év' szerinti rendező gombra.");
        driver.findElement(By.xpath("//*[@class='sortingBox']/span/span")).click();
        Utils.waitForElementPresent(Gui2MainPageLocators.PUBLICATION_LIST);

        // Validate
        actualList = getPublicationYearsAsList();
        orderedList = actualList;
        Utils.myAssertEquals("A publikációknak évszám alapján növekvő sorrendben kell megjelenniük.", orderedList, actualList);
    }
}
