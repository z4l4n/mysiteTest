package com.frontendart.managers.main.rightpanel.view;

import static org.junit.Assert.assertTrue;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.common.Utils;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.leftpanel.RecordSelectionLocators;
import com.frontendart.locators.main.rightpanel.view.SortLocators;
import com.frontendart.locators.records.attributes.general.AuthorRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.ReportTemplateRecordAttributes;
import com.frontendart.managers.general.GeneralTableManager;

/**
 * Manager class for sort tests
 *
 * @author Zoli
 *
 */
public class SortManager {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("SortManager");

    /**
     * number of sorts present
     *
     */
    public static int getNumberOfSorters() {
        return Utils.createGeneralWebElementsFromEnum(SortLocators.ALL_SORTERS).size();

    }

    /**
     * checks sort numbers
     */
    public static void checkNumberOfSorters(final int expectedNumber) {
        Utils.myAssertEquals(expectedNumber + "db rendezésnek kell láthatónak lennie.", expectedNumber, getNumberOfSorters());

        if (expectedNumber < 3) {
            Utils.myAssertFalse(
                    "A " + SortLocators.NEW_SORTING_BUTTON.getNames().get(0) + "/" + SortLocators.NEW_SORTING_BUTTON.getNames().get(1)
                            + " nevű gombnak aktívnak kell lennie.",
                    Utils.createGeneralWebElementFromEnum(SortLocators.NEW_SORTING_BUTTON).getAttribute("class")
                            .contains("x-item-disabled"));
        } else {
            Utils.myAssertTrue(
                    "A " + SortLocators.NEW_SORTING_BUTTON.getNames().get(0) + "/" + SortLocators.NEW_SORTING_BUTTON.getNames().get(1)
                            + " nevű gombnak inaktívnak kell lennie.",
                    Utils.createGeneralWebElementFromEnum(SortLocators.NEW_SORTING_BUTTON).getAttribute("class")
                            .contains("x-item-disabled"));
        }
    }

    /**
     * Closes a sorting
     *
     * @param sortingIndex
     *
     */
    public static void closeThisSorter(final int sortingIndex) {
        LOGGER.info("Zárjuk be a következő sorszámú rendezést: " + sortingIndex);
        final WebElement sorter = Utils.createGeneralWebElementsFromEnum(SortLocators.ALL_SORTERS).get(sortingIndex - 1);
        sorter.findElement(By.xpath(SortLocators.CLOSE_SORTER.toString())).click();
    }

    /**
     * Closes a sorting
     *
     * @param sortingIndex
     *
     */
    public static void closeAllSorters() {
        if (getNumberOfSorters() > 0) {
            Utils.waitForAndClickOnGeneralWebElement(SortLocators.DELETE_ALL_SORTING_BUTTON);
        }
    }

    /**
     * Check multiple sorting
     *
     * @param recordType
     * @param selectedLocators
     *
     */
    public static void checkMultipleSorting(final GeneralRecordTypes recordType, final List<GeneralTableAttributes> selectedAttributes) {
        final List<Integer> colPositions = GeneralTableManager.getColumnDivPositionsForTheseLocators(selectedAttributes);

        LOGGER.info("Elvárt eredmény: A táblázat sorainak növekvő sorrendbe kell rendeződnie az alábbi szempont szerint: "
                + Utils.getAllMyLabelsAsString(selectedAttributes.get(0)));
        doCompare(colPositions);
    }

    /**
     * Compare by first attribute
     *
     */
    public static void doCompare(final List<Integer> attributePositions) {
        // Get rows
        final List<WebElement> myGridPanelRows = GeneralTableManager.getAllRowsOfMainGrid();
        final Collator myCollator = Collator.getInstance(new Locale("hu", "HU"));

        // Do until we have rows
        for (int index = 0; index < (myGridPanelRows.size() - 1); index++) {

            // Compare by first attributes
            final String firstValueOfThisRow = myGridPanelRows.get(index)
                    .findElement(By.xpath("./tbody[1]/tr/td[" + attributePositions.get(0) + "]")).getText();
            final String firstValueOfNextRow = myGridPanelRows.get(index + 1)
                    .findElement(By.xpath("./tbody/tr[1]/td[" + attributePositions.get(0) + "]")).getText();

            // If first attributes equal, do compare with second attributes
            if ((firstValueOfThisRow.equals(firstValueOfNextRow)) && (getNumberOfSorters() > 1)) {
                compareBySecondAttribute(attributePositions, index);
            } else {
                assertTrue("Comparing by first attribute: " + firstValueOfThisRow + " <= " + firstValueOfNextRow,
                        myCollator.compare(firstValueOfThisRow, firstValueOfNextRow) <= 0);
            }
        }
    }

    /**
     * Comparing by second attribute
     *
     * @param attributePositions
     * @param index
     */
    public static void compareBySecondAttribute(final List<Integer> attributePositions, final int index) {
        // Get rows
        final List<WebElement> myGridPanelRows = GeneralTableManager.getAllRowsOfMainGrid();
        final Collator myCollator = Collator.getInstance(new Locale("hu", "HU"));

        // Get second attributes
        final String secondValueOfThisRow = myGridPanelRows.get(index)
                .findElement(By.xpath("./tbody[1]/tr/td[" + attributePositions.get(1) + "]")).getText();
        final String secondValueOfNextRow = myGridPanelRows.get(index + 1)
                .findElement(By.xpath("./tbody/tr/td[" + attributePositions.get(1) + "]")).getText();

        // If second attributes equal (and we have more sorters), do third attribute comparision
        if ((secondValueOfThisRow.equals(secondValueOfNextRow)) && (getNumberOfSorters() > 2)) {
            compareByThirdAttribute(attributePositions, index);
        } else {
            assertTrue("Comparing by second attribute: " + secondValueOfThisRow + " <= " + secondValueOfNextRow,
                    myCollator.compare(secondValueOfThisRow, secondValueOfNextRow) <= 0);
        }
    }

