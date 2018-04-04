package com.frontendart.managers.main.leftpanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.frontendart.common.Constants;
import com.frontendart.common.TestConfiguration;
import com.frontendart.common.Utils;
import com.frontendart.locators.main.leftpanel.MainQueryLocators;
import com.frontendart.locators.main.leftpanel.QueryEditorLocators;
import com.frontendart.locators.records.attributes.general.AuthorTypeRecordAttributes;
import com.frontendart.locators.records.attributes.general.CitationRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.InstituteRecordAttributes;
import com.frontendart.locators.records.attributes.general.KeywordRecordAttributes;
import com.frontendart.locators.records.attributes.general.PublicationRecordAttributes;
import com.frontendart.managers.general.GeneralTableManager;
import com.frontendart.managers.general.GeneralTableManager.TableOrder;
import com.frontendart.managers.main.leftpanel.SearchEditorManager.SearchCondition;
import com.frontendart.managers.main.leftpanel.SearchEditorManager.SearchMatchType;
import com.frontendart.managers.main.rightpanel.crud.DeleteRecordManager;
import com.frontendart.managers.main.rightpanel.view.ChangeViewManager;
import com.frontendart.managers.main.rightpanel.view.SortManager;

/**
 * Manager class for main query tests.
 *
 * TODO: refakt
 *
 * @author Zoli
 *
 */
public class SearchManager {

    /**
     * Logger
     */
    private static final Logger LOGGER                 = LogManager.getLogger("MainSearchManager");
    private static final int    SUBSET_QUERY_AUTHORS   = 5;
    private static final String INSTITUTE_SUBSET_QUERY = "Szeged";
    private static final String CITATION_SUBSET_QUERY  = "ieee";

    /**
     * Returns the new search button as a webelement on the smart queries grid
     *
     * @return
     *
     */
    public static void clickOnNewQueryButton() {
        Utils.waitForAndClickOnGeneralWebElement(MainQueryLocators.NEW_QUERY_BUTTON);
    }

    /**
     * Returns the queries as a list of webElements.
     *
     * @return
     *
     */
    public static List<WebElement> getMyQueriesAsWebElement() {
        return Utils.createGeneralWebElementsFromEnum(MainQueryLocators.ALL_QUERIES);
    }

    /**
     * Returns the number of queries
     *
     * @return
     *
     */
    public static int getNumberOfQueries() {
        return getMyQueriesAsWebElement().size();
    }

    /**
     * Returns the result number
     *
     * @return
     *
     */
    public static String getNumberOfResultsOfThisQuery(final String name) {
        final WebElement queryToFind = findThisQueryByName(Arrays.asList(name));
        return queryToFind.findElement(By.xpath(MainQueryLocators.QUERY_NUMBER_OF_RESULTS.toString())).getText();
    }

    /**
     * Starts new query with name
     *
     *
     * @param name
     */
    public static void startNewQuery(final String name) {
        // Clicks on new query button
        clickOnNewQueryButton();
        Utils.defaultWait();
        Utils.waitForAndClickOnGeneralWebElement(MainQueryLocators.NEW_QUERY_BUTTON_ON_TEMPLATE_PAGE);

        // change name
        Utils.writeTextToThisField(name, QueryEditorLocators.NAME_INPUT_FIELD);
    }

