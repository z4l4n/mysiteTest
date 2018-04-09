package com.frontendart.testsuites.main.leftpanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.frontendart.categories.AUTHOR_Suite;
import com.frontendart.categories.CENTRAL_ADMIN_Suite;
import com.frontendart.categories.CoreSuite;
import com.frontendart.categories.INSTITUTIONAL_ADMIN_Suite;
import com.frontendart.categories.SearchWithConditionSuite;
import com.frontendart.categories.SimpleSearchSuite;
import com.frontendart.common.Constants;
import com.frontendart.common.Roles;
import com.frontendart.common.Utils;
import com.frontendart.junitclasses.JunitTestClass;
import com.frontendart.locators.main.MainPageLocators;
import com.frontendart.locators.main.leftpanel.MainQueryLocators;
import com.frontendart.locators.main.leftpanel.QueryEditorLocators;
import com.frontendart.locators.records.attributes.general.AuthorRecordAttributes;
import com.frontendart.locators.records.attributes.general.GeneralRecordTypes;
import com.frontendart.locators.records.attributes.general.GeneralTableAttributes;
import com.frontendart.locators.records.attributes.general.PublicationRecordAttributes;
import com.frontendart.locators.records.attributes.general.RecordAttributeFlags;
import com.frontendart.managers.main.leftpanel.RecordSelectionManager;
import com.frontendart.managers.main.leftpanel.SavedListsManager;
import com.frontendart.managers.main.leftpanel.SearchCheckTypes;
import com.frontendart.managers.main.leftpanel.SearchEditorManager;
import com.frontendart.managers.main.leftpanel.SearchManager;

/**
 * Test class for main search
 *
 * TODO: refaktor (CBO)
 *
 * @author Zoli
 *
 */
@Category({ AUTHOR_Suite.class, INSTITUTIONAL_ADMIN_Suite.class, CENTRAL_ADMIN_Suite.class })
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.JUnitTestContainsTooManyAsserts" })
public class SearchTest extends JunitTestClass {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("MainSearchTest");

