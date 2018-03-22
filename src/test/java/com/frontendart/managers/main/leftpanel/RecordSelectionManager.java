package com.frontendart.managers.main.leftpanel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assume.assumeTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.frontendart.common.Roles;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.leftpanel.RecordSelectionLocators;
import com.frontendart.locators.main.rightpanel.view.ToolbarLocators;
import com.frontendart.locators.records.attributes.general.GeneralRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.general.GeneralTableManager.TableOrder;
import com.frontendart.managers.main.rightpanel.view.SortManager;

/**
 * Manager class for record selection.
 *
 * TODO: refakt
 *
 * @author Zoli
 *
 */
public class RecordSelectionManager {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("RecordSelectionManager");

    /**
     * Open record type selector
     *
     *
     */
    public static void openRecordTypeSelector() {
        Utils.waitForElementVisible(RecordSelectionLocators.RECORD_SELECTOR);
        Utils.createGeneralWebElementFromEnum(RecordSelectionLocators.RECORD_SELECTOR).sendKeys(Keys.ARROW_DOWN);
        Utils.defaultWait();
    }

    /**
     * Finds and clicks record type in selector @throws
     */
    public static boolean findThisTypeInSelectorOptionsAndClick(final GeneralRecordTypes recordType) {
        Utils.waitForElementVisible(RecordSelectionLocators.RECORD_SELECTOR_MENU_OPTIONS);
        // Language check (If English is the active, we should check for the English name of the attribute)

        final String text = ("English".equals(Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText()))
                ? recordType.getNames().get(1)
                : recordType.getNames().get(0);

        boolean returnValue = false;

        try {
            List<WebElement> menuOptions = Utils.createGeneralWebElementsFromXpathString(
                    RecordSelectionLocators.RECORD_SELECTOR_MENU_OPTIONS.toString());

            for (WebElement elem : menuOptions) {
                if (elem.getAttribute("textContent").equalsIgnoreCase(text)) {
                    Utils.scrollIntoView(elem);
                    Utils.defaultWait();
                    elem.click();
                    returnValue = true;
                }
            }

        } catch (final NoSuchElementException e) {
            System.out.println("Nem található az adott rekord a listában!");
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Does cycle
     *
     * @param recordType
     */
    public static boolean searchForThisRecordType(final GeneralRecordTypes recordType) {
        boolean returnValue = false;

        // Get current menuitem, and menuitem text
        final WebElement activeElement = Utils.switchToActiveElement();
        final String activeElementText = activeElement.findElement(By.xpath("./span")).getText();

        // If we find, we click on it, else go down
        if ((activeElementText.equals(recordType.getNames().get(0))) || activeElementText.equals(recordType.getNames().get(1))) {
            activeElement.click();
            returnValue = true;
        } else {
            activeElement.sendKeys(Keys.ARROW_DOWN);
        }

        return returnValue;
    }

    /**
     * Selects a record from the record selector and run a default query to have results
     */
    public static boolean selectThisRecordTypeFromSelector(final GeneralRecordTypes recordType) {
        LOGGER.info("Válasszuk ki az alábbi rekord típust a rekord választóból: " + recordType.getNames().get(0) + "/"
                + recordType.getNames().get(1));

        // Opens record selector and finds the given record type
        openRecordTypeSelector();
        final boolean found = findThisTypeInSelectorOptionsAndClick(recordType);
        //waitUntilTableIsReady();
        Utils.defaultWait();
        Utils.defaultWait();

        // If gridtable is empty, we run an empty query, to get results
        //if (gridTableFullSize() == 0) {
        //SearchManager.createAndRunSubsetQuery(recordType);
        // This is needed because of the filtering...
        //FilterManager.performFilterWithText("");

//			org.junit.Assume.assumeFalse("There are no " + getAllMyLabelsAsString(recordType) + " type records present!", 0==gridTableFullSize());
//		assertNotEquals("There are no " + getAllMyLabelsAsString(recordType) + " type records present!", 0, gridTableFullSize());
        //}

        return found;
    }

    /**
     * Selects a record from the record selector
     */
    public static boolean selectThisRecordTypeFromSelectorWithoutRunEmptyQuery(final GeneralRecordTypes recordType) {
        LOGGER.info("Válasszuk ki az alábbi rekord típust a rekord választóból: " + recordType.getNames().get(0) + "/"
                + recordType.getNames().get(1));

        // Opens record selector and finds the given record type
        openRecordTypeSelector();
        final boolean found = findThisTypeInSelectorOptionsAndClick(recordType);
        //waitUntilTableIsReady();
        Utils.defaultWait();
        Utils.defaultWait();

        return found;
    }

    /**
     * Waits until table headers, and table rows are visible
     *
     */
    public static void waitUntilTableIsReady() {
        // Wait for change view buttons
        Utils.waitMillisec(1000);
        Utils.waitForElementPresent(ToolbarLocators.CHANGE_VIEW_ALL);

        // Get active view, and wait for content
        if (Utils.createGeneralWebElementFromEnum(ToolbarLocators.CHANGE_VIEW_GRID).getAttribute("class").contains("pressed")) {
            Utils.waitForElementPresent(MainPageLocators.CONTENT_GRID_PANEL);
        } else if (Utils.createGeneralWebElementFromEnum(ToolbarLocators.CHANGE_VIEW_LISTGRID).getAttribute("class").contains("pressed")) {
            Utils.waitForElementPresent(MainPageLocators.CONTENT_LIST_GRID_PANEL);
        }

        waitUntilInfoBarChanges();
    }

    /**
     * Waits until info bar changes
     *
     */
    public static void waitUntilInfoBarChanges() {
        String myInfoText = Utils.createGeneralWebElementFromEnum(RecordSelectionLocators.INFO_BAR_NUMBERS).getText();

        int tryCount = 0;
        while (" ".equals(myInfoText)) {
            Utils.waitMillisec(500);
            myInfoText = Utils.createGeneralWebElementFromEnum(RecordSelectionLocators.INFO_BAR_NUMBERS).getText();
            tryCount++;
            assertFalse("The info bar did not loaded in time!", tryCount > (TestConfiguration.maxWaitTimeInSec * 2));
        }
    }

    /**
     * Selects a record from the record selector
     */
    public static GeneralRecordTypes selectRandomRecordTypeFromSelector() {
        //random for author role
        if (Utils.getActualRole().equals(Roles.AUTHOR)) {
            GeneralRecordTypes selectedRecordType = GeneralRecordTypes.getVisibleRecordTypeForActualRole();
            selectThisRecordTypeFromSelector(selectedRecordType);
            return selectedRecordType;
        }

        //directed random for the other roles
        GeneralRecordTypes selectedRecordType = null;

        final GeneralRecordTypes[] records = new GeneralRecordTypes[] { GeneralRecordTypes.APPEARANCE,
                GeneralRecordTypes.AUTHOR_TYPE,
                GeneralRecordTypes.CATEGORY,
                GeneralRecordTypes.DEGREE,
                GeneralRecordTypes.FORUM,
                GeneralRecordTypes.FUNDER,
                GeneralRecordTypes.INSTITUTE_TYPE,
                GeneralRecordTypes.LANGUAGE,
                GeneralRecordTypes.RATING_TYPE,
                GeneralRecordTypes.SUB_TYPE,
                GeneralRecordTypes.VARIABLE };
        final int randNumber = Utils.randInt(0, records.length - 1);
        selectedRecordType = records[randNumber];

        RecordSelectionManager.selectThisRecordTypeFromSelector(selectedRecordType);
        return selectedRecordType;
    }

    /**
     * Returns the rendered elements of the grid panel
     */
    public static List<WebElement> getGridElementsOnPage() {
        return Utils.createGeneralWebElementsFromEnum(MainPageLocators.GRID_ELEMENTS_ON_PAGE);
    }

    /**
     * Checks the rendered rows size of a table
     */
    public static int gridTableRenderedRowsSize() {
        int tableSize = getGridElementsOnPage().size();
        if (tableSize > 30) {
            tableSize = 30;
        }
        return tableSize;
    }

    /**
     * Checks the full size of a table
     */
    public static int gridTableFullSize() {
        final String myInfoText = Utils.createGeneralWebElementFromEnum(RecordSelectionLocators.INFO_BAR_NUMBERS).getText();
        final List<String> myList = Arrays.asList(myInfoText.split(" "));
        final String[] myNumbersList = myList.get(0).split("/");

        return Integer.parseInt(myNumbersList[1]);
    }

    /**
     * Clicks on random rows of a table
     *
     */
    public static List<WebElement> selectRandomRenderedRowsFromGridPanel() {
        int range;
        if (gridTableRenderedRowsSize() < 5) {
            range = Utils.randInt(1, gridTableRenderedRowsSize());
        } else {
            range = Utils.randInt(1, 5);
        }

        return selectRandomRenderedRowsFromGridPanel(range);
    }

    /**
     * Clicks on random row of a table
     */
    public static List<WebElement> selectRandomRenderedRowsFromGridPanel(final int count) {
        final List<WebElement> selectedRowWebElements = new ArrayList<>();
        final int tableSize = gridTableRenderedRowsSize();

        // Select rows
        final List<Integer> selectedRows = new ArrayList<>();
        for (int index = 0; index < count; index++) {

            // Select a row which is not selected yet
            int myRandomRow = Utils.randInt(1, tableSize);
            while (selectedRows.contains(myRandomRow)) {
                myRandomRow = Utils.randInt(1, tableSize);
            }
            selectedRows.add(myRandomRow);

            // Put it in the return list
            final List<WebElement> myRowsAsWebElement = getGridElementsOnPage();
            final WebElement selected = myRowsAsWebElement.get(myRandomRow - 1);
            selectedRowWebElements.add(selected);
            Utils.scrollIntoView(selected);

            controlClickOnThisRow(selected);
        }

        LOGGER.info("Válasszunk ki " + count + "db sort a táblázatból. (Jelen tesztben kiválasztott sor, sorok sorszáma: " + selectedRows
                + ")");
        return selectedRowWebElements;
    }

    /**
     * Click on rows
     *
     *
     * @param myGeneralRecordType
     * @return
     *
     */
    public static List<WebElement> clickRandomRenderedRowsFromGridPanel(final List<WebElement> selectedRows) {
        // This is a precaution
        final int tableSize = gridTableRenderedRowsSize();

        // Determine how many rows to click on
        int howManyRowsToClickOn = 0;
        if (selectedRows.isEmpty()) {
            howManyRowsToClickOn = Utils.randInt(1, tableSize);
        } else {
            howManyRowsToClickOn = Utils.randInt(1, selectedRows.size());
        }

        // Logging
        LOGGER.info("Kattintsunk tetszőleges sorokra a táblázatban úgy, hogy a CTRL (Control) gombot lenyomva tartjuk. ");
        LOGGER.info("Elvárt eredmény: Ha az adott (kattintott) sor már ki volt jelölve, akkor a kattintás következtében "
                + "eltűnik a kijelölés, valamint a kijelölt sorok száma a kattintást követően 1-el csökken."
                + " Ellenkező esetben a kattintást követően az adott sor ki lesz jelölve és az "
                + "összes kijelölt sor száma a kattintás után 1-el növekszik.");

        // Click...
        for (int index = 0; index < howManyRowsToClickOn; index++) {
            final int myRandomRow = Utils.randInt(1, tableSize);
            final List<WebElement> myRowsAsWebElement = getGridElementsOnPage();
            final WebElement selectedRowAsWebElement = myRowsAsWebElement.get(myRandomRow - 1);
            controlClickOnThisRow(selectedRowAsWebElement);

            // If the selected row was already in the selected rows list, we remove, otherwise we put it
            if (selectedRows.contains(selectedRowAsWebElement)) {
                selectedRows.remove(selectedRowAsWebElement);
            } else {
                selectedRows.add(selectedRowAsWebElement);
            }
        }

        return selectedRows;

    }

    /**
     * Select with shift button
     *
     * @param myGeneralRecordType
     * @return
     *
     */
    public static List<WebElement> selectFromGridPanelWithShift() {
        int tableSize = gridTableRenderedRowsSize();
        final List<WebElement> selectedRowWebElements = new ArrayList<>();

        // Check if there are enough records present
        if (gridTableFullSize() < 2) {
            SearchManager.createAndRunEmptyQuery();
            tableSize = gridTableRenderedRowsSize();

            assumeTrue("There are not enough records to perform this test case! At least 2 records should be present!",
                    gridTableFullSize() > 1);
//			assertTrue("There are not enough records to perform this test case! At least 2 records should be present!",
//					gridTableFullSize() > 1);
        }

        // Get rows
        final List<WebElement> myRowsAsWebElement = getGridElementsOnPage();

        // Get start row index and end row index
        final int startRowIndex = Utils.randInt(1, tableSize - 1);
        final int endRowIndex = Utils.randInt(startRowIndex + 1, tableSize);
        final WebElement startRowAsWebElement = myRowsAsWebElement.get(startRowIndex - 1);
        final WebElement endRowAsWebElement = myRowsAsWebElement.get(endRowIndex - 1);

        // Logger
        LOGGER.info("Válasszunk egy tetszőleges sort a táblázatból, kattintsunk rá, majd a SHIFT gombot nyomva tartva válasszunk "
                + "egy másik sort, és arra is kattintsunk rá.");
        LOGGER.info("Elvárt eredmény: A két kiválasztott sor, valamint a köztük lévő sorok kijelölésre kerülnek.");

        // Shift click
        shiftClickOnThisRow(startRowAsWebElement, endRowAsWebElement);

        // add to selected rows
        for (int index = startRowIndex; index <= endRowIndex; index++) {
            selectedRowWebElements.add(myRowsAsWebElement.get(index - 1));
        }

        return selectedRowWebElements;
    }

    /**
     * Select with shift button
     *
     * @param myGeneralRecordType
     * @return
     */
    public static List<WebElement> selectLastCreatedRecordsOnGridPanelWithShift(int numberOfSelections) {
        final List<WebElement> selectedRowWebElements = new ArrayList<>();

        // Check if there are enough records present
        assumeTrue(String.format(
                "There are not enough records to perform this test case! At least %s records should be present!",
                numberOfSelections), gridTableRenderedRowsSize() >= numberOfSelections);

        // Order rows by last modification to have the last ones on top
        SortManager.closeAllSorters();
        GeneralTableManager.changeOrderOnThisHeader(GeneralRecordAttributes.LAST_MODIFICATION, TableOrder.DESC);

        // Get rows
        final List<WebElement> myRowsAsWebElement = getGridElementsOnPage();

        // Logger
        LOGGER.info(String.format("Válasszuk ki a legnagyobb id-jű %d db sort a táblázatból a SHIFT gombot lenyomásával.",
                numberOfSelections));

        // Shift click
        shiftClickOnThisRow(myRowsAsWebElement.get(0), myRowsAsWebElement.get(numberOfSelections - 1));

        // add to selected rows
        for (int i = 0; i < numberOfSelections; i++) {
            selectedRowWebElements.add(myRowsAsWebElement.get(i));
        }

        return selectedRowWebElements;
    }

    /**
     * Clicks on the given row in the grid table
     *
     * @param rowToClick
     */
    public static void controlClickOnThisRow(final WebElement rowToClick) {
        final Actions builder = Utils.createActionForDriver();
        builder.keyDown(Keys.CONTROL)
                .click(rowToClick.findElement(By.xpath(RecordSelectionLocators.RECORD_SELECTION_SELECT_ROW.toString())))
                .keyDown(Keys.CONTROL);
//		builder.keyDown(Keys.CONTROL).click(rowToClick).keyDown(Keys.CONTROL);
        final Action selectMultiple = builder.build();
        selectMultiple.perform();
        Utils.defaultWait();
    }

    /**
     * Clicks on the given row in the grid table
     *
     * @param rowToClick
     */
    public static void shiftClickOnThisRow(final WebElement startRow, final WebElement endRow) {
        Utils.waitMillisec(2000);
        final Actions builder = Utils.createActionForDriver();
        builder.click(startRow.findElement(By.xpath(RecordSelectionLocators.RECORD_SELECTION_SELECT_ROW.toString()))).keyDown(Keys.SHIFT)
                .click(endRow.findElement(By.xpath(RecordSelectionLocators.RECORD_SELECTION_SELECT_ROW.toString()))).keyDown(Keys.SHIFT);
        final Action selectMultiple = builder.build();
        selectMultiple.perform();
        Utils.waitMillisec(2000);
    }

    /**
     * Returns the selected items in the view
     *
     * @return
     */
    public static List<WebElement> getSelectedItemsInView() {
        final List<WebElement> selectedElements = new ArrayList<>();

        // Go through elements, and check class attribute, if it contains 'selected'
        for (final WebElement item : getGridElementsOnPage()) {
            if (item.getAttribute("class").contains("selected")) {
                selectedElements.add(item);
            }
        }

        return selectedElements;
    }

    // Methods only for this class

    /**
     * Get labels of general record type
     *
     * @param recordType
     * @return
     */
    public static String getAllMyLabelsAsString(final GeneralRecordTypes recordType) {
        String returnString = recordType.getNames().get(0);
        for (int index = 1; index < recordType.getNames().size(); index++) {
            returnString = returnString.concat("/").concat(recordType.getNames().get(index));
        }

        return returnString;
    }
}