    /**
     * Deletes all existing queries, if there are lot of them...
     */
    public static void checkAndDeleteAllMyQueriesIfTooMuch() {
        // Click on my queries checkbox
        int originalQueryNumber = getNumberOfQueries();
        Utils.waitMillisec(2000);
        Utils.waitForAndClickOnGeneralWebElement(MainQueryLocators.MY_QUERIES_CHECKBOXFIELD);
        waitUntilQueryNumberChanges(originalQueryNumber);

        // Delete queries if there are more than 10 of them..
        List<String> queryNames = getMyQueriesNameAsString();
        if (queryNames.size() > 30) {
            int deletedQueriesNumber = 0;
            while ((!queryNames.isEmpty()) && (deletedQueriesNumber < 50)) {
                SearchManager.deleteThisQuery(queryNames.get(0));
                queryNames = getMyQueriesNameAsString();
                deletedQueriesNumber++;
            }
        }

        // Click on my queries checkbox
        originalQueryNumber = getNumberOfQueries();
        Utils.waitMillisec(2000);
        Utils.waitForAndClickOnGeneralWebElement(MainQueryLocators.MY_QUERIES_CHECKBOXFIELD);
        waitUntilQueryNumberChanges(originalQueryNumber);

    }

    /**
     * creates new query with name
     *
     */
    public static WebElement createNewQueryWithName(final String name) {
        final List<String> queriesName = getMyQueriesNameAsString();
        final List<String> queryNames = new ArrayList<>();

        // checks queries - if too many queries are present, it removes all of them
//		checkAndDeleteAllMyQueriesIfTooMuch();

        // If there is no query with this name
        if (!queriesName.contains(name)) {

            final int originalQueryNumber = getNumberOfQueries();
            startNewQuery(name);

            // Click on save
            clickSaveButton();
            waitUntilQueryNumberChanges(originalQueryNumber);
        }

        queryNames.add(name);
        return findThisQueryByName(queryNames);
    }

    /**
     * Waits until one more query is present.
     *
     * @param originalQueryNumber
     */
    public static void waitUntilQueryNumberChanges(final int originalQueryNumber) {
        int newNumberOfQueries = getNumberOfQueries();

        int tryCount = 0;
        while (originalQueryNumber == newNumberOfQueries) {
            Utils.waitMillisec(1000);
            newNumberOfQueries = getNumberOfQueries();
            tryCount++;
            assertFalse("Query number is not correct!", tryCount > TestConfiguration.maxWaitTimeInSec);
        }
    }

    public static void renameQueryTo(String oldName, String newName) {
        openQueryEditorByName(oldName);
        Utils.defaultWait();
        Utils.writeTextToThisField(newName, QueryEditorLocators.NAME_INPUT_FIELD);
        Utils.defaultWait();
        clickSaveButton();
        Utils.defaultWait();
    }

    /**
     * Waits until results are ready.
     *
     * @param name
     */
    public static void waitUntilResultsAreReady(final String name) {
        String resultsString = getNumberOfResultsOfThisQuery(name);

        int tryCount = 0;
        while (resultsString.contains("-")) {
            Utils.defaultWait();
            resultsString = getNumberOfResultsOfThisQuery(name);
            tryCount++;
            assertFalse("Results are not ready in time.", tryCount > 5);
        }

    }

    public static void selectRandomVisibilityOnAdvancedQuerySettingsPanel() {
        Utils.createGeneralWebElementFromEnum(QueryEditorLocators.MIN_VISIBILITY_LIST_BUTTON).click();
        Utils.defaultWait();
        List<WebElement> list = Utils.createGeneralWebElementsFromEnum(QueryEditorLocators.MIN_VISIBILITY_LIST);
        list.get(Utils.randInt(0, list.size() - 1)).click();

    }

    public static void selectRandomInstituteOnAdvancedQuerySettingsPanel() {
        Utils.createGeneralWebElementFromString(QueryEditorLocators.SEARCH_INSTITUTE_BUTTON.toString()).click();

        Utils.defaultWait();

        List<WebElement> list = Utils.createGeneralWebElementsFromEnum(QueryEditorLocators.INSTITUTE_LIST);
        System.out.println(list.size());

        //click on a random element
        WebElement selectedOne = list.get(Utils.randInt(0, list.size() - 1));
        Utils.scrollIntoView(selectedOne);
        selectedOne.click();
        Utils.defaultWait();
        Utils.createGeneralWebElementFromEnum(QueryEditorLocators.SELECT_AND_CLOSE_BUTTON).click();
    }