    /**
     * Clicks on NewSearch button Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1143">#1143</a> Redmine
     * issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1457">#1457</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testCreateQuery() {
        Utils.writeMyRedmineIssues("#1143#1457");

        // Select random record type
        RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.getVisibleRecordTypeForActualRole());

        // checks queries - if too many queries are present, it removes all of them
        SearchManager.checkAndDeleteAllMyQueriesIfTooMuch();

        // Old number of queries
        final int oldNumberOfQueries = SearchManager.getNumberOfQueries();

        // Create new query and check
        final String name = SearchManager.createNewQuery();
        SearchManager.waitUntilQueryNumberChanges(oldNumberOfQueries);
        final int newNumberOfQueries = SearchManager.getNumberOfQueries();
        assertTrue("Number of searches unchanged",
                Utils.myAssertEquals("1-el több keresésnek kell lennie a táblázatban.", oldNumberOfQueries + 1, newNumberOfQueries));
        LOGGER.info("A keresések között szerepelnie kell az új keresésnek.");

        // Cleanup, the name of the query is related to the actual date.
        SearchManager.deleteThisQuery(name);
    }

    /**
     * Check query last run dates Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1129">#1129</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testNewQueryLastRunDates() {
        Utils.writeMyRedmineIssues("#1129");

        // Create query
        final String name = SearchManager.navigateToRandomRecordAndCreateQueryWithoutRunSubsetQuery();

        // Check that query didin't run before
        final String lastRunTextBeforeRunning = SearchManager.getQueryLastRunText(driver, name);
        assertTrue("The query was run.",
                Utils.myAssertTrue(
                        "A kereséshez tartozó felugró tooltipnek tájékoztatnia kell arról, hogy a query még soha nem volt futtatva.",
                        lastRunTextBeforeRunning.contains("Latest run: Never") || lastRunTextBeforeRunning.contains("soha")));

        // run query
        SearchManager.runThisQuery(name);

        // Validate
        Utils.defaultWait();
        final String lastRunTextAfterRunning = SearchManager.getQueryLastRunText(driver, name);
        final String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        Utils.myAssertFalse("A kereséshez tartozó felugró tooltipnek tájékoztatnia kell arról, hogy a query már volt futtatva.",
                lastRunTextAfterRunning.contains("Latest run: Never") || lastRunTextAfterRunning.contains("soha"));
        Utils.myAssertTrue("A kereséshez tartozó felugró tooltipnek tartalmaznia kell a futtatás évét.",
                lastRunTextAfterRunning.contains(year));

        // Cleanup
        SearchManager.deleteThisQuery(name);
    }

    /**
     * Check existing query last run dates Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/3029">#3029</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testExistingQueryLastRunDates() {
        Utils.writeMyRedmineIssues("#3029");

        // Create query
        RecordSelectionManager.selectRandomRecordTypeFromSelector();
        Utils.defaultWait();
        // String queryName = SearchManager.getMyQueriesNameAsString()
        List<String> queryNames = SearchManager.getMyQueriesNameAsString();

        if (queryNames.size() == 0) {
            Utils.myAssertTrue("Nincs keresés ebben a rekord típusban!", false);
            return;
        }
        String selectedQuery = queryNames.get(Utils.randInt(0, queryNames.size() - 1));
        SearchManager.runThisQuery(selectedQuery);
        // Validate
        Utils.defaultWait();
        final String lastRunTextAfterRunning = SearchManager.getQueryLastRunText(driver, selectedQuery);
        final String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        final String month = Integer.toString(Calendar.getInstance().get(Calendar.MONTH) + 1);
        final String day = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        Utils.myAssertFalse("A kereséshez tartozó felugró tooltipnek tájékoztatnia kell arról, hogy a query már volt futtatva.",
                lastRunTextAfterRunning.contains("Latest run: Never") || lastRunTextAfterRunning.contains("soha"));
        Utils.myAssertTrue("A kereséshez tartozó felugró tooltipnek tartalmaznia kell a futtatás évét.",
                lastRunTextAfterRunning.contains(year));
        Utils.myAssertTrue("A kereséshez tartozó felugró tooltipnek tartalmaznia kell a futtatás hónapját.",
                lastRunTextAfterRunning.contains(month));
        Utils.myAssertTrue("A kereséshez tartozó felugró tooltipnek tartalmaznia kell a futtatás napját.",
                lastRunTextAfterRunning.contains(day));

        // Cleanup
        //SearchManager.deleteThisQuery(Constants.MY_EMPTY_QUERY_NAME);
    }

    /**
     * Changes new query name Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1462">#1462</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testChangeQueryName() {
        Utils.writeMyRedmineIssues("#1462");
        /*
        // Create query
        final String name = SearchManager.navigateToRandomRecordAndCreateQuery();

        // Check query name
        assertTrue("The search with " + name + "  name not found",
                Utils.myAssertTrue("A keresések között szerepelnie kell egy " + name + " nevű keresésnek.",
                        SearchManager.getMyQueriesNameAsString().contains(name)));

        // Delete query
        SearchManager.deleteThisQuery(name);

        // Validate
        assertTrue("In the search there is search with " + name + " name.",
                Utils.myAssertFalse("A keresések közül törlődik a " + name + " nevű keresés.",
                        SearchManager.getMyQueriesNameAsString().contains(name)));

                        */
        final String oldName = SearchManager.navigateToRandomRecordAndCreateQuery();
        String newName = Utils.randomString(Constants.CHARSET, 6);
        Utils.defaultWait();
        SearchManager.renameQueryTo(oldName, newName);
        Utils.myAssertFalse("Átnevezés után nem szabadna a(z)  '" + oldName + "' nevű keresésnek szerepelnie!",
                SearchManager.getMyQueriesNameAsString().contains(oldName));
        Utils.myAssertTrue("Átnevezés után szerepelnie kéne a(z) '" + newName + "' nevű keresésnek!",
                SearchManager.getMyQueriesNameAsString().contains(newName));

