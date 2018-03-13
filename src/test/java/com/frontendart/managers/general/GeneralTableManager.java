package com.frontendart.managers.general;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.frontendart.common.BrowserTypes;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.locators.general.GeneralLocatorTypes;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.records.attributes.general.AuthorRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;

/**
 * General manager class for adding, editing, removing records
 *
 * @author Zoli
 *
 */
public class GeneralTableManager {

    public enum TableOrder {
        ASC,
        DESC
    }

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("GeneralRecordManager");

    /**
     * Returns the rows in the grid
     */
    public static List<WebElement> getAllRowsOfMainGrid() {

        int originalLastIndex = 0;
        int newLastIndex = 0;

        List<WebElement> temp = new ArrayList<>();
        List<WebElement> rows = new ArrayList<>();

        originalLastIndex = Integer.parseInt(GeneralTableManager.getAllRowsOfThisGrid(MainPageLocators.CONTENT_GRID_PANEL)
                .get(getAllRowsOfThisGrid(MainPageLocators.CONTENT_GRID_PANEL).size() - 1).getAttribute("data-recordindex"));

        while (originalLastIndex != newLastIndex) {
            temp = getAllRowsOfThisGrid(MainPageLocators.CONTENT_GRID_PANEL);

//			for(int i = 0; i<temp.size(); i++){
//				if(!rows.contains(temp.get(i))){
//					rows.add(temp.get(i));
//				}
//			}

            newLastIndex = originalLastIndex;
            Utils.defaultWait();
            Utils.scrollIntoView(GeneralTableManager.getAllRowsOfThisGrid(MainPageLocators.CONTENT_GRID_PANEL)
                    .get(getAllRowsOfThisGrid(MainPageLocators.CONTENT_GRID_PANEL).size() - 1));
            originalLastIndex = Integer.parseInt(GeneralTableManager.getAllRowsOfThisGrid(MainPageLocators.CONTENT_GRID_PANEL)
                    .get(getAllRowsOfThisGrid(MainPageLocators.CONTENT_GRID_PANEL).size() - 1).getAttribute("data-recordindex"));

            for (int i = 0; i < temp.size(); i++) {
                if (!rows.contains(temp.get(i))) {
                    rows.add(temp.get(i));
                }
            }
        }

        Utils.defaultWait();

//		return getAllRowsOfThisGrid(MainPageLocators.CONTENT_GRID_PANEL);
        return rows;
    }

    /**
     * Returns the rows in the grid
     */
    public static List<WebElement> getAllRowsOfThisGrid(final GeneralLocatorTypes gridPanel) {
        return Utils.createGeneralWebElementFromEnum(gridPanel).findElements(By.xpath(".//table"));
    }

    /**
     * Returns the record values of the given grid
     */
    public static List<String> getTheseValuesFromThisGrid(final GeneralTableAttributes attribute, final GeneralLocatorTypes gridPanel) {
        final int attributePosition = getColPos(attribute, gridPanel);
        final List<String> values = new ArrayList<>();

        // Go through all rows and get all mtIDs
        for (final WebElement row : getAllRowsOfThisGrid(gridPanel)) {
            values.add(row.findElement(By.xpath(".//tbody/tr/td[" + attributePosition + "]/div")).getText());
        }

        return values;
    }

    /**
     * Returns the record id-s (Mtmt id-s) of table
     */
    public static List<Integer> getAllRecordIdsFromGrid() {
        final List<String> recordIDsAsString = getTheseValuesFromThisGrid(AuthorRecordAttributes.ID, MainPageLocators.CONTENT_GRID_PANEL);
        return Utils.convertThisStringArrayToIntArray(recordIDsAsString);
    }

    /**
     * Finds the given record by mtID in the grid table - returns the row.
     */
    public static WebElement findThisRecordByID(final int recordToFind) {
        final int index = getAllRecordIdsFromGrid().indexOf(recordToFind);
        return Utils.createGeneralWebElementsFromEnum(MainPageLocators.CONTENT_GRID_PANEL_ALL_ROWS).get(index);
    }