    /**
     * creates new query
     */
    public static String createNewQuery() {
        // Click on new query button, and get query name
        clickOnNewQueryButton();
        Utils.defaultWait();
        Utils.waitForAndClickOnGeneralWebElement(MainQueryLocators.NEW_QUERY_BUTTON_ON_TEMPLATE_PAGE);
        Utils.waitForElementPresent(QueryEditorLocators.NAME_INPUT_FIELD);
        final String name = Utils.createGeneralWebElementFromEnum(QueryEditorLocators.NAME_INPUT_FIELD).getAttribute("value");

        Utils.createGeneralWebElementFromEnum(QueryEditorLocators.NAME_INPUT_FIELD).clear();
        Utils.createGeneralWebElementFromEnum(QueryEditorLocators.NAME_INPUT_FIELD).sendKeys(Constants.PREFIX + name);

        // Click save, and wait for query to be present
        clickSaveButton();

        // returns the query name
        return Constants.PREFIX + name;
    }

    /**
     * Formats date
     */
    public static String formatThisDate(final Date date, final Locale locale) {
        final SimpleDateFormat formater = new SimpleDateFormat("YY-MM-dd HH:mm", locale);
        final String lowerCase = formater.format(date);
        return lowerCase.substring(0, 6) + lowerCase.substring(6, 7).toUpperCase(locale) + lowerCase.substring(7);
    }

    /**
     * Returns the queries name as a list of String
     *
     * @return
     *
     */
    public static List<String> getMyQueriesNameAsString() {
        final List<WebElement> queryNamesAsWebelement = Utils.createGeneralWebElementsFromEnum(MainQueryLocators.ALL_QUERIES_NAME);
        return Utils.convertThisWebElementArrayToStringArray(queryNamesAsWebelement);
    }

    /**
     * Returns the queries name as a list of String
     *
     * @return
     */
    public static WebElement findThisQueryByName(final List<String> name) {
        Utils.waitForElementPresent(MainQueryLocators.ALL_QUERIES);
        final List<String> queriesName = getMyQueriesNameAsString();
        final List<WebElement> myQueriesAsWebElement = getMyQueriesAsWebElement();

        // If new query not found then fail
        final int index = queriesName.indexOf(name.get(0));
        assertNotEquals("Query not found with name: " + name.get(0), -1, index);

        return myQueriesAsWebElement.get(index);
    }

    /**
     * Opens the query editor for a given query (by name)
     *
     * @return
     *
     */
    public static void openQueryEditorByName(final String name) {
        LOGGER.info("Kattintsunk a " + name + " nevű keresés utolsó oszlopában lévő nagyító ikonra.");
        final WebElement queryToOpen = findThisQueryByName(Arrays.asList(name));

        Utils.scrollIntoView(queryToOpen.findElement(By.xpath(MainQueryLocators.QUERY_OPEN_EDITOR.toString())));

        queryToOpen.findElement(By.xpath(MainQueryLocators.QUERY_OPEN_EDITOR.toString())).click();
//		Utils.clickToWebelementWithAction(queryToOpen.findElement(By.xpath(MainQueryLocators.QUERY_OPEN_EDITOR.toString())));

        Utils.waitForElementPresent(QueryEditorLocators.SAVE_BUTTON);
    }

    /**
     * Clicks on 'Save And Run' button in the editor
     *
     */
    public static void clickOnSaveAndRunButton() {
        Utils.waitForAndClickOnGeneralWebElement(QueryEditorLocators.SAVE_AND_RUN_BUTTON);
        Utils.waitMillisec(2000);
    }

    /**
     * Clicks on Save button in the editor
     *
     */
    public static void clickSaveButton() {
        Utils.waitForAndClickOnGeneralWebElement(QueryEditorLocators.SAVE_BUTTON);
        Utils.waitMillisec(2000);
    }