    /**
     * Comparing by third attribute
     *
     * @param colPositions
     * @param index
     */
    public static void compareByThirdAttribute(final List<Integer> colPositions, final int index) {
        // Get rows
        final List<WebElement> myGridPanelRows = GeneralTableManager.getAllRowsOfMainGrid();
        final Collator myCollator = Collator.getInstance(new Locale("hu", "HU"));

        // Get third attributes
        final String thirdValueOfThisRow = myGridPanelRows.get(index).findElement(By.xpath("./tbody/tr/td[" + colPositions.get(2) + "]"))
                .getText();
        final String thirdValueOfNextRow = myGridPanelRows.get(index + 1)
                .findElement(By.xpath("./tbody/tr/td[" + colPositions.get(2) + "]")).getText();
        assertTrue("Comparing by third attribute: " + thirdValueOfThisRow + " <= " + thirdValueOfNextRow,
                myCollator.compare(thirdValueOfThisRow, thirdValueOfNextRow) <= 0);
    }

    /**
     * Adds new sorter
     */
    public static void addNewSorter() {
        Utils.waitForAndClickOnGeneralWebElement(SortLocators.NEW_SORTING_BUTTON);
        Utils.defaultWait();
        Utils.defaultWait();
    }

    /**
     * Creates sorter for random record type
     *
     * @param recordType
     */
    public static List<GeneralTableAttributes> createRandomSorterForThisRecordType(final GeneralRecordTypes recordType) {
        // Close all sorters
        closeAllSorters();
        Utils.defaultWait();

        // Adds new sorter
        addNewSorter();
        Utils.defaultWait();
        // Select random locator from sorter
        final GeneralTableAttributes randomAttribute = GeneralRecordTypes.getRandomUnextendableAttribute(recordType);

        final List<GeneralTableAttributes> selectedAttributes = new ArrayList<>();
        selectedAttributes.add(randomAttribute);
        selectThisAttributeFromSorter(randomAttribute);
        Utils.defaultWait();
        // Check number of sortings
        checkNumberOfSorters(1);
        // TODO: checkMultipleSorting(driver, recordType, selectedAttributes);

        return selectedAttributes;
    }

    /**
     * Sorts table by mtID, descending
     *
     * @param recordType
     */
    public static void sortTableByIdDesc() {
        // Close all sorters
        closeAllSorters();

        // Adds new sorter by ID
        addNewSorter();
        selectThisAttributeFromSorter(AuthorRecordAttributes.ID);

        // TODO: Click on sorter arrow to make it descending
        Utils.createGeneralWebElementsFromEnum(SortLocators.ALL_SORTERS).get(0).findElement(By.xpath(SortLocators.SORTER_ARROW.toString()))
                .click();
        Utils.switchToActiveElement().sendKeys(Keys.DOWN);
        Utils.switchToActiveElement().sendKeys(Keys.DOWN);
        Utils.switchToActiveElement().sendKeys(Keys.ENTER);
        Utils.defaultWait();
    }

    /**
     * Sorts table by mtID, descending
     *
     * @param recordType
     */
    public static void sortTableByIDDesc() {
        // Close all sorters
        closeAllSorters();

        // Adds new sorter by ID
        addNewSorter();
        selectThisAttributeFromSorter(ReportTemplateRecordAttributes.ID);

        // TODO: Click on sorter arrow to make it descending
        Utils.createGeneralWebElementsFromEnum(SortLocators.ALL_SORTERS).get(0).findElement(By.xpath(SortLocators.SORTER_ARROW.toString()))
                .click();
        Utils.switchToActiveElement().sendKeys(Keys.DOWN);
        Utils.switchToActiveElement().sendKeys(Keys.DOWN);
        Utils.switchToActiveElement().sendKeys(Keys.ENTER);
        Utils.defaultWait();
    }

    /**
     * Selects locator from sorter
     */
    public static void selectThisAttributeFromSorter(final GeneralTableAttributes attribute) {
        LOGGER.info("Válasszuk ki az alábbi opciót a listából: " + Utils.getAllMyLabelsAsString(attribute));

        // Open sorter menu
        int sorterIndex = getNumberOfSorters();
        List<WebElement> sorters = Utils.createGeneralWebElementsFromEnum(SortLocators.ALL_SORTERS);
        final WebElement sorter = sorters.get(sorterIndex - 1);
        sorter.findElement(By.xpath(SortLocators.SORTER_SELECTOR.toString())).sendKeys(Keys.DOWN);
        Utils.defaultWait();

        // Get menu options

        Utils.defaultWait();

        boolean found = false;
        final String language = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
        String targetLabel = ("English".equals(language) ? attribute.getNames().get(1) : attribute.getNames().get(0));

        // Iterating the items of the list and we'll stop if we arrive back to the first element

        List<WebElement> menuOptions = Utils.createGeneralWebElementsFromXpathString(
                RecordSelectionLocators.RECORD_SELECTOR_MENU_OPTIONS.toString());

        for (WebElement elem : menuOptions) {
            if (elem.getAttribute("textContent").equalsIgnoreCase(targetLabel)) {
                Utils.scrollIntoView(elem);
                Utils.defaultWait();
                elem.click();
                found = true;
            }
        }
        Utils.myAssertTrue("Couldn't find the attribute in the sorting list", found);
    }
}