    /**
     * Returns the mtID of the given row
     */
    public static int getIDOfThisRow(final WebElement row) {
        final int mtIdColPos = getColPos(AuthorRecordAttributes.ID);
        return Integer.parseInt(row.findElement(By.xpath("./tbody/tr/td[" + mtIdColPos + "]/div")).getText());
    }

    /**
     * Returns the status of the given row
     */
    public static String getStatusOfThisRow(final WebElement row) {
        final int mtIdColPos = getColPos(AuthorRecordAttributes.STATUS);
        return row.findElement(By.xpath("./tbody/tr/td[" + mtIdColPos + "]/div")).getText();
    }

    /**
     * get column (attribute) position
     */
    public static int getColPos(final GeneralTableAttributes attribute) {
        return getColPos(attribute, MainPageLocators.CONTENT_GRID_PANEL);
    }

    /**
     * get column (attribute) position
     */
    public static int getColPos(final GeneralTableAttributes attribute, final GeneralLocatorTypes gridPanel) {
        final List<String> visibleHeaders = getAllVisibleHeadersText(gridPanel);
        //final List<String> visibleHeaders = getAllHeadersText();

        int columnPosition = visibleHeaders.indexOf(attribute.getNames().get(0));
        if (columnPosition == -1) {
            columnPosition = visibleHeaders.indexOf(attribute.getNames().get(1));
            assertNotEquals("This attribute is not visible in the gridpanel: " + Utils.getAllMyLabelsAsString(attribute), -1,
                    columnPosition);
        }

        return (columnPosition + 1);

    }

    /**
     * Returns all column positions located by the given locators
     *
     * @param selectedLocators
     * @return
     */
    public static List<Integer> getColumnDivPositionsForTheseLocators(final List<GeneralTableAttributes> selectedAttributes) {
        final List<Integer> colPositions = new ArrayList<>();
        for (final GeneralTableAttributes attribute : selectedAttributes) {
            colPositions.add(getColPos(attribute));
        }

        return colPositions;
    }

    /**
     * Returns all visible headers as text in gridpanel
     *
     * @return
     *
     */
    public static List<String> getAllVisibleHeadersText() {
        return getAllVisibleHeadersText(MainPageLocators.CONTENT_GRID_PANEL);
    }

    /**
     * Returns all visible headers as text in gridpanel
     *
     * @return
     *
     */
    public static List<String> getAllVisibleHeadersText(final GeneralLocatorTypes gridPanel) {
        final List<WebElement> headers = getAllVisibleHeadersAsWebElements(gridPanel);
        final List<String> headersText = new ArrayList<>();

        for (final WebElement header : headers) {
            final WebElement myElement = header.findElement(By.xpath("./div/div/div/div/span"));
            headersText.add(myElement.getAttribute("innerHTML"));
        }
        return headersText;
    }

    /**
     * Returns all (visible or not hidden) headers as text in gridpanel
     *
     * @return
     *
     */
    public static List<String> getAllHeadersText() {
        final List<WebElement> headers = Utils.createGeneralWebElementsFromEnum(MainPageLocators.CONTENT_GRID_PANEL_ALL_HEADERS);
        final List<String> headersText = new ArrayList<>();

        for (final WebElement header : headers) {
            if (!header.getAttribute("style").contains("display: none")) {
                final WebElement myElement = header.findElement(By.xpath("./div/div/div/div/span"));
                headersText.add(myElement.getAttribute("innerHTML"));
            }
        }
        return headersText;
    }

    /**
     * Returns all visible headers as webelements
     *
     * @return
     *
     */
    public static List<WebElement> getAllVisibleHeadersAsWebElements() {
        return getAllVisibleHeadersAsWebElements(MainPageLocators.CONTENT_GRID_PANEL);
    }