    /**
     * runs query
     *
     * @param name
     */
    public static void runThisQuery(final String name) {
//		final WebElement queryToFind = findThisQueryByName(Arrays.asList(name));
//		final WebElement resultFieldOfQuery = queryToFind.findElement(By.xpath(MainQueryLocators.QUERY_NUMBER_OF_RESULTS.toString()));
        performThisOperationOnThisQuery(name, MainQueryLocators.RUN);
//		Utils.waitForStalenessOfThisElement(resultFieldOfQuery);
        Utils.waitForRightPanelLoadingFinished();
        Utils.dismissRunAlertIfExist();
        ChangeViewManager.switchToGridView();
    }

    /**
     * Save this query to list.
     *
     * @param name
     */
    public static String saveThisQueryToList(final String name) {
        performThisOperationOnThisQuery(name, MainQueryLocators.SAVE_AS_LIST);
        final String listName = Constants.PREFIX + Utils.randomString();
        SavedListsManager.addThisNameToListName(listName);
        SavedListsManager.clickOnSaveListButton();

        return listName;
    }

    /**
     * Saves query as default
     *
     * @param name
     */
    public static void saveThisQueryAsDefault(final String name) {
        final WebElement queryToFind = findThisQueryByName(Arrays.asList(name));
        final WebElement resultFieldOfQuery = queryToFind.findElement(By.xpath(MainQueryLocators.QUERY_NUMBER_OF_RESULTS.toString()));
        performThisOperationOnThisQuery(name, MainQueryLocators.SAVE_AS_DEFAULT);
        Utils.waitForStalenessOfThisElement(resultFieldOfQuery);
        RecordSelectionManager.waitUntilTableIsReady();
    }

    /**
     * deletes query
     *
     * @param name
     *
     */
    public static void deleteThisQuery(final String name) {

//		checkAndDeleteAllMyQueriesIfTooMuch();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final int originalQueryNumber = getNumberOfQueries();
        performThisOperationOnThisQuery(name, MainQueryLocators.DELETE);
        Utils.waitForMessageBoxPresentAndAccept();

        if (!"Társszerzőim".equals(name)) {
            waitUntilQueryNumberChanges(originalQueryNumber);
        }

    }

    /**
     * deletes query
     *
     * @param name
     *
     */
    public static void duplicateThisQuery(final String name) {
        final int originalQueryNumber = getNumberOfQueries();
        performThisOperationOnThisQuery(name, MainQueryLocators.DUPLICATE);
        waitUntilQueryNumberChanges(originalQueryNumber);
    }

    /**
     * Operation on query
     *
     * @param name
     *
     */
    public static boolean performThisOperationOnThisQuery(final String name, final MainQueryLocators operation) {
        final WebElement query = findThisQueryByName(Arrays.asList(name));
        LOGGER.info("Kattintsunk a " + name + " nevű keresés első oszlopában lévő legördülő ikonra és válasszuk "
                + "a következő opciót.: " + Utils.getAllMyLabelsAsString(operation));

        final WebElement queryToggle = query.findElement(By.xpath(MainQueryLocators.QUERY_TOGGLE.toString()));

        Utils.scrollIntoView(queryToggle);
        Utils.sendKeysToWebelementWithAction(queryToggle, Keys.DOWN);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final WebElement activeElement = Utils.switchToActiveElement();

        System.out.println("operation: " + operation);

        Utils.clickToWebelementWithAction(activeElement.findElement(By.xpath(operation.toString())));
        /*

        if (operation.toString().contains("Töröl")) {
            final WebElement deleteQuery = Utils.createGeneralWebElementFromEnum(MainQueryLocators.DELETE_QUERY);
            deleteQuery.click();
        }

        else if (operation.toString().contains("Keresés")) {
            final WebElement runQuery = Utils.createGeneralWebElementFromEnum(MainQueryLocators.RUN_QUERY);
            runQuery.click();
        }

        else if (operation.toString().contains("Másolás")) {
            final WebElement runQuery = Utils.createGeneralWebElementFromEnum(MainQueryLocators.COPY_QUERY);
            runQuery.click();
        }

        else if (operation.toString().contains("Eredmények mentése listaként")) {
            final WebElement runQuery = Utils.createGeneralWebElementFromEnum(MainQueryLocators.SAVE_RESULTS_AS_QUERY);
            runQuery.click();
        }

        }

        else {
        Utils.clickToWebelementWithAction(activeElement.findElement(By.xpath(operation.toString())));
        }
        */
        return true;
    }