        SearchManager.deleteThisQuery(newName);
    }

    /**
     * Runs empty query Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1048">#1048</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1273">#1273</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1339">#1339</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1424">#1424</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1458">#1458</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testEmptyQueryResultNumbers() {
        Utils.writeMyRedmineIssues("#1048#1273#1339#1424#1458");

        // Create query
        final String nameOfQuery = SearchManager.navigateToRandomRecordAndCreateQuery();

        // Check query before run
        Utils.myAssertTrue("A " + nameOfQuery + " nevű keresés eredmény mezőjének üresnek kéne lennie",
                SearchManager.getNumberOfResultsOfThisQuery(nameOfQuery).equals(" "));

        // Run query
        SearchManager.runThisQuery(nameOfQuery);

        // Check query results
        final int gridTableFullSize = RecordSelectionManager.gridTableFullSize();
        assertTrue("The search function work wrong.",
                Utils.myAssertEquals("A " + nameOfQuery
                        + " nevű keresés eredmény mezőjében szereplő értéknek meg kell egyeznie a táblázat összes sorának értékével.",
                        gridTableFullSize, Integer.parseInt(SearchManager.getNumberOfResultsOfThisQuery(nameOfQuery))));

        // Cleanup
        SearchManager.deleteThisQuery(nameOfQuery);

    }

    /**
     * Runs duplicate query
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testDuplicateQuery() {
        // Create query
        final String newName = SearchManager.navigateToRandomRecordAndCreateQuery();

        // Get query names before duplication
        final List<String> queryNamesBeforeDuplication = SearchManager.getMyQueriesNameAsString();
        assertTrue("More search in search with same name.",
                Utils.myAssertEquals(newName + " névvel egyetlen keresés szerepel a keresések között.",
                        1, Collections.frequency(queryNamesBeforeDuplication, newName)));

        // Duplicate
        SearchManager.duplicateThisQuery(newName);

        // Get query names after duplication
        String newNameCopy = Constants.PREFIX + "";
        final String language = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
        if ("English".equals(language)) {
            newNameCopy = newName.concat(" (copy)");
        } else {
            newNameCopy = newName.concat(" (másolat)");
        }
        final List<String> queryNamesAfterDuplication = SearchManager.getMyQueriesNameAsString();
        Utils.myAssertEquals(newName + " névvel egy keresésnek kellene szerepelnie a keresések között!",
                1, Collections.frequency(queryNamesAfterDuplication, newName));
        Utils.myAssertEquals(newNameCopy + " névvel egy keresésnek kellene szerepelnie a keresések között!",
                1, Collections.frequency(queryNamesAfterDuplication, newNameCopy));

        // Delete
        SearchManager.deleteThisQuery(newNameCopy);
        SearchManager.deleteThisQuery(newName);

    }

    /**
     * Checks if delete query is successful Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1476">#1476</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testDeleteQuery() {
        Utils.writeMyRedmineIssues("#1476");

        // Create query
        final String newName = SearchManager.navigateToRandomRecordAndCreateQuery();

        // Get query numbers before deletion
        final int oldNumberOfQueries = SearchManager.getNumberOfQueries();
        final List<String> queryNamesBeforeDeletion = SearchManager.getMyQueriesNameAsString();
        assertTrue("More search in search with same name.",
                Utils.myAssertEquals(newName + " névvel egyetlen keresés szerepel a keresések között.",
                        1, Collections.frequency(queryNamesBeforeDeletion, newName)));

        // Delete
        SearchManager.deleteThisQuery(newName);

        // Get query names after deletion, and validate
        final List<String> queryNamesAfterDeletion = SearchManager.getMyQueriesNameAsString();
        Utils.myAssertEquals(newName + " névvel egyetlen keresés sem szerepel a keresések között.",
                0, Collections.frequency(queryNamesAfterDeletion, newName));
        final int newNumberOfQueries = SearchManager.getNumberOfQueries();
        Utils.myAssertEquals("1-el kevesebb keresésnek kell lennie a táblázatban.", oldNumberOfQueries - 1, newNumberOfQueries);
    }

    /**
     * Re-run query
     */
    @Test
    @Category(SimpleSearchSuite.class)
    public final void testRerunEmptyQuery() {
        // Create query
        final String newName = SearchManager.navigateToRandomRecordAndCreateQuery();

        // Run query
        SearchManager.runThisQuery(newName);

        // Re-run query
        SearchManager.runThisQuery(newName);

        // Check results
        final int gridTableFullSize = RecordSelectionManager.gridTableFullSize();
        assertTrue("The Search function works wrong.",
                Utils.myAssertEquals("A " + newName
                        + " nevű keresés eredmény mezőjében szereplő értéknek meg kell egyeznie a táblázat összes sorának értékével.",
                        gridTableFullSize, Integer.parseInt(SearchManager.getNumberOfResultsOfThisQuery(newName))));

        // Cleanup
        SearchManager.deleteThisQuery(newName);

    }

    /**
     * Test query editor is visible Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1649">#1649</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testQueryEditorIsVisible() {
        Utils.writeMyRedmineIssues("#1649");

        // Create query
        final String newName = SearchManager.navigateToRandomRecordAndCreateQuery();
        Utils.defaultWait();
        // Open query editor
        SearchManager.openQueryEditorByName(newName);

        // Close editor
        SearchEditorManager.closeQueryEditor();

        Utils.defaultWait();

        // open query editor by double click
        LOGGER.info("Kattintsunk úgy a " + newName + " nevű keresésre, hogy közben a CTRL gombot lenyomva tartjuk.");
        final Actions myAction = new Actions(driver);
        myAction.keyDown(Keys.CONTROL)
                .click(SearchManager.findThisQueryByName(Arrays.asList(newName)).findElement(By.xpath("./tbody/tr/td[2]/div"))).build()
                .perform();

        // Close editor
        SearchEditorManager.closeQueryEditor();

        // Cleanup
        SearchManager.deleteThisQuery(newName);
    }

    /**
     * Add one condition to the query, and test if it is present Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1531">#1531</a>
     */
    @Test
    @Category({ SearchWithConditionSuite.class, CoreSuite.class })
    public final void testSimpleConditionIsPresent() {
        Utils.writeMyRedmineIssues("#1531");

        // Navigate to random record type, and create query and go to editor
        final GeneralRecordTypes recordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();
        Utils.defaultWait();
        SearchManager.checkAndDeleteAllMyQueriesIfTooMuch();
        Utils.defaultWait();
        final int originalQueryNumber = SearchManager.getNumberOfQueries();
        final String name = Constants.PREFIX + Utils.randomString();
        SearchManager.startNewQuery(name);
        Utils.defaultWait();
        // Add simple condition and save
        GeneralTableAttributes locator = SearchEditorManager.addRandomCondition(recordType);

        while (Utils.getActualRole().equals(Roles.AUTHOR) && locator.equals(AuthorRecordAttributes.EMAIL_ADDRESS_CONFIRMED)) {
            locator = SearchEditorManager.addRandomCondition(recordType);
        }

        SearchManager.clickSaveButton();
        SearchManager.waitUntilQueryNumberChanges(originalQueryNumber);

        // Open query editor, and check
        SearchManager.openQueryEditorByName(name);
        assertTrue("More search criterias are present! ",
                Utils.myAssertEquals("1 db keresési feltételnek kell lennie.", 1, SearchEditorManager.getNumberOfConditions()));

        // Check label
        final String labelOfCondition = SearchEditorManager.getAllConditionLabels().get(0).getText();
        assertTrue("This label " + Utils.getAllMyLabelsAsString(locator) + " is missing.",
                Utils.myAssertTrue(
                        "Az  1db keresési feltételnek tartalmaznia kell a következő feliratot: " + Utils.getAllMyLabelsAsString(locator),
                        Utils.doesThisStringListContainsThisAttribute(Arrays.asList(labelOfCondition), locator)));
        SearchEditorManager.clickOnCancelButton();
        Utils.defaultWait();
        // Cleanup
        SearchManager.deleteThisQuery(name);
    }

    /**
     * Remove one condition from the query, and test if it has disappeared.
     */
    @Test
    @Category({ SearchWithConditionSuite.class, CoreSuite.class })
    public final void testSimpleConditionDeletion() {
        // Navigate to random record, and add query
        final GeneralRecordTypes recordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();
        Utils.defaultWait();
        // checks queries - if too many queries are present, it removes all of them
        SearchManager.checkAndDeleteAllMyQueriesIfTooMuch();
        Utils.defaultWait();
        // Start query
        final String name = Constants.PREFIX + Utils.randomString();
        SearchManager.startNewQuery(name);
        Utils.defaultWait();
        final GeneralTableAttributes locator = SearchEditorManager.addRandomCondition(recordType);
        Utils.defaultWait();

        // Remove condition and check
        SearchEditorManager.removeCondition(locator);
        Utils.defaultWait();
        assertTrue("There should be no conditions present! ",
                Utils.myAssertEquals("0 db keresési feltételnek kell lennie.", SearchEditorManager.getNumberOfConditions(), 0));
        SearchEditorManager.clickOnCancelButton();

    }

    /**
     * change views and run query Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1297">#1297</a> Redmine
     * issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1424">#1424</a>
     */
    @Test
    @Ignore // Nincs icon nézet már
    @Category(SearchWithConditionSuite.class)
    public final void testChangeView() {
        Utils.writeMyRedmineIssues("#1297#1424");

        // Navigate to random record type
        final GeneralRecordTypes recordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();

        // Change view
//		ChangeViewManager.switchToIconView();
//		assertTrue("Icon view is not visible.", Utils.myAssertTrue("Az ikon nézetnek láthatónak kell lennie.",
//				Utils.isThisElementVisible(MainPageLocators.CONTENT_ICON_GRID_PANEL)));

        // Create query
        final int originalQueryNumber = SearchManager.getNumberOfQueries();
        final String name = Constants.PREFIX + Utils.randomString();
        SearchManager.startNewQuery(name);
        SearchEditorManager.addRandomCondition(recordType);
        SearchManager.clickSaveButton();
        SearchManager.waitUntilQueryNumberChanges(originalQueryNumber);

        // run
        final WebElement queryToFind = SearchManager.findThisQueryByName(Arrays.asList(name));
        final WebElement resultFieldOfQuery = queryToFind.findElement(By.xpath(MainQueryLocators.QUERY_NUMBER_OF_RESULTS.toString()));
        SearchManager.performThisOperationOnThisQuery(name, MainQueryLocators.RUN);
        Utils.dismissRunAlertIfExist();
        Utils.waitForStalenessOfThisElement(resultFieldOfQuery);

        // Validate - If we changed icon view, it is not supposed to be the default.
        Utils.myAssertFalse("Icon view should be visible after running the query.",
                Utils.isThisElementPresent(MainPageLocators.CONTENT_ICON_GRID_PANEL));

        // Cleanup
        SearchManager.deleteThisQuery(name);

    }

    /**
     * query filtering
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testFilterQuery() {
        // Create query
        final String name = SearchManager.navigateToRandomRecordAndCreateQuery();
        Utils.defaultWait();
        Utils.myAssertEquals("1 db keresésnek kell szerepelnie a találatok között.", 1, SearchManager.filterQueries(name));
        Utils.defaultWait();
        // Duplicate
        SearchManager.duplicateThisQuery(name);
        Utils.defaultWait();
        Utils.myAssertEquals("2 db keresésnek kell szerepelnie a találatok között.", 2, SearchManager.filterQueries(name));
        Utils.defaultWait();
        // delete
        String newNameCopy = "";
        final String language = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
        if ("English".equals(language)) {
            newNameCopy = name.concat(" (copy)");
        } else {
            newNameCopy = name.concat(" (másolat)");
        }
        SearchManager.deleteThisQuery(newNameCopy);
        Utils.defaultWait();
        Utils.myAssertEquals("1 db keresésnek kell szerepelnie a találatok között.", 1, SearchManager.filterQueries(name));
        Utils.defaultWait();
        SearchManager.deleteThisQuery(name);
        Utils.defaultWait();
        Utils.myAssertEquals("0 db keresésnek kell szerepelnie a találatok között.", 0, SearchManager.filterQueries(name));
    }

    /**
     * query filtering Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1590">#1590</a>
     */
    @Test
    @Category(SimpleSearchSuite.class)
    public final void testFilterMySavedQueries() {
        Utils.writeMyRedmineIssues("#1590");

        // Navigate to random record type, and check query numbers
        RecordSelectionManager.selectRandomRecordTypeFromSelector();

        // checks queries - if too many queries are present, it removes all of them
        //SearchManager.checkAndDeleteAllMyQueriesIfTooMuch();
        Utils.defaultWait();
        // Get query number
        final int queriesNumber = SearchManager.getNumberOfQueries();

        // Get my saved queries number
        final int myOldSavedQueriesNumber = SearchManager.getMyOwnSavedQueriesNumber();
        Utils.myAssertNotEquals("Lennie kell előre definiált keresés(ek)nek.", queriesNumber, myOldSavedQueriesNumber);
        Utils.defaultWait();
        // Create query and check
        final String name = Constants.PREFIX + Utils.randomString();
        SearchManager.createNewQueryWithName(name);
        Utils.defaultWait();
        Utils.myAssertEquals("A kezdetekhez képest 1-el több saját keresésnek kell szerepelnie a találatok között.",
                myOldSavedQueriesNumber + 1, SearchManager.getMyOwnSavedQueriesNumber());

        // Duplicate query and check
        SearchManager.duplicateThisQuery(name);
        Utils.myAssertEquals("A kezdetekhez képest 2-vel több saját keresésnek kell szerepelnie a találatok között.",
                myOldSavedQueriesNumber + 2, SearchManager.getMyOwnSavedQueriesNumber());

        // delete
        String newNameCopy = "";
        final String language = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
        if ("English".equals(language)) {
            newNameCopy = name.concat(" (copy)");
        } else {
            newNameCopy = name.concat(" (másolat)");
        }
        SearchManager.deleteThisQuery(newNameCopy);
        Utils.myAssertEquals("A kezdetekhez képest 1-el több saját keresésnek kell szerepelnie a találatok között.",
                myOldSavedQueriesNumber + 1, SearchManager.getMyOwnSavedQueriesNumber());
        SearchManager.deleteThisQuery(name);
        Utils.myAssertEquals("A saját keresések számának meg kell egyeznie a kezdeti értékkel.", myOldSavedQueriesNumber,
                SearchManager.getMyOwnSavedQueriesNumber());
    }

    /**
     * Tests condition options. Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1543">#1543</a>
     */
    @Test
    @Category(SearchWithConditionSuite.class)
    public final void testConditionOptions() {
        Utils.writeMyRedmineIssues("#1543");

        // Select random record type, create query and go to editor
        final GeneralRecordTypes myGeneralRecordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();
        //SearchManager.checkAndDeleteAllMyQueriesIfTooMuch();
        final int originalQueryNumber = SearchManager.getNumberOfQueries();
        final String name = Constants.PREFIX + Utils.randomString();
        SearchManager.startNewQuery(name);

        // Validate options
        final GeneralTableAttributes[] attributes = myGeneralRecordType.getAttributes().getEnumConstants();
        final List<String> options = SearchEditorManager.getAllConditionOptionsAsString();
        for (final GeneralTableAttributes attribute : attributes) {
            if (attribute.getAttributeFlags().contains(RecordAttributeFlags.NOT_SEARCHABLE)) {
                continue;
            }
            // check Hungarian attribute name
            int result = options.indexOf(attribute.getNames().get(0));
            // check English
            if (result == -1) {
                result = options.indexOf(attribute.getNames().get(1));
            }
            // search for subcategory menu option
            if (result == -1) {
                result = SearchEditorManager.searchForOption(attribute) ? 0 : -1;
            }

            assertNotEquals(
                    "The \"" + Utils.getAllMyLabelsAsString(attribute) + "\" attribute is not present at this record type: "
                            + myGeneralRecordType,
                    result, -1);

        }

        // Click save
        SearchManager.clickSaveButton();
        SearchManager.waitUntilQueryNumberChanges(originalQueryNumber);

        // Cleanup
        SearchManager.deleteThisQuery(name);
    }

    /**
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1133">#1133</a> Redmine issue number:
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public void testClickOnSaveAndRunButtonShouldWork() {
        Utils.writeMyRedmineIssues("#1133");

        RecordSelectionManager.selectRandomRecordTypeFromSelector();

        Utils.defaultWait();

        final String name = Constants.PREFIX + Utils.randomString();

        SearchManager.startNewQuery(name);

        Utils.defaultWait();

        SearchManager.clickOnSaveAndRunButton();

        Utils.defaultWait();

        Utils.myAssertTrue("There should be some results", RecordSelectionManager.gridTableFullSize() > 0);

        // Cleanup
        SearchManager.deleteThisQuery(name);
    }

    /**
     * Runs query with one condition ("is met" and after that, "is not met") Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1133">#1133</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1134">#1134</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1190">#1190</a> TODO: Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1284">#1284</a> TODO: Redmine issue number:
     *
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1294">#1294</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1588">#1588</a> Redmine issue number:
     * <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1650">#1650</a>
     */

    @Test
    @Category({ SearchWithConditionSuite.class, CoreSuite.class })
    public final void testRunQueryWithOneSimpleCondition() {
        Utils.writeMyRedmineIssues("#1133#1134#1190#1294#1588#1650");

        // Navigate to random record type, create query and go to editor
        //final GeneralRecordTypes myGeneralRecordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();

//		final GeneralRecordTypes myGeneralRecordType = GeneralRecordTypes.INSTITUTE;
//		RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.INSTITUTE);

        final GeneralRecordTypes myGeneralRecordType = GeneralRecordTypes.KEYWORD;
        RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.KEYWORD);

        //SearchManager.checkAndDeleteAllMyQueriesIfTooMuch();
        final int originalQueryNumber = SearchManager.getNumberOfQueries();
        final String name = Constants.PREFIX + Utils.randomString();
        Utils.defaultWait();
        SearchManager.startNewQuery(name);

        // Add random condition
        final Map<GeneralTableAttributes, String> attributeValues = new HashMap<>();

        final GeneralTableAttributes attribute = SearchEditorManager.addRandomCondition(myGeneralRecordType);
        final String value = SearchEditorManager.getValueFromLastCondition(attribute);
        attributeValues.put(attribute, value);

        // Save query
        SearchManager.clickSaveButton();
        SearchManager.waitUntilQueryNumberChanges(originalQueryNumber);
        SearchManager.runThisQuery(name);
        Utils.defaultWait();
        SearchManager.checkQueryResult(attributeValues, SearchCheckTypes.IS_EVERY_IS_MET);

        // Is not met part, validate
        LOGGER.info("Kattintsunk a Tartalmazza/Is met gombra.");
        SearchEditorManager.changeIsMetConditionOfThisQuery(name);
        Utils.defaultWait();
        SearchManager.runThisQuery(name);
        Utils.defaultWait();
        SearchManager.checkQueryResult(attributeValues, SearchCheckTypes.IS_EVERY_IS_NOT_MET);

        // Cleanup
        SearchManager.deleteThisQuery(name);
    }

    /**
     * save query to list.
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testSaveQueryAsList() {
        // Create query and run
        final String name = SearchManager.navigateToRandomRecordAndCreateQuery();
        Utils.defaultWait();
        SearchManager.runThisQuery(name);
        Utils.defaultWait();

        // Duplicate
        final String nameOfSavedList = SearchManager.saveThisQueryToList(name);
        Utils.defaultWait();
        SavedListsManager.expandListPanel();
        Utils.defaultWait();
        assertTrue("The list should be present.", SavedListsManager.getSavedListsNamesAsString().contains(nameOfSavedList));

        // Cleanup
        SavedListsManager.deleteThisListByName(nameOfSavedList);
        Utils.defaultWait();
        SearchManager.deleteThisQuery(name);
    }

    /**
     * save query as default.
     */
    // TODO: reimplement

    /**
     * searches by ID
     *
     * TODO: Update the test. Theres is an obstacle with the visibility of the MTMT ID header in the gridpanel
     *
     * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2196">#2196</a>
     */
    /*	@Test
    	@Category(SearchWithConditionSuite.class)
    	public final void testSearchByID() {
    		Utils.writeMyRedmineIssues("#2196");

    		// Select Author  record type, and select one row
    		RecordSelectionManager.selectThisRecordTypeFromSelector(GeneralRecordTypes.AUTHOR);
    		ChangeViewManager.switchToGridView();

    		// Get mtID of selected row
    		final String label = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).getText();
    		if ("English".equals(label)) {
    			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_BUTTON);
    			Utils.waitForAndClickOnGeneralWebElement(MainPageLocators.CHANGE_LANGUAGE_HUNGARIAN);
    			Utils.waitMillisec(10000);
    		}
    		final List<String> oldVisibleHeaders = GeneralTableManager.getAllVisibleHeadersText();
    		ChangeViewManager.switchToGridView();
    		if (!oldVisibleHeaders.contains("MTMT azonosító")) {
    			GeneralTableManager.changeTheVisibilityOfThisHeader("MTMT azonosító");
    			Utils.defaultWait();
    		}
    		final WebElement myRow = RecordSelectionManager.selectRandomRenderedRowsFromGridPanel(1).get(0);
    		final int idToFind = GeneralTableManager.getIDOfThisRow(myRow);

    		// Create new query
    		SearchManager.checkAndDeleteAllMyQueriesIfTooMuch();
    		final int oldNumberOfQueries = SearchManager.getNumberOfQueries();
    		final String name = Utils.randomString();
    		SearchManager.startNewQuery(name);

    		// Add "id" condition with the ID we would like to find, and save query
    		final Map<GeneralTableAttributes, String> attributeValues = new HashMap<GeneralTableAttributes, String>();
    		attributeValues.put(AuthorRecordAttributes.ID, Integer.toString(idToFind));
    		SearchEditorManager.addConditionWithThisAttribute(attributeValues);
    		SearchManager.clickSaveButton();
    		SearchManager.waitUntilQueryNumberChanges(oldNumberOfQueries);

    		// Run query
    		SearchManager.runThisQuery(name);

    		// Check result (validate)
    		if (!oldVisibleHeaders.contains("MTMT azonosító")) {
    			GeneralTableManager.changeTheVisibilityOfThisHeader("MTMT azonosító");
    			Utils.defaultWait();
    		}
    		assertEquals("There should be one record in the results table!", 1, RecordSelectionManager.gridTableFullSize());
    		final int idOfFirstRow = GeneralTableManager.getIDOfThisRow(RecordSelectionManager.getGridElementsOnPage().get(0));
    		assertEquals("The ID of the result record is not correct!", idToFind, idOfFirstRow);

    		// Cleanup
    		SearchManager.deleteThisQuery(name);
    	}
    */

    /**
     * searches for api.defaultQueries named queries
     *
     * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/3386">#3386</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testCodedQueryNames() {
        Utils.writeMyRedmineIssues("#3386");

        // Select random record type
        final GeneralRecordTypes selectedRecordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();

        // Get names of all queries
        final List<String> listOfNames = SearchManager.getMyQueriesNameAsString();
        for (final String queryName : listOfNames) {
            // Validation
            assertFalse("There are coded query names at this record type: " + selectedRecordType,
                    queryName.contains("api.defaultQueries"));
        }
    }

    /**
     * searches for same query names
     *
     * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/2192">#2192</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public final void testSameQueryNames() {
        Utils.writeMyRedmineIssues("#2192");

        // Select random record type
        final GeneralRecordTypes selectedRecordType = RecordSelectionManager.selectRandomRecordTypeFromSelector();

        Utils.defaultWait();

        // Get names of all queries
        final List<String> listOfNames = SearchManager.getMyQueriesNameAsString();
        final Set<String> setOfNames = new HashSet<>(listOfNames);

        // Do the validation
        assertEquals("There are queries present with the same name at this record type: " + selectedRecordType + "! ", setOfNames.size(),
                listOfNames.size());
    }

    /**
     * Runs query with one string condition that contains only spaces (Publication, title attribute)
     *
     * Redmine issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/3395">#3395</a>
     */
    @Test
    @Category({ SearchWithConditionSuite.class, CoreSuite.class })
    public final void testRunQueryWithConditionOnlySpaces() {
        Utils.writeMyRedmineIssues("#3395");

        // Navigate to Publication record type, create query and go to editor
        //SearchManager.checkAndDeleteAllMyQueriesIfTooMuch();
        final String name = Constants.PREFIX + Utils.randomString();
        SearchManager.startNewQuery(name);

        // Add string condition (title)
        final Map<GeneralTableAttributes, String> attributeValues = new HashMap<>();
        attributeValues.put(PublicationRecordAttributes.TITLE, "   ");
        SearchEditorManager.addConditionWithThisAttribute(attributeValues);

        // Save query and validate
        SearchManager.clickSaveButton();
        assertTrue("Message box should be visible!", Utils.isMessageBoxVisible());
        Utils.acceptMessageBoxIfVisible();
        SearchEditorManager.clickOnCancelButton();

    }

    /**
     * issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1589">#1589</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public void testAdvancedQueryShouldWorkAfterAddingAnInstitute() {
        // bug appears at Inst. admin level
        assumeTrue(Utils.getActualRole() == Roles.INSTITUTIONAL_ADMIN);

        Utils.writeMyRedmineIssues("#1589");

        final String name = SearchManager.navigateToRandomRecordAndCreateQuery();
        SearchManager.performThisOperationOnThisQuery(name, MainQueryLocators.ADVANCED);
        SearchManager.selectRandomInstituteOnAdvancedQuerySettingsPanel();
        Utils.defaultWait();
        Utils.createGeneralWebElementFromEnum(QueryEditorLocators.ADVANCED_SAVE_BUTTON).click();
        Utils.defaultWait();
        Utils.myAssertFalse("There shouldnt be an error window on the screen.", Utils.isErrorWindowVisible());
        // Cleanup
        SearchManager.deleteThisQuery(name);
    }

    /**
     * issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/1612">#1589</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public void testQueryShouldntDisappearAfterSettingMinVisibility() {
        // bug appears at Inst. admin level
        assumeTrue(Utils.getActualRole() == Roles.CENTRAL_ADMIN);

        Utils.writeMyRedmineIssues("#1612");

        final String name = SearchManager.navigateToRandomRecordAndCreateQuery();
        SearchManager.performThisOperationOnThisQuery(name, MainQueryLocators.ADVANCED);
        Utils.defaultWait();
        SearchManager.selectRandomVisibilityOnAdvancedQuerySettingsPanel();
        Utils.defaultWait();
        Utils.createGeneralWebElementFromEnum(QueryEditorLocators.ADVANCED_SAVE_BUTTON).click();
        Utils.defaultWait();
        Utils.myAssertTrue("The queries list should contain the created query!", SearchManager.getMyQueriesNameAsString().contains(name));
        // Cleanup
        SearchManager.deleteThisQuery(name);

    }

    /**
     * issue number: <a href="https://redmine.mt2.dsd.sztaki.hu:18018/issues/5301">#5301</a>
     */
    @Test
    @Category({ SimpleSearchSuite.class, CoreSuite.class })
    public void testQueryNamesShouldBeVisibleAfterChangingLanguage() {
        Utils.writeMyRedmineIssues("#5301");

        // Switching to Hungarian lang
        WebElement langButton = Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON);
        if ("English".equals(langButton.getText())) {
            langButton.click();
            Utils.defaultWait();
            Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_HUNGARIAN).click();
            Utils.defaultWait();
        }

        // Creating a query
        String name = SearchManager.navigateToRandomRecordAndCreateQuery();
        Utils.defaultWait();
        Utils.myAssertTrue("The newly generated query should be visible!", SearchManager.getMyQueriesNameAsString().contains(name));

        // Switching to English lang
        Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_BUTTON).click();
        Utils.defaultWait();
        Utils.createGeneralWebElementFromEnum(MainPageLocators.CHANGE_LANGUAGE_ENGLISH).click();
        Utils.defaultWait();

        // Validating
        Utils.myAssertTrue("The newly generated query should be visible after switching to English!",
                SearchManager.getMyQueriesNameAsString().contains(name));

        // Cleanup
        SearchManager.deleteThisQuery(name);
    }

    // FURTHER TESTS TO BE IMPLEMENTED

    /**
     * Runs query with an exact match condition
     *
     * TODO: reimplement
     */

    /*
    * TODO: reimplement
    */
    /**
     * Runs empty query by clicking on it.
     */

}