    /**
     * Returns all visible headers as webelements
     *
     * @return
     *
     */
    public static List<WebElement> getAllVisibleHeadersAsWebElements(final GeneralLocatorTypes grid) {
        final List<WebElement> gridHeaders = Utils.createGeneralWebElementFromEnum(grid)
                .findElements(By.xpath(".//div[starts-with(@class, 'x-column-header ')]"));
        final List<WebElement> visibleHeaders = new ArrayList<>();

        for (final WebElement header : gridHeaders) {
            if (!header.getAttribute("style").contains("display: none")) {
                visibleHeaders.add(header);
            }
        }
        return visibleHeaders;
    }

    /**
     * Checks a given page table headers
     *
     * @param pageToValidate
     * @return
     *
     */
    public static boolean checkGridTableHeadersAfterSelection(final Class<? extends GeneralTableAttributes> pageToValidate) {
        LOGGER.info("Ellenőrizzük, hogy az alábbi oszlopfejlécek megjelennek a táblázatban: ");

        final List<String> visibleHeaders = getAllHeadersText();

        System.out.println("Látható headerek");
        for (String s : visibleHeaders) {
            System.out.print(s + " ");
        }
        final List<GeneralTableAttributes> notDisabledAttributes = GeneralRecordTypes.getNotDisabledAttributes(pageToValidate);

        for (final GeneralTableAttributes attribute : notDisabledAttributes) {
            LOGGER.info(" - " + Utils.getAllMyLabelsAsString(attribute));
            assertTrue("This header should be visible: " + Utils.getAllMyLabelsAsString(attribute),
                    Utils.isThisStringListContainsThisAttribute(visibleHeaders, attribute));
        }

        return true;

    }

    /**
     * Changes the visibility of header
     *
     * @param randomHeader
     */
    public static void changeTheVisibilityOfThisHeader(final String randomHeader) {
        // Open header menu by click on the first visible header
        final WebElement secondVisibleHeader = getAllVisibleHeadersAsWebElements().get(1);
        secondVisibleHeader.sendKeys(Keys.DOWN);

        // Menu, click right on "Oszlopok/Columns" item.
        Utils.waitForElementVisible(MainPageLocators.GRID_PANEL_HEADERS_MENU_OPTIONS_COLUMN);

        if (TestConfiguration.getDesiredBrowser().equals(BrowserTypes.FIREFOX)) {
            Utils.clickToWebelementWithJavascriptExecutor(
                    Utils.createGeneralWebElementFromEnum(MainPageLocators.GRID_PANEL_HEADERS_MENU_OPTIONS_COLUMN));
            Utils.defaultWait();
        } else {
            Utils.createGeneralWebElementFromEnum(MainPageLocators.GRID_PANEL_HEADERS_MENU_OPTIONS_COLUMN).click();
            Utils.defaultWait();
        }

        Utils.waitForElementVisible(MainPageLocators.GRID_PANEL_HEADERS_COLUMNS_MENU);

        // Menu, find header and click
        final List<WebElement> menuItemsAsWebElement = Utils
                .createGeneralWebElementsFromEnum(MainPageLocators.GRID_PANEL_HEADERS_COLUMNS_MENU_OPTIONS);
        final List<String> menuItemsAsString = Utils.convertThisWebElementArrayToStringArray(menuItemsAsWebElement);
        menuItemsAsWebElement.get(menuItemsAsString.indexOf(randomHeader)).click();

    }

    public static void changeOrderOnThisHeader(final GeneralTableAttributes header, TableOrder order) {
        final List<WebElement> headers = getAllVisibleHeadersAsWebElements();
        final List<String> headerTexts = getAllHeadersText();
        WebElement headerToClick = headers.get(headerTexts.indexOf(header.getNames().get(0)));
        Utils.scrollIntoView(headerToClick);
        headerToClick.click();
        Utils.waitForRightPanelLoadingFinished();
        if (order == TableOrder.DESC) {
            Utils.scrollIntoView(headerToClick);
            headerToClick.click();
            Utils.waitForRightPanelLoadingFinished();
        }
    }
}