    /**
     * Checks query result (TODO: works only for one condition)
     */
    public static void checkQueryResult(final Map<GeneralTableAttributes, String> attributeValues, final SearchCheckTypes checkType) {

        // Get rows from grid panel
        final List<WebElement> myRows = RecordSelectionManager.getGridElementsOnPage();

        // Go through rows
        for (final WebElement row : myRows) {
            final GeneralTableAttributes firstAttribute = attributeValues.keySet().iterator().next();

            boolean isStatus = false;
            if (firstAttribute.getNames().get(0).equals("Státusz")) {
                isStatus = true;
            }

            final int attributePosition = GeneralTableManager.getColPos(firstAttribute);
            final WebElement myCell = row.findElement(By.xpath("./tbody/tr/td[" + (attributePosition - 1) + "]"));
            final String valueInCell = myCell.getText();
            final String firstValue = attributeValues.values().iterator().next();
            doCheck(firstValue, valueInCell, checkType, isStatus);
        }

    }

    /**
     * Does the check
     *
     * @param string
     * @param valueInCell
     * @param checkType
     */
    public static void doCheck(final String valueOfLocator, final String valueInCell, final SearchCheckTypes checkType, boolean isStatus) {
        switch (checkType) {
            case IS_EVERY_IS_MET:
            case IS_ANY_IS_MET:
                if (isStatus == true) {
                    assertEquals("The two values should equal!", valueOfLocator.substring(0, 1), valueInCell.substring(0, 1));
                } else {
                    assertEquals("The two values should equal!", valueOfLocator, valueInCell);
                }
                break;
            default:
                assertNotEquals("The two values should not equal!", valueOfLocator, valueInCell);
                break;
        }
    }

    /**
     * Gets the last run text of query
     *
     * @param name
     * @return
     *
     */
    public static String getQueryLastRunText(final WebDriver driver, final String name) {
        LOGGER.info("Vigyük az egeret a " + name + " nevű keresés fölé.");

        // Move mouse over query
        Utils.scrollIntoView(findThisQueryByName(Arrays.asList(name)).findElement(By.xpath(MainQueryLocators.QUERY_NAME.toString())));

        final Actions action = new Actions(driver);
        action.moveToElement(findThisQueryByName(Arrays.asList(name)).findElement(By.xpath(MainQueryLocators.QUERY_NAME.toString())))
                .build().perform();
        Utils.waitMillisec(1500);

        // Get tooltip text
        final WebElement body = driver.findElement(By.xpath("//body"));
        final WebElement tooltip = body.findElement(By.xpath(".//div[contains(@id, 'tooltip')]"));
        Utils.myAssertFalse("Meg kell jelennie egy felugró tooltipnek.", tooltip.getAttribute("class").contains("display: none"));

        return tooltip.getText();
    }

    /**
     * filters queries
     *
     *
     * @param name
     *
     */
    public static int filterQueries(final String name) {
        LOGGER.info("Töröljük a keresőpanelnél lévő szűrőmezőt, és írjuk be a következő karaktersorozatot: " + name);
        Utils.writeTextToThisField(name, MainQueryLocators.FILTER_INPUT_FIELD);
        Utils.waitForAndClickOnGeneralWebElement(MainQueryLocators.FILTER_BUTTON);
        Utils.waitMillisec(2000);
        return getNumberOfQueries();

    }

    /**
     * get my own queries
     *
     *
     */
    public static int getMyOwnSavedQueriesNumber() {
        final int originalQueryNumber = getNumberOfQueries();
        Utils.waitForAndClickOnGeneralWebElement(MainQueryLocators.MY_QUERIES_CHECKBOXFIELD);
        waitUntilQueryNumberChanges(originalQueryNumber);

        final int newNumber = getNumberOfQueries();
        Utils.waitForAndClickOnGeneralWebElement(MainQueryLocators.MY_QUERIES_CHECKBOXFIELD);
        waitUntilQueryNumberChanges(newNumber);

        return newNumber;
    }

    /**
     * Navigates to random record, and create query with random name
     *
     * @return
     */
    public static String navigateToRandomRecordAndCreateQuery() {

        // Navigate to random record type
        GeneralRecordTypes recordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();

        // Create query with random name
        final String name = Constants.PREFIX + Utils.randomString();
        createAndRunSubsetQuery(recordType, name);

        return name;
    }

    /**
     * Navigates to random record, and create query with random name without run subsetQuery
     *
     * @return
     */
    public static String navigateToRandomRecordAndCreateQueryWithoutRunSubsetQuery() {

        // Navigate to random record type
        RecordSelectionManager.selectRandomRecordTypeFromSelector();

        // Create query with random name
        final String name = Constants.PREFIX + Utils.randomString();
        createNewQueryWithName(name);

        return name;
    }

    /**
     * Creates and runs an empty query (to get all the records)
     *
     *
     */
    public static void createAndRunEmptyQuery() {
        final String nameOfQuery = Constants.PREFIX + Constants.MY_EMPTY_QUERY_NAME;
        createNewQueryWithName(nameOfQuery);
        runThisQuery(nameOfQuery);
    }

    public static void createAndRunSubsetQuery(GeneralRecordTypes recordType) {
        final String nameOfQuery = Constants.PREFIX + Constants.SUBSET_QUERY_NAME;
        createAndRunSubsetQuery(recordType, nameOfQuery);
    }

    public static void createAndRunOwnQuery(GeneralRecordTypes recordTypes) {
        final List<String> queriesName = getMyQueriesNameAsString();
        String ownQuery = Constants.PREFIX + Constants.OWN_QUERY_NAME;

        if (!queriesName.contains(ownQuery)) {
            createNewQueryWithName(ownQuery);
            SearchManager.openQueryEditorByName(ownQuery);
            final Map<GeneralTableAttributes, String> attributeValues = new HashMap<>();
            attributeValues.put(GeneralRecordAttributes.CREATOR, "");
            SearchEditorManager.addConditionWithThisAttribute(attributeValues);
            SearchEditorManager.changeMatchType(SearchMatchType.ME);
            SearchEditorManager.clickOnActiveCheckbox(GeneralRecordAttributes.CREATOR);
            clickSaveButton();
        }

        runThisQuery(ownQuery);
    }

    public static String createAndRunOwnQueryWithTheseAttributes(GeneralRecordTypes recordTypes, GeneralTableAttributes attribute,
            String mtmtId) {
        final List<String> queriesName = getMyQueriesNameAsString();
        //String ownQuery = Constants.PREFIX + Constants.OWN_QUERY_NAME;
        String ownQuery = Constants.PREFIX + Utils.randomString();

        if (!queriesName.contains(ownQuery)) {
            createNewQueryWithName(ownQuery);
            SearchManager.openQueryEditorByName(ownQuery);
            final Map<GeneralTableAttributes, String> attributeValues = new HashMap<>();
            attributeValues.put(attribute, mtmtId);
            SearchEditorManager.addConditionWithThisAttribute(attributeValues);

            clickSaveButton();
        }

        runThisQuery(ownQuery);

        return ownQuery;
    }

    public static void createAndRunSubsetQuery(GeneralRecordTypes recordType, String nameOfQuery) {
        final List<String> queriesName = getMyQueriesNameAsString();

        if (!queriesName.contains(nameOfQuery)) {
            final Map<GeneralTableAttributes, String> attributeValues = new HashMap<>();
            switch (recordType) {
                case PUBLICATION:
                    createNewQueryWithName(nameOfQuery);
                    SearchManager.openQueryEditorByName(nameOfQuery);
                    attributeValues.put(PublicationRecordAttributes.TITLE, "");
                    for (int i = 0; i < SUBSET_QUERY_AUTHORS; i++) {
                        SearchEditorManager.addConditionWithThisAttribute(attributeValues);
                    }
                    SearchEditorManager.changeConditionButton(SearchCondition.ANY);
                    break;
                case KEYWORD:
                    createNewQueryWithName(nameOfQuery);
                    SearchManager.openQueryEditorByName(nameOfQuery);
                    attributeValues.put(KeywordRecordAttributes.LABEL, Constants.PREFIX);
                    SearchEditorManager.addConditionWithThisAttribute(attributeValues);
                    SearchEditorManager.changeMatchType(SearchMatchType.ANY_MATCH);
                    break;
                case INSTITUTE:
                    createNewQueryWithName(nameOfQuery);
                    SearchManager.openQueryEditorByName(nameOfQuery);
                    attributeValues.put(InstituteRecordAttributes.NAME, INSTITUTE_SUBSET_QUERY);
                    SearchEditorManager.addConditionWithThisAttribute(attributeValues);
                    SearchEditorManager.changeMatchType(SearchMatchType.ANY_MATCH);
                    break;
                case CITATION:
                    createNewQueryWithName(nameOfQuery);
                    SearchManager.openQueryEditorByName(nameOfQuery);
                    attributeValues.put(CitationRecordAttributes.COMMENT, CITATION_SUBSET_QUERY);
                    SearchEditorManager.addConditionWithThisAttribute(attributeValues);
                    SearchEditorManager.changeMatchType(SearchMatchType.ANY_MATCH);
                    break;
                default:
                    // can't query partial result, use the empty query
                    //createAndRunEmptyQuery();
                    createNewQueryWithName(nameOfQuery);
                    return;
            }

            clickSaveButton();
        }

        runThisQuery(nameOfQuery);
    }

    /**
     * Creates and runs query to get deleted records
     *
     */
    public static void createAndRunDeletedRecordsQuery() {
        final List<String> queriesName = getMyQueriesNameAsString();
        final String nameOfQuery = Constants.MY_DELETED_RECORDS_QUERY_NAME;

        if (!queriesName.contains(nameOfQuery)) {
            createNewQueryWithName(nameOfQuery);
            openQueryEditorByName(nameOfQuery);

            // TODO: The deleted field works here for every attribute...
            SearchEditorManager.addConditionWithThisAttribute(AuthorTypeRecordAttributes.DELETED);
            clickSaveButton();
        }

        runThisQuery(nameOfQuery);
    }

    /**
     * Cleanup
     */
    public static void cleanup() {
        cleanup(1);
    }

    public static void cleanup(int numberOfRecordsToDelete) {
        // Cleanup
        ChangeViewManager.switchToGridView();
        SortManager.closeAllSorters();
        GeneralTableManager.changeOrderOnThisHeader(GeneralRecordAttributes.LAST_MODIFICATION, TableOrder.DESC);
        // remove the query which was needed to get all of the records
//				if (getMyQueriesNameAsString().contains(Constants.MY_EMPTY_QUERY_NAME)) {
//					deleteThisQuery(Constants.MY_EMPTY_QUERY_NAME);
//				}
        for (int i = 0; i < numberOfRecordsToDelete; i++) {
            DeleteRecordManager.removeFirstRecord();
            Utils.waitForRightPanelLoadingFinished();
        }
    }
}
